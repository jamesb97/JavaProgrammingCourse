import java.time.LocalDate;
import java.time.LocalTime;
import java.io.FileWriter;
import java.io.IOException;

public class MoodTracker {
    public static void main(String s[]) {
        // Create an arrayList that can store Mood objects
        java.util.ArrayList<Mood> moodList = new java.util.ArrayList<>();
        // Import scanner from the util package and create an object of it to read from
        // the console in the MoodTracker class
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        // Create an infinite loop that runs until the user decides to exit
        while (true) {
            System.out.println("\n=== Mood Tracker ===");
            System.out.println("1. Add a mood");
            System.out.println("2. Edit mood notes");
            System.out.println("3. Delete a mood");
            System.out.println("4. Search moods");
            System.out.println("5. View all moods");
            System.out.println("6. Save moods to file");
            System.out.println("7. Exit");
            System.out.println("Choose an option (1-7): ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                addMood(moodList, scanner);
            } else if (choice.equals("2")) {
                editMoodNotes(moodList, scanner);
            } else if (choice.equals("3")) {
                deleteMood(moodList, scanner);
            } else if (choice.equals("4")) {
                searchMoods(moodList, scanner);
            } else if (choice.equals("5")) {
                viewAllMoods(moodList);
            } else if (choice.equals("6")) {
                saveMoodsToFile(moodList);
            } else if (choice.equals("7")) {
                System.out.println("Thank you for using Mood Tracker!");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // Method to add a mood
    private static void addMood(java.util.ArrayList<Mood> moodList, java.util.Scanner scanner) {
        try {
            // Ask the user to enter a mood and store it in a variable
            System.out.println("Enter a mood: ");
            String moodName = scanner.nextLine().trim();

            if (moodName.isEmpty()) {
                System.out.println("Mood cannot be empty. Please try again.");
                return;
            }

            // Ask the user to enter a date and store it in a variable
            System.out.println("Enter a date (YYYY-MM-DD): ");
            String dateString = scanner.nextLine().trim();

            // Ask the user to enter a time and store it in a variable
            System.out.println("Enter a time (HH:MM): ");
            String timeString = scanner.nextLine().trim();

            LocalDate date = LocalDate.parse(dateString);
            LocalTime time = LocalTime.parse(timeString);

            // Check if a mood already exists for this date and time
            if (moodExistsForDateTime(moodList, date, time)) {
                System.out.println("A mood already exists for this date and time. The mood cannot be changed.");
                return;
            }

            // Ask the user to enter any notes and store it in a variable
            System.out.println("Enter any notes: ");
            String notes = scanner.nextLine();

            // Create a Mood object using the variables and add it to the arrayList
            Mood mood = new Mood(moodName, date, time, notes);
            moodList.add(mood);
            System.out.println("Mood successfully recorded!");

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid date or time format. Please use YYYY-MM-DD for date and HH:MM for time.");
        }
    }

    // Method to edit mood notes
    private static void editMoodNotes(java.util.ArrayList<Mood> moodList, java.util.Scanner scanner) {
        try {
            System.out.println("Enter the mood name: ");
            String moodName = scanner.nextLine().trim();

            System.out.println("Enter a date (YYYY-MM-DD): ");
            String dateString = scanner.nextLine().trim();

            System.out.println("Enter a time (HH:MM): ");
            String timeString = scanner.nextLine().trim();

            LocalDate date = LocalDate.parse(dateString);
            LocalTime time = LocalTime.parse(timeString);

            // Find the mood with matching name, date, and time
            Mood moodToEdit = null;
            for (Mood mood : moodList) {
                if (mood.getMoodName().equals(moodName) && 
                    mood.getDate().equals(date) && 
                    mood.getTime().equals(time)) {
                    moodToEdit = mood;
                    break;
                }
            }

            if (moodToEdit == null) {
                System.out.println("No mood found with the given name, date, and time.");
                return;
            }

            System.out.println("Current notes: " + moodToEdit.getNotes());
            System.out.println("Enter new notes: ");
            String newNotes = scanner.nextLine();

            moodToEdit.setNotes(newNotes);
            System.out.println("Notes successfully updated!");

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid date or time format. Please use YYYY-MM-DD for date and HH:MM for time.");
        }
    }

    // Method to delete a mood
    private static void deleteMood(java.util.ArrayList<Mood> moodList, java.util.Scanner scanner) {
        System.out.println("\n=== Delete Options ===");
        System.out.println("1. Delete all moods by date");
        System.out.println("2. Delete a specific mood by name, date, and time");
        System.out.println("Choose an option (1-2): ");
        String deleteChoice = scanner.nextLine().trim();

        if (deleteChoice.equals("1")) {
            deleteByDate(moodList, scanner);
        } else if (deleteChoice.equals("2")) {
            deleteByNameDateAndTime(moodList, scanner);
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    // Method to delete all moods by date
    private static void deleteByDate(java.util.ArrayList<Mood> moodList, java.util.Scanner scanner) {
        try {
            System.out.println("Enter a date (YYYY-MM-DD): ");
            String dateString = scanner.nextLine().trim();
            LocalDate date = LocalDate.parse(dateString);

            int initialSize = moodList.size();
            moodList.removeIf(mood -> mood.getDate().equals(date));
            int deletedCount = initialSize - moodList.size();

            if (deletedCount > 0) {
                System.out.println("Successfully deleted " + deletedCount + " mood(s) for " + date + ".");
            } else {
                System.out.println("No moods found for " + date + ".");
            }

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    // Method to delete a specific mood by name, date, and time
    private static void deleteByNameDateAndTime(java.util.ArrayList<Mood> moodList, java.util.Scanner scanner) {
        try {
            System.out.println("Enter the mood name: ");
            String moodName = scanner.nextLine().trim();

            System.out.println("Enter a date (YYYY-MM-DD): ");
            String dateString = scanner.nextLine().trim();

            System.out.println("Enter a time (HH:MM): ");
            String timeString = scanner.nextLine().trim();

            LocalDate date = LocalDate.parse(dateString);
            LocalTime time = LocalTime.parse(timeString);

            boolean found = moodList.removeIf(mood -> 
                mood.getMoodName().equals(moodName) && 
                mood.getDate().equals(date) && 
                mood.getTime().equals(time)
            );

            if (found) {
                System.out.println("Mood successfully deleted!");
            } else {
                System.out.println("No mood found with the given name, date, and time.");
            }

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid date or time format. Please use YYYY-MM-DD for date and HH:MM for time.");
        }
    }

    // Method to search moods
    private static void searchMoods(java.util.ArrayList<Mood> moodList, java.util.Scanner scanner) {
        System.out.println("\n=== Search Options ===");
        System.out.println("1. Get moods by date");
        System.out.println("2. Get a specific mood by name, date, and time");
        System.out.println("Choose an option (1-2): ");
        String searchChoice = scanner.nextLine().trim();

        if (searchChoice.equals("1")) {
            searchByDate(moodList, scanner);
        } else if (searchChoice.equals("2")) {
            searchByNameDateAndTime(moodList, scanner);
        } else {
            System.out.println("Invalid option. Please try again.");
        }
    }

    // Method to search moods by date
    private static void searchByDate(java.util.ArrayList<Mood> moodList, java.util.Scanner scanner) {
        try {
            System.out.println("Enter a date (YYYY-MM-DD): ");
            String dateString = scanner.nextLine().trim();
            LocalDate date = LocalDate.parse(dateString);

            java.util.ArrayList<Mood> moodsOnDate = new java.util.ArrayList<>();
            for (Mood mood : moodList) {
                if (mood.getDate().equals(date)) {
                    moodsOnDate.add(mood);
                }
            }

            if (moodsOnDate.isEmpty()) {
                System.out.println("No moods found for " + date + ".");
            } else {
                System.out.println("\n=== Moods for " + date + " ===");
                for (int i = 0; i < moodsOnDate.size(); i++) {
                    Mood mood = moodsOnDate.get(i);
                    System.out.println((i + 1) + ". Mood: " + mood.getMoodName() + 
                                     " | Time: " + mood.getTime() + 
                                     " | Notes: " + mood.getNotes());
                }
            }

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    // Method to search for a specific mood by name, date, and time
    private static void searchByNameDateAndTime(java.util.ArrayList<Mood> moodList, java.util.Scanner scanner) {
        try {
            System.out.println("Enter the mood name: ");
            String moodName = scanner.nextLine().trim();

            System.out.println("Enter a date (YYYY-MM-DD): ");
            String dateString = scanner.nextLine().trim();

            System.out.println("Enter a time (HH:MM): ");
            String timeString = scanner.nextLine().trim();

            LocalDate date = LocalDate.parse(dateString);
            LocalTime time = LocalTime.parse(timeString);

            // Find the mood with matching name, date, and time
            Mood foundMood = null;
            for (Mood mood : moodList) {
                if (mood.getMoodName().equals(moodName) && 
                    mood.getDate().equals(date) && 
                    mood.getTime().equals(time)) {
                    foundMood = mood;
                    break;
                }
            }

            if (foundMood == null) {
                System.out.println("No mood found with the given name, date, and time.");
            } else {
                System.out.println("\n=== Mood Details ===");
                System.out.println("Mood: " + foundMood.getMoodName());
                System.out.println("Date: " + foundMood.getDate());
                System.out.println("Time: " + foundMood.getTime());
                System.out.println("Notes: " + foundMood.getNotes());
            }

        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Invalid date or time format. Please use YYYY-MM-DD for date and HH:MM for time.");
        }
    }

    // Helper method to check if a mood exists for a given date and time
    private static boolean moodExistsForDateTime(java.util.ArrayList<Mood> moodList, LocalDate date, LocalTime time) {
        for (Mood mood : moodList) {
            if (mood.getDate().equals(date) && mood.getTime().equals(time)) {
                return true;
            }
        }
        return false;
    }

    // Method to view all moods
    private static void viewAllMoods(java.util.ArrayList<Mood> moodList) {
        if (moodList.isEmpty()) {
            System.out.println("No moods tracked yet.");
        } else {
            System.out.println("\n=== All Moods ===");
            for (int i = 0; i < moodList.size(); i++) {
                Mood mood = moodList.get(i);
                System.out.println((i + 1) + ". Mood: " + mood.getMoodName() + 
                                 " | Date: " + mood.getDate() + 
                                 " | Time: " + mood.getTime() + 
                                 " | Notes: " + mood.getNotes());
            }
        }
    }

    // Method to save all moods to a file
    private static void saveMoodsToFile(java.util.ArrayList<Mood> moodList) {
        try (FileWriter fileWriter = new FileWriter("moodtracker.txt")) {
            if (moodList.isEmpty()) {
                fileWriter.write("No moods tracked yet.\n");
                System.out.println("File saved. (No moods to save)");
                return;
            }

            fileWriter.write("=== Mood Tracker ===\n\n");
            for (int i = 0; i < moodList.size(); i++) {
                Mood mood = moodList.get(i);
                fileWriter.write((i + 1) + ". Mood: " + mood.getMoodName() + "\n");
                fileWriter.write("   Date: " + mood.getDate() + "\n");
                fileWriter.write("   Time: " + mood.getTime() + "\n");
                fileWriter.write("   Notes: " + mood.getNotes() + "\n\n");
            }

            System.out.println("Moods successfully saved to moodtracker.txt!");

        } catch (IOException e) {
            System.out.println("Error saving moods to file: " + e.getMessage());
        }
    }
}