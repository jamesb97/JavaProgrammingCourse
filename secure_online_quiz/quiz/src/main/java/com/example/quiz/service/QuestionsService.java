package com.example.quiz.service;

import com.example.quiz.model.Question;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class for managing quiz questions.
 * Uses a HashMap to store questions in memory within the service layer.
 */
@Service
public class QuestionsService {

    // Data structure to store questions - HashMap with question ID as key
    private final Map<Integer, Question> questions = new HashMap<>();
    private int nextQuestionId = 1;

    /**
     * Loads all quizzes (questions) from the collection.
     * Returns a List containing all stored questions.
     *
     * @return List of all Question objects
     */
    public List<Question> loadQuizzes() {
        return new ArrayList<>(questions.values());
    }

    /**
     * Adds a new quiz (question) to the collection.
     * If the question ID already exists, it updates the corresponding question.
     *
     * @param question the Question object to add
     * @return true if added successfully
     */
    public boolean addQuiz(Question question) {
        if (question == null) {
            return false;
        }

        // Add or update the question in the HashMap
        questions.put(question.getId(), question);

        // Update nextQuestionId if needed
        if (question.getId() >= nextQuestionId) {
            nextQuestionId = question.getId() + 1;
        }

        return true;
    }

    /**
     * Edits an existing quiz (question) in the collection.
     * Updates the question with the matching ID.
     *
     * @param question the Question object with updated information
     * @return true if the question was updated, false if question ID not found
     */
    public boolean editQuiz(Question question) {
        if (question == null || !questions.containsKey(question.getId())) {
            return false; // Question not found
        }

        // Update the existing question in the HashMap
        questions.put(question.getId(), question);

        return true;
    }

    /**
     * Deletes a quiz (question) from the collection by its ID.
     * Removes the corresponding question from the HashMap.
     *
     * @param questionId the ID of the question to delete
     * @return true if the question was deleted, false if question ID not found
     */
    public boolean deleteQuiz(int questionId) {
        // Check if question exists and remove it
        if (questions.containsKey(questionId)) {
            questions.remove(questionId);
            return true;
        }

        return false; // Question not found
    }

    /**
     * Helper method to get a question by ID.
     *
     * @param questionId the ID of the question to retrieve
     * @return the Question object or null if not found
     */
    public Question getQuestionById(int questionId) {
        return questions.get(questionId);
    }

    /**
     * Helper method to get the total number of questions.
     *
     * @return the number of questions in the collection
     */
    public int getTotalQuestionCount() {
        return questions.size();
    }
}
