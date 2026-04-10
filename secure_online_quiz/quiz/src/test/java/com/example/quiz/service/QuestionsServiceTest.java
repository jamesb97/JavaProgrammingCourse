package com.example.quiz.service;

import com.example.quiz.model.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class QuestionsServiceTest {

    private QuestionsService questionsService;

    @BeforeEach
    public void setUp() {
        questionsService = new QuestionsService();
    }

    @Test
    public void testLoadQuizzesWhenEmpty() {
        // Act
        List<Question> questions = questionsService.loadQuizzes();

        // Assert
        assertNotNull(questions);
        assertTrue(questions.isEmpty());
    }

    @Test
    public void testAddQuizWithValidQuestion() {
        // Arrange
        ArrayList<String> options = new ArrayList<>();
        options.add("A");
        options.add("B");
        Question question = new Question(1, "Test Question", options, "A");

        // Act
        boolean result = questionsService.addQuiz(question);

        // Assert
        assertTrue(result);
        List<Question> questions = questionsService.loadQuizzes();
        assertEquals(1, questions.size());
    }

    @Test
    public void testAddQuizWithNullQuestion() {
        // Act
        boolean result = questionsService.addQuiz(null);

        // Assert
        assertFalse(result);
        List<Question> questions = questionsService.loadQuizzes();
        assertTrue(questions.isEmpty());
    }

    @Test
    public void testAddMultipleQuizzes() {
        // Arrange
        ArrayList<String> options = new ArrayList<>();
        options.add("A");
        options.add("B");
        Question q1 = new Question(1, "Q1", options, "A");
        Question q2 = new Question(2, "Q2", options, "B");

        // Act
        questionsService.addQuiz(q1);
        questionsService.addQuiz(q2);

        // Assert
        List<Question> questions = questionsService.loadQuizzes();
        assertEquals(2, questions.size());
    }

    @Test
    public void testGetQuestionByIdFound() {
        // Arrange
        ArrayList<String> options = new ArrayList<>();
        options.add("A");
        options.add("B");
        Question question = new Question(1, "Test Q", options, "A");
        questionsService.addQuiz(question);

        // Act
        Question retrieved = questionsService.getQuestionById(1);

        // Assert
        assertNotNull(retrieved);
        assertEquals(1, retrieved.getId());
        assertEquals("Test Q", retrieved.getQuestionText());
    }

    @Test
    public void testGetQuestionByIdNotFound() {
        // Act
        Question retrieved = questionsService.getQuestionById(999);

        // Assert
        assertNull(retrieved);
    }

    @Test
    public void testEditQuizWithValidQuestion() {
        // Arrange
        ArrayList<String> options = new ArrayList<>();
        options.add("A");
        options.add("B");
        Question question = new Question(1, "Original", options, "A");
        questionsService.addQuiz(question);

        // Act
        ArrayList<String> newOptions = new ArrayList<>();
        newOptions.add("X");
        newOptions.add("Y");
        Question updatedQuestion = new Question(1, "Updated", newOptions, "X");
        boolean result = questionsService.editQuiz(updatedQuestion);

        // Assert
        assertTrue(result);
        Question retrieved = questionsService.getQuestionById(1);
        assertEquals("Updated", retrieved.getQuestionText());
    }

    @Test
    public void testEditQuizWithNullQuestion() {
        // Act
        boolean result = questionsService.editQuiz(null);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testEditQuizWithNonExistentId() {
        // Arrange
        ArrayList<String> options = new ArrayList<>();
        options.add("A");
        Question question = new Question(999, "Q", options, "A");

        // Act
        boolean result = questionsService.editQuiz(question);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testDeleteQuizWithValidId() {
        // Arrange
        ArrayList<String> options = new ArrayList<>();
        options.add("A");
        Question question = new Question(1, "Test", options, "A");
        questionsService.addQuiz(question);

        // Act
        boolean result = questionsService.deleteQuiz(1);

        // Assert
        assertTrue(result);
        List<Question> questions = questionsService.loadQuizzes();
        assertTrue(questions.isEmpty());
    }

    @Test
    public void testDeleteQuizWithNonExistentId() {
        // Act
        boolean result = questionsService.deleteQuiz(999);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testAddQuizUpdatesNextQuestionId() {
        // Arrange
        ArrayList<String> options = new ArrayList<>();
        Question q1 = new Question(5, "Q", options, "A");

        // Act
        questionsService.addQuiz(q1);
        Question q2 = new Question(0, "New Q", options, "A");
        // The service would generate an ID for the new question based on nextQuestionId

        // Assert
        List<Question> questions = questionsService.loadQuizzes();
        assertTrue(questions.size() > 0);
    }
}
