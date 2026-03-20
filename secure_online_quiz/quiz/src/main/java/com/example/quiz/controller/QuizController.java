package com.example.quiz.controller;

import com.example.quiz.model.Question;
import com.example.quiz.model.User;
import com.example.quiz.service.QuestionsService;
import com.example.quiz.service.QuizUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller class for handling quiz-related API requests.
 * Manages user authentication, quiz question management, and answer submission.
 */
@Controller
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private QuizUserDetailsService quizUserDetailsService;

    // GET Endpoints for retrieving pages

    /* GET endpoint for home page */
    @GetMapping("/")
    public String home() {
        return "home";
    }

    /**
     * GET endpoint for retrieving the login page.
     *
     * @param model the model object to pass data to the view
     * @return login page view name
     */
    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    /**
     * GET endpoint for retrieving the registration page.
     *
     * @param model the model object to pass data to the view
     * @return registration page view name
     */
    @GetMapping("/register")
    public String getRegistrationPage(Model model) {
        return "register";
    }

    /**
     * GET mapping for retrieving the addQuiz page.
     *
     * @param model the model object to pass data to the view
     * @return addQuiz page view name
     */
    @GetMapping("/add-quiz")
    public String getAddQuizPage(Model model) {
        return "add-quiz";
    }

    /**
     * GET mapping for retrieving the editQuiz page.
     * Loads question details if questionId is provided.
     *
     * @param questionId the ID of the question to edit (optional)
     * @param model      the model object to pass data to the view
     * @return editQuiz page view name
     */
    @GetMapping("/edit-quiz")
    public String getEditQuizPage(@RequestParam(value = "id", required = false) Integer questionId, Model model) {
        if (questionId != null) {
            Question question = questionsService.getQuestionById(questionId);
            if (question != null) {
                model.addAttribute("question", question);
            }
        }
        return "edit-quiz";
    }

    /**
     * GET request for retrieving quiz questions.
     *
     * @return ResponseEntity with list of all quiz questions
     */
    @GetMapping("/questions")
    @ResponseBody
    public ResponseEntity<List<Question>> getQuizQuestions() {
        List<Question> questions = questionsService.loadQuizzes();
        return ResponseEntity.ok(questions);
    }

    /**
     * GET request for retrieving the result page.
     *
     * @param model the model object to pass data to the view
     * @return result page view name
     */
    @GetMapping("/result")
    public String getResultPage(Model model) {
        return "result";
    }

    /* Quiz List Mapping */
    @GetMapping("/quizlist")
    public String getQuizListPage(Model model) {
        return "quiz-list";
    }

    @GetMapping("/quiz")
    public String getQuizPage() {
        return "quiz";
    }

    // POST Endpoints

    /**
     * POST request for registering a new user.
     *
     * @param username the username for the new account
     * @param password the password for the new account
     * @param email    the email address of the user
     * @param role     the role assigned to the user
     * @return ResponseEntity with registration status
     */
    @PostMapping("/register")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> registerUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam(defaultValue = "USER") String role) {

        Map<String, Object> response = new HashMap<>();

        try {
            boolean success = quizUserDetailsService.registerUser(username, password, email, role);

            if (success) {
                response.put("success", true);
                response.put("message", "User registered successfully");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Username already exists");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Registration failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * POST request for adding a new quiz question.
     *
     * @param id            the ID of the question
     * @param questionText  the text of the question
     * @param options       the answer options as a comma-separated string
     * @param correctAnswer the correct answer text
     * @return ResponseEntity with add quiz status
     */
    @PostMapping("/add-quiz")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addQuizQuestion(
            @RequestParam int id,
            @RequestParam String questionText,
            @RequestParam String options,
            @RequestParam String correctAnswer) {

        Map<String, Object> response = new HashMap<>();

        try {
            ArrayList<String> optionsList = new ArrayList<>(Arrays.asList(options.split(",")));

            Question question = new Question(id, questionText, optionsList, correctAnswer);
            boolean success = questionsService.addQuiz(question);

            if (success) {
                response.put("success", true);
                response.put("message", "Question added successfully");
                response.put("questionId", id);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Failed to add question");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error adding question: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * POST request for submitting quiz answers.
     * Evaluates the answers and returns the score.
     *
     * @param answers a map of question IDs to user answers
     * @return ResponseEntity with evaluation results
     */
    @PostMapping("/submit-answers")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> submitAnswers(@RequestBody Map<String, String> answers) {
        Map<String, Object> response = new HashMap<>();

        try {
            int correctCount = 0;
            int totalCount = 0;

            // Evaluate each answer
            for (Map.Entry<String, String> entry : answers.entrySet()) {
                int questionId = Integer.parseInt(entry.getKey());
                String userAnswer = entry.getValue();

                Question question = questionsService.getQuestionById(questionId);
                if (question != null) {
                    totalCount++;
                    if (question.isCorrectAnswer(userAnswer)) {
                        correctCount++;
                    }
                }
            }

            int score = totalCount > 0 ? (correctCount * 100) / totalCount : 0;

            response.put("success", true);
            response.put("score", score);
            response.put("correctAnswers", correctCount);
            response.put("totalQuestions", totalCount);
            response.put("message", "Quiz submitted successfully");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error submitting answers: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // PUT Endpoint

    /**
     * PUT request for editing an existing quiz question.
     *
     * @param id            the ID of the question to edit
     * @param questionText  the updated question text
     * @param options       the updated answer options as a comma-separated string
     * @param correctAnswer the updated correct answer text
     * @return ResponseEntity with edit status
     */
    @PutMapping("/edit-quiz/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> editQuizQuestion(
            @PathVariable int id,
            @RequestParam String questionText,
            @RequestParam String options,
            @RequestParam String correctAnswer) {

        Map<String, Object> response = new HashMap<>();

        try {
            ArrayList<String> optionsList = new ArrayList<>(Arrays.asList(options.split(",")));

            Question question = new Question(id, questionText, optionsList, correctAnswer);
            boolean success = questionsService.editQuiz(question);

            if (success) {
                response.put("success", true);
                response.put("message", "Question updated successfully");
                response.put("questionId", id);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Question not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error updating question: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // DELETE Endpoint

    /**
     * DELETE request for removing a quiz question.
     *
     * @param questionId the ID of the question to delete
     * @return ResponseEntity with delete status
     */
    @DeleteMapping("/delete-quiz/{questionId}")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteQuizQuestion(@PathVariable int questionId) {
        Map<String, Object> response = new HashMap<>();

        try {
            boolean success = questionsService.deleteQuiz(questionId);

            if (success) {
                response.put("success", true);
                response.put("message", "Question deleted successfully");
                response.put("questionId", questionId);
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "Question not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Error deleting question: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
