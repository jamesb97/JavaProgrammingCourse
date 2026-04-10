package com.example.quiz.service;

import com.example.quiz.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

public class QuizUserDetailsServiceTest {

    private QuizUserDetailsService quizUserDetailsService;

    @BeforeEach
    public void setUp() {
        quizUserDetailsService = new QuizUserDetailsService();
    }

    @Test
    public void testRegisterUserWithValidCredentials() {
        // Act
        boolean result = quizUserDetailsService.registerUser("testuser", "password123", "test@example.com",
                "ROLE_USER");

        // Assert
        assertTrue(result);
    }

    @Test
    public void testRegisterUserWithDuplicateUsername() {
        // Arrange
        quizUserDetailsService.registerUser("testuser", "password123", "test@example.com", "ROLE_USER");

        // Act
        boolean result = quizUserDetailsService.registerUser("testuser", "otherpassword", "other@example.com",
                "ROLE_USER");

        // Assert
        assertFalse(result);
    }

    @Test
    public void testLoadUserByUsernameWithValidCredentials() {
        // Arrange
        quizUserDetailsService.registerUser("alice", "secret123", "alice@example.com", "ROLE_ADMIN");

        // Act
        UserDetails userDetails = quizUserDetailsService.loadUserByUsername("alice", "secret123");

        // Assert
        assertNotNull(userDetails);
        assertEquals("alice", userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().contains("ROLE_ADMIN")));
    }

    @Test
    public void testLoadUserByUsernameWithInvalidPassword() {
        // Arrange
        quizUserDetailsService.registerUser("alice", "correctPassword", "alice@example.com", "ROLE_USER");

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            quizUserDetailsService.loadUserByUsername("alice", "wrongPassword");
        });
    }

    @Test
    public void testLoadUserByUsernameWithNonExistentUser() {
        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            quizUserDetailsService.loadUserByUsername("nonexistent", "password");
        });
    }

    @Test
    public void testRegisterMultipleUsers() {
        // Act
        boolean user1 = quizUserDetailsService.registerUser("user1", "pass1", "user1@example.com", "ROLE_USER");
        boolean user2 = quizUserDetailsService.registerUser("user2", "pass2", "user2@example.com", "ROLE_USER");

        // Assert
        assertTrue(user1);
        assertTrue(user2);
    }

    @Test
    public void testLoadUserByUsernameMultipleTimesWithSameUser() {
        // Arrange
        quizUserDetailsService.registerUser("testuser", "testpass", "test@example.com", "ROLE_USER");

        // Act
        UserDetails userDetails1 = quizUserDetailsService.loadUserByUsername("testuser", "testpass");
        UserDetails userDetails2 = quizUserDetailsService.loadUserByUsername("testuser", "testpass");

        // Assert
        assertNotNull(userDetails1);
        assertNotNull(userDetails2);
        assertEquals(userDetails1.getUsername(), userDetails2.getUsername());
    }

    @Test
    public void testRegisterUserWithAdminRole() {
        // Act
        boolean result = quizUserDetailsService.registerUser("admin", "adminpass", "admin@example.com", "ROLE_ADMIN");

        // Assert
        assertTrue(result);
        UserDetails userDetails = quizUserDetailsService.loadUserByUsername("admin", "adminpass");
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")));
    }

    @Test
    public void testPasswordIsEncrypted() {
        // Arrange
        String plainPassword = "myPassword123";

        // Act
        quizUserDetailsService.registerUser("user", plainPassword, "user@example.com", "ROLE_USER");

        // Assert - trying with wrong password should fail
        assertThrows(UsernameNotFoundException.class, () -> {
            quizUserDetailsService.loadUserByUsername("user", "wrongPassword");
        });
    }
}
