package com.example.email;

/**
 * Main application demonstrating Observer Pattern
 */
public class EmailApp {
    public static void main(String[] args) {
        System.out.println("=== Email Observer Pattern Demo ===");

        // Create email system
        EmailSystem emailSystem = new EmailSystem();

        // Create and register observers
        InboxCounter counter = new InboxCounter();
        PopupNotifier popup = new PopupNotifier();

        emailSystem.addObserver(counter);
        emailSystem.addObserver(popup);

        System.out.println("\nObservers registered: " + emailSystem.getObserverCount());

        // Simulate receiving emails
        emailSystem.receiveEmail("boss@company.com", "Meeting at 3 PM today", true);
        emailSystem.receiveEmail("friend@gmail.com", "Want to grab lunch?", false);

        emailSystem.removeObserver(popup);
        System.out.println("\nObservers after unsubscribe: " + emailSystem.getObserverCount());
        emailSystem.receiveEmail("news@daily.com", "Top stories for your morning brief");

        System.out.println("\n=== Results ===");
        System.out.println("Total unread: " + counter.getCount());
        System.out.println("Urgent unread: " + counter.getUrgentCount());
        System.out.println("Demo complete!");
    }
}