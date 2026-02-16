package ObjectOriented.EventSchedulerApplication.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventScheduler {
    private List<Event> events;

    public EventScheduler() {
        events = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void displayEvents() {
        if (events.isEmpty()) {
            System.out.println("No events scheduled.");
            return;
        }
        for (Event event : events) {
            System.out.println(event);
        }
    }

    public static void main(String[] args) {
        EventScheduler scheduler = new EventScheduler();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add Event");
            System.out.println("2. Display Events");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter event name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter event date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    scheduler.addEvent(new Event(name, date));
                    break;
                case 2:
                    scheduler.displayEvents();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Simple inner Event class to represent events and allow instantiation
    private static class Event {
        private final String name;
        private final String date;

        public Event(String name, String date) {
            this.name = name;
            this.date = date;
        }

        @Override
        public String toString() {
            return name + " on " + date;
        }
    }
}