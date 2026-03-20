package com.example.quiz.service;

import com.example.quiz.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Service class for managing user authentication and registration.
 * Uses a HashMap to store user details in memory.
 */
@Service
public class QuizUserDetailsService {

    // Data structure to store user details - HashMap with username as key
    private final Map<String, User> users = new HashMap<>();
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * Loads a user by username and validates the password.
     * Returns a UserDetails object if the credentials are valid.
     *
     * @param username the username to look up
     * @param password the password to validate
     * @return UserDetails object containing user information
     * @throws UsernameNotFoundException if user is not found or password is invalid
     */
    public UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException {
        // Check if user exists in the data structure
        User user = users.get(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        // Validate password
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        // Return UserDetails object with user information
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole())
                .build();
    }

    /**
     * Registers a new user with the provided details.
     * Encrypts the password before storing.
     *
     * @param username the username for the new user
     * @param password the plain text password (will be encrypted)
     * @param email    the email address of the user
     * @param role     the role assigned to the user
     * @return true if registration is successful, false if user already exists
     */
    public boolean registerUser(String username, String password, String email, String role) {
        // Check if user already exists
        if (users.containsKey(username)) {
            return false; // User already exists
        }

        // Encrypt password before storing
        String encryptedPassword = passwordEncoder.encode(password);

        // Create new user with encrypted password
        User newUser = new User(username, email, encryptedPassword, role);

        // Store user in the data structure
        users.put(username, newUser);

        return true; // Registration successful
    }
}