package com.example.email;

import java.util.ArrayList;
import java.util.List;

/**
 * EmailSystem manages observers and sends notifications
 */
public class EmailSystem {
    // private final List<Observer> observers = new ArrayList<>();

    // public void addObserver(Observer observer) {
    // observers.add(observer);
    // System.out.println("Added: " + observer.getName());
    // }

    // public void receiveEmail(String sender, String message) {
    // String urgentText = message.toLowerCase().contains("urgent") ? "URGENT" :
    // "normal";
    // System.out.println("\n📧 New email from: " + sender);

    // Notify all observers
    // for (Observer observer : observers) {
    // observer.update(sender, message, isUrgentEmail(sender, message));
    // }
    // }

    // public int getObserverCount() {
    // return observers.size();
    // }
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Added: " + observer.getName());
    }

    public void removeObserver(Observer observer) {
        if (observers.remove(observer)) {
            System.out.println("Removed: " + observer.getName());
        }
    }

    public void receiveEmail(String sender, String message) {
        Email email = new Email(sender, message);
        System.out.println("\n📧 New email from: " + email.getSender());

        // Notify all observers while isolating failures in individual observers.
        for (Observer observer : observers) {
            try {
                observer.update(email);
            } catch (RuntimeException e) {
                System.out.println("⚠️ Observer failed: " + observer.getName() + " - " + e.getMessage());
            }
        }
    }

    public int getObserverCount() {
        return observers.size();
    }
}