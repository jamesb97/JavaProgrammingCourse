package com.example.quiz.service;

import com.example.quiz.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class QuizUserDetailsService {

    private final Map<String, User> users = new HashMap<>();
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Returns UserDetails object when provided with username and password
    public UserDetails loadUserByUsername(String username, String password) throws UsernameNotFoundException {
        User user = users.get(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(user.getRole())
                .build();
    }

    // Registers a new user with username, password, email, and role
    public boolean registerUser(String username, String password, String email, String role) {
        if (users.containsKey(username)) {
            return false;
        }

        String encryptedPassword = passwordEncoder.encode(password);
        User newUser = new User(username, email, encryptedPassword, role);
        users.put(username, newUser);

        return true;
    }
}
