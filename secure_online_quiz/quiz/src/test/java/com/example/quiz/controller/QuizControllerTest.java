package com.example.quiz.controller;

import com.example.quiz.model.Question;
import com.example.quiz.service.QuestionsService;
import com.example.quiz.service.QuizUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class QuizControllerTest {

    private QuizController quizController;
    private QuestionsService questionsService;
    private QuizUserDetailsService quizUserDetailsService;
    private Model model;

    @BeforeEach
    public void setUp() {
        questionsService = mock(QuestionsService.class);
        quizUserDetailsService = mock(QuizUserDetailsService.class);
        quizController = new QuizController();
        // Inject mocks using reflection
        try {
            java.lang.reflect.Field field1 = QuizController.class.getDeclaredField("questionsService");
            field1.setAccessible(true);
            field1.set(quizController, questionsService);

            java.lang.reflect.Field field2 = QuizController.class.getDeclaredField("quizUserDetailsService");
            field2.setAccessible(true);
            field2.set(quizController, quizUserDetailsService);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model = mock(Model.class);
    }

    @Test
    public void testHomeEndpoint() {
        // Act
        String result = quizController.home();

        // Assert
        assertEquals("home", result);
    }

    @Test
    public void testGetLoginPage() {
        // Act
        String result = quizController.getLoginPage(model);

        // Assert
        assertEquals("login", result);
    }

    @Test
    public void testGetRegistrationPage() {
        // Act
        String result = quizController.getRegistrationPage(model);

        // Assert
        assertEquals("register", result);
    }

    @Test
    public void testGetAddQuizPage() {
        // Act
        String result = quizController.getAddQuizPage(model);

        // Assert
        assertEquals("add-quiz", result);
    }

    @Test
    public void testGetEditQuizPageWithoutQuestionId() {
        // Act
        String result = quizController.getEditQuizPage(null, model);

        // Assert
        assertEquals("edit-quiz", result);
        verify(questionsService, never()).getQuestionById(anyInt());
    }

    @Test
    public void testGetEditQuizPageWithQuestionId() {
        // Arrange
        ArrayList<String> options = new ArrayList<>();
        options.add("Option A");
        Question question = new Question(1, "Test Q", options, "Option A");
        when(questionsService.getQuestionById(1)).thenReturn(question);

        // Act
        String result = quizController.getEditQuizPage(1, model);

        // Assert
        assertEquals("edit-quiz", result);
        verify(questionsService).getQuestionById(1);
        verify(model).addAttribute("question", question);
    }

    @Test
    public void testGetResultPage() {
        // Act
        String result = quizController.getResultPage(model);

        // Assert
        assertEquals("result", result);
    }

    @Test
    public void testGetQuizListPage() {
        // Act
        String result = quizController.getQuizListPage(model);

        // Assert
        assertEquals("quiz-list", result);
    }

    @Test
    public void testGetQuizPage() {
        // Act
        String result = quizController.getQuizPage();

        // Assert
        assertEquals("quiz", result);
    }

    @Test
    public void testRegisterUserSuccess() {
        // Arrange
        when(quizUserDetailsService.registerUser("testuser", "password", "test@example.com", "USER"))
                .thenReturn(true);

        // Act
        var response = quizController.registerUser("testuser", "password", "test@example.com", "USER");

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        Map<String, Object> body = response.getBody();
        assertEquals(true, body.get("success"));
        assertEquals("User registered successfully", body.get("message"));
    }

    @Test
    public void testRegisterUserDuplicate() {
        // Arrange
        when(quizUserDetailsService.registerUser("existing", "password", "email@test.com", "USER"))
                .thenReturn(false);

        // Act
        var response = quizController.registerUser("existing", "password", "email@test.com", "USER");

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        Map<String, Object> body = response.getBody();
        assertEquals(false, body.get("success"));
        assertEquals("Username already exists", body.get("message"));
    }

    @Test
    public void testAddQuizQuestion() {
        // Arrange
        when(questionsService.addQuiz(any(Question.class))).thenReturn(true);

        // Act
        var response = quizController.addQuizQuestion(1, "Test Question", "A,B,C", "A");

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        Map<String, Object> body = response.getBody();
        assertEquals(true, body.get("success"));
        assertEquals("Question added successfully", body.get("message"));
    }

    @Test
    public void testSubmitAnswersCorrectly() {
        // Arrange
        ArrayList<String> options = new ArrayList<>();
        options.add("Option A");
        options.add("Option B");
        Question question = new Question(1, "Test Q", options, "Option A");
        when(questionsService.getQuestionById(1)).thenReturn(question);

        Map<String, String> answers = new java.util.HashMap<>();
        answers.put("1", "Option A");

        // Act
        var response = quizController.submitAnswers(answers);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        Map<String, Object> body = response.getBody();
        assertEquals(true, body.get("success"));
        assertEquals(100, body.get("score"));
    }

    @Test
    public void testSubmitAnswersIncorrectly() {
        // Arrange
        ArrayList<String> options = new ArrayList<>();
        options.add("Option A");
        options.add("Option B");
        Question question = new Question(1, "Test Q", options, "Option A");
        when(questionsService.getQuestionById(1)).thenReturn(question);

        Map<String, String> answers = new java.util.HashMap<>();
        answers.put("1", "Option B");

        // Act
        var response = quizController.submitAnswers(answers);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        Map<String, Object> body = response.getBody();
        assertEquals(true, body.get("success"));
        assertEquals(0, body.get("score"));
    }

    @Test
    public void testEditQuizQuestion() {
        // Arrange
        when(questionsService.editQuiz(any(Question.class))).thenReturn(true);

        // Act
        var response = quizController.editQuizQuestion(1, "Updated Q", "X,Y", "X");

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        Map<String, Object> body = response.getBody();
        assertEquals(true, body.get("success"));
        assertEquals("Question updated successfully", body.get("message"));
    }

    @Test
    public void testEditQuizQuestionNotFound() {
        // Arrange
        when(questionsService.editQuiz(any(Question.class))).thenReturn(false);

        // Act
        var response = quizController.editQuizQuestion(999, "Q", "A,B", "A");

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        Map<String, Object> body = response.getBody();
        assertEquals(false, body.get("success"));
        assertEquals("Question not found", body.get("message"));
    }

    @Test
    public void testDeleteQuizQuestion() {
        // Arrange
        when(questionsService.deleteQuiz(1)).thenReturn(true);

        // Act
        var response = quizController.deleteQuizQuestion(1);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        Map<String, Object> body = response.getBody();
        assertEquals(true, body.get("success"));
        assertEquals("Question deleted successfully", body.get("message"));
    }

    @Test
    public void testDeleteQuizQuestionNotFound() {
        // Arrange
        when(questionsService.deleteQuiz(999)).thenReturn(false);

        // Act
        var response = quizController.deleteQuizQuestion(999);

        // Assert
        assertNotNull(response);
        assertNotNull(response.getBody());
        Map<String, Object> body = response.getBody();
        assertEquals(false, body.get("success"));
        assertEquals("Question not found", body.get("message"));
    }
}
