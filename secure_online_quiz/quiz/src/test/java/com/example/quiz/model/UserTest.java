package com.example.quiz.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserDefaultConstructor() {
        // Act
        User user = new User();

        // Assert
        assertNull(user.getUsername());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getRole());
    }

    @Test
    public void testUserConstructorWithUsernameAndPassword() {
        // Act
        User user = new User("john_doe", "password123");

        // Assert
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertNull(user.getEmail());
        assertNull(user.getRole());
    }

    @Test
    public void testUserConstructorWithAllParameters() {
        // Act
        User user = new User("john_doe", "john@example.com", "password123", "ADMIN");

        // Assert
        assertEquals("john_doe", user.getUsername());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("ADMIN", user.getRole());
    }

    @Test
    public void testSettersAndGetters() {
        // Arrange
        User user = new User();

        // Act
        user.setUsername("test_user");
        user.setEmail("test@example.com");
        user.setPassword("secret_password");
        user.setRole("USER");

        // Assert
        assertEquals("test_user", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("secret_password", user.getPassword());
        assertEquals("USER", user.getRole());
    }

    @Test
    public void testUserToString() {
        // Arrange
        User user = new User("alice", "alice@example.com", "pass", "ROLE_USER");

        // Act
        String result = user.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("User"));
        assertTrue(result.contains("alice"));
        assertTrue(result.contains("alice@example.com"));
        assertTrue(result.contains("ROLE_USER"));
    }

    @Test
    public void testUserToStringWithNullFields() {
        // Arrange
        User user = new User();

        // Act
        String result = user.toString();

        // Assert
        assertNotNull(result);
        assertTrue(result.contains("User"));
    }

    @Test
    public void testMultipleUserInstances() {
        // Arrange & Act
        User user1 = new User("user1", "pass1");
        User user2 = new User("user2", "pass2");

        // Assert
        assertNotEquals(user1.getUsername(), user2.getUsername());
        assertNotEquals(user1.getPassword(), user2.getPassword());
    }
}
