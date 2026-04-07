package com.example.quiz.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;

public class WebSecurityConfigTest {

    @Test
    public void testPasswordEncoderBeanCreation() {
        // Arrange
        WebSecurityConfig config = new WebSecurityConfig(new CustomAuthenticationSuccessHandler());

        // Act
        PasswordEncoder encoder = config.passwordEncoder();

        // Assert
        assertNotNull(encoder);
        assertTrue(encoder instanceof BCryptPasswordEncoder);
    }

    @Test
    public void testPasswordEncoderEncoding() {
        // Arrange
        WebSecurityConfig config = new WebSecurityConfig(new CustomAuthenticationSuccessHandler());
        PasswordEncoder encoder = config.passwordEncoder();
        String plainPassword = "myPassword123";

        // Act
        String encodedPassword = encoder.encode(plainPassword);

        // Assert
        assertNotNull(encodedPassword);
        assertNotEquals(plainPassword, encodedPassword);
        assertTrue(encoder.matches(plainPassword, encodedPassword));
    }

    @Test
    public void testPasswordEncoderDoesNotMatchWrongPassword() {
        // Arrange
        WebSecurityConfig config = new WebSecurityConfig(new CustomAuthenticationSuccessHandler());
        PasswordEncoder encoder = config.passwordEncoder();
        String password1 = "password123";
        String password2 = "password456";
        String encoded = encoder.encode(password1);

        // Act
        boolean matches = encoder.matches(password2, encoded);

        // Assert
        assertFalse(matches);
    }

    @Test
    public void testConstructorWithSuccessHandler() {
        // Arrange
        CustomAuthenticationSuccessHandler handler = new CustomAuthenticationSuccessHandler();

        // Act
        WebSecurityConfig config = new WebSecurityConfig(handler);

        // Assert
        assertNotNull(config);
    }
}
