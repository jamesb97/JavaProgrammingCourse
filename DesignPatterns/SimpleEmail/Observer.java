package com.example.email;

/**
 * Observer interface - defines what observers must implement
 */
public interface Observer {
    // void update(String sender, String message, boolean isUrgent);
    void update(Email email);

    String getName();
}