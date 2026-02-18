import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;;

public class MoodTracking {
    public static void main(String s[]) {
        // Create an arrayList that can store Mood objects
        java.util.ArrayList<Mood> moodList = new java.util.ArrayList<>();
        // Import scanner from the util package and create an object of it to read from
        // the console in the MoodTracker class
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        // Ask the user to enter a mood and store it in a variable
        System.out.println("Enter a mood: ");
        String moodName = scanner.nextLine();
        // Ask the user to enter a date and store it in a variable
        System.out.println("Enter a date (YYYY-MM-DD): ");
        String dateString = scanner.nextLine();
        // Ask the user to enter a time and store it in a variable
        System.out.println("Enter a time (HH:MM): ");
        String timeString = scanner.nextLine();
        // Ask the user to enter any notes and store it in a variable
        System.out.println("Enter any notes: ");
        String notes = scanner.nextLine();
        while (true) {
            System.out.println("Press 'a' to add mood\n" +
                    "'d' to delete mood(s)\n" +
                    "'e' to edit mood\n" +
                    "'s' to search for moods\n" +
                    "'M' to get all moods\n" +
                    "'w' to write the moods to a file\n" +
                    "Type 'Exit' to exit");
            String menuOption = scanner.nextLine();
            switch (menuOption) {
                case "a": // add code to add mood
                    continue;
                case "d": // add code to delete mood
                    continue;
                case "e": // add code to edit mood
                    continue;
                case "s": // add code to search mood
                    continue;
                case "M": // add code to get all moods
                    continue;
                case "w": // add code to write mood to a file
                    continue;
                case "Exit":
                    System.out.println("Thank you for using the MoodTracker. Goodbye!");
                    break;
                default:
                    System.out.println("Not a valid input!");
                    continue;
            }
        }
    }

    public static boolean isMoodValid(Mood mood, ArrayList<Mood> moodsList) throws InvalidMoodException {
        for (Mood tempMood : moodsList) {
            if (tempMood.equals(mood)) {
                throw new InvalidMoodException();
            }
        }
        return true;
    }

    public static void addMood(java.util.Scanner scanner, ArrayList<Mood> moodsList) {
        System.out.println("Enter the mood name");
        String moodName = scanner.nextLine();
        System.out.println("Are you tracking the mood for a current day? y/n");
        String isForCurrentDate = scanner.nextLine();
        Mood moodToAdd = null;
        if (isForCurrentDate.equalsIgnoreCase("n")) {
            try {
                System.out.println("Input the date in MM/dd/yyyy format:");
                String moodDateStr = scanner.nextLine();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDate moodDate = LocalDate.parse(moodDateStr, dateFormatter);
                System.out.println("Input the time in HH:mm:ss format:");
                String moodTimeStr = scanner.nextLine();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime moodTime = LocalTime.parse(moodTimeStr, timeFormatter);
                System.out.println("Add notes about this mood");
                String moodNotes = scanner.nextLine();
                if (moodNotes.strip().equalsIgnoreCase("")) {
                    moodToAdd = new Mood(moodName, moodDate, moodTime, moodNotes);
                } else {
                    moodToAdd = new Mood(moodName, moodDate, moodTime, moodNotes);
                }
            } catch (DateTimeParseException dfe) {
                System.out.println("Incorrect format of date or time. Cannot create mood.\n" + dfe);
                // continue;
            }
        } else {
            System.out.println("Add notes about this mood");
            String moodNotes = scanner.nextLine();
            if (moodNotes.strip().equalsIgnoreCase("")) {
                moodToAdd = new Mood(moodName, null, null, moodNotes);
            } else {
                moodToAdd = new Mood(moodName, null, null, moodNotes);
            }
        }
        try {
            boolean isValid = isMoodValid(moodToAdd, moodsList);
            if (isValid) {
                moodsList.add(moodToAdd);
                System.out.println("The mood has been added to the tracker");
            }
        } catch (InvalidMoodException ime) {
            System.out.println("The mood is not valid");
        }
    }

    public static boolean deleteMoods(LocalDate moodDate, ArrayList<Mood> moodsList) {
        boolean removed = false;
        for (Mood tempMood : moodsList) {
            if (tempMood.getDate().equals(moodDate)) {
                moodsList.remove(tempMood);
                removed = true;
            }
        }
        return removed;
    }

    public static void deleteMoodMenu(java.util.Scanner scanner, ArrayList<Mood> moodsList) {
        System.out.println("Enter '1' to delete all moods by date\n" +
                "Enter '2' to delete a specific mood");
        String deleteVariant = scanner.nextLine();
        if (deleteVariant.equals("1")) {
            try {
                System.out.println("Input the date in MM/dd/yyyy format:");
                String moodDateStr = scanner.nextLine();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDate moodDate = LocalDate.parse(moodDateStr, dateFormatter);
                boolean areMoodsDeleted = deleteMoods(moodDate, moodsList);
                if (areMoodsDeleted) {
                    System.out.println("The moods have been deleted");
                } else {
                    System.out.println("No matching moods found");
                }
            } catch (DateTimeParseException dfe) {
                System.out.println("Incorrect format of date. Cannot delete mood.");
                // continue;
            }
        } else if (deleteVariant.equals("2")) {
            try {
                System.out.println("Enter the mood name");
                String moodName = scanner.nextLine();
                System.out.println("Input the date in MM/dd/yyyy format:");
                String moodDateStr = scanner.nextLine();
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                LocalDate moodDate = LocalDate.parse(moodDateStr, dateFormatter);
                System.out.println("Input the time in HH:mm:ss format:");
                String moodTimeStr = scanner.nextLine();
                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime moodTime = LocalTime.parse(moodTimeStr, timeFormatter);
                Mood delMood = new Mood(moodName, moodDate, moodTime, moodTimeStr);
                boolean isMoodDeleted = deleteMood(delMood, moodsList);
                if (isMoodDeleted) {
                    System.out.println("The mood has been deleted");
                } else {
                    System.out.println("No matching mood found");
                }
            } catch (DateTimeParseException dfe) {
                System.out.println("Incorrect format of date or time. Cannot delete mood.");
                // continue;
            }
        }
    }

    private static boolean deleteMood(Mood delMood, ArrayList<Mood> moodsList) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteMood'");
    }

    public static boolean editMood(Mood moodToEdit, ArrayList<Mood> moodsList) {
        for (Mood tempMood : moodsList) {
            if (tempMood.equals(moodToEdit)) {
                tempMood.setNotes(moodToEdit.getNotes());
                return true;
            }
        }
        return false;
    }

    public static void editMoodMenu(java.util.Scanner scanner, ArrayList<Mood> moodsList) {
        Mood moodToEdit = null;
        try {
            System.out.println("Enter the mood name");
            String moodName = scanner.nextLine();
            System.out.println("Input the date in MM/dd/yyyy format:");
            String moodDateStr = scanner.nextLine();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            LocalDate moodDate = LocalDate.parse(moodDateStr, dateFormatter);
            System.out.println("Input the time in HH:mm:ss format:");
            String moodTimeStr = scanner.nextLine();
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime moodTime = LocalTime.parse(moodTimeStr, timeFormatter);
            System.out.println("Add new notes about this mood");
            String moodNotes = scanner.nextLine();
            if (moodNotes.strip().equalsIgnoreCase("")) {
                System.out.println("No notes entered");
                // continue;
            } else {
                moodToEdit = new Mood(moodName, moodDate, moodTime, moodNotes);
                boolean isMoodEdited = editMood(moodToEdit, moodsList);
                if (isMoodEdited) {
                    System.out.println("The mood has been successfully edited");
                } else {
                    System.out.println("No matching mood could be found");
                }
            }
        } catch (DateTimeParseException dfe) {
            System.out.println("Incorrect format of date or time. Cannot create mood.");
        }
    }

    public static void displayAllMoods(ArrayList<Mood> moodsList) {
        for (Mood moodObj : moodsList) {
            System.out.println(moodObj);
        }
    }

    public static void writeMoodsToFile(ArrayList<Mood> moodsList) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Moods.txt"))) {
            for (Mood mood : moodsList) {
                writer.println(mood + "\n\n");
            }
            System.out.println("The entries are written to a file");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
