package com.example.quiz.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class QuestionTest {

    @Test
    public void testQuestionConstructorWithAllParameters() {
        // Arrange
        ArrayList<String> options = new ArrayList<>();
        options.add("Option A");
        options.add("Option B");

        // Act
        Question question = new Question(1, "What is 2+2?", options, "Option A");

        // Assert
        assertEquals(1, question.getId());
        assertEquals("What is 2+2?", question.getQuestionText());
        assertEquals(options, question.getOptions());
        assertEquals("Option A", question.getCorrectAnswer());
    }

    @Test
    public void testQuestionDefaultConstructor() {
        // Act
        Question question = new Question();

        // Assert
        assertNull(question.getQuestionText());
        assertNull(question.getOptions());
        assertNull(question.getCorrectAnswer());
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        Question question = new Question();
        ArrayList<String> options = new ArrayList<>();
        options.add("A");
        options.add("B");

        // Act
        question.setId(5);
        question.setQuestionText("Test Question");
        question.setOptions(options);
        question.setCorrectAnswer("A");

        // Assert
        assertEquals(5, question.getId());
        assertEquals("Test Question", question.getQuestionText());
        assertEquals(options, question.getOptions());
        assertEquals("A", question.getCorrectAnswer());
    }

    @Test
    public void testIsCorrectAnswerWithValidAnswer() {
        // Arrange
        Question question = new Question(1, "Question", new ArrayList<>(), "CorrectAnswer");

        // Act
        boolean result = question.isCorrectAnswer("CorrectAnswer");

        // Assert
        assertTrue(result);
    }

    @Test
    public void testIsCorrectAnswerWithInvalidAnswer() {
        // Arrange
        Question question = new Question(1, "Question", new ArrayList<>(), "CorrectAnswer");

        // Act
        boolean result = question.isCorrectAnswer("WrongAnswer");

        // Assert
        assertFalse(result);
    }

    @Test
    public void testIsCorrectAnswerWithNullCorrectAnswer() {
        // Arrange
        Question question = new Question(1, "Question", new ArrayList<>(), null);

        // Act
        boolean result = question.isCorrectAnswer("AnyAnswer");

        // Assert
        assertFalse(result);
    }

    @Test
    public void testIsCorrectAnswerWithNullParameter() {
        // Arrange
        Question question = new Question(1, "Question", new ArrayList<>(), "CorrectAnswer");

        // Act
        boolean result = question.isCorrectAnswer(null);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testQuestionToString() {
        // Arrange
        ArrayList<String> options = new ArrayList<>();
        options.add("A");
        options.add("B");
        Question question = new Question(1, "Test Question", options, "A");

        // Act
        String result = question.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("Question"));
        assertTrue(result.contains("1"));
        assertTrue(result.contains("Test Question"));
    }
}
