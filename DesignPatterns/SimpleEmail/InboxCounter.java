package com.example.email;

/**
 * Counts unread emails - like the badge on email apps
 */
public class InboxCounter implements Observer {
    private int count = 0;
    private int urgerntCount = 0;

    @Override
    public void update(String sender, String message, boolean isUrgent) {
        count++;
        if (isUrgent) {
            urgerntCount++;
        }
        System.out.println("📬 Inbox: " + count + " unread emails");
        System.out.println("🔴 Urgent: " + urgerntCount);
    }

    @Override
    public String getName() {
        return "Inbox Counter";
    }

    public int getCount() {
        return count;
    }

    public int getUrgentCount() {
        return urgerntCount;
    }
}