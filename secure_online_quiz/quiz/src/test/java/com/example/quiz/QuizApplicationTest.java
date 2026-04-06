package com.example.quiz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuizApplicationTest {

    @Test
    public void contextLoads() {
        // This test verifies that the Spring context loads successfully
        assertDoesNotThrow(() -> {
            // If the context fails to load, this test would fail
        });
    }

    @Test
    public void mainMethodExists() {
        // Verify the main method exists and is executable
        try {
            QuizApplication.class.getMethod("main", String[].class);
        } catch (NoSuchMethodException e) {
            fail("Main method not found in QuizApplication");
        }
    }
}
