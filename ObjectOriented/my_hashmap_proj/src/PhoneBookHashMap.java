package ObjectOriented.my_hashmap_proj.src;

import java.util.HashMap;
import java.util.Scanner;

public class PhoneBookHashMap {

    // Method to validate if the name contains only letters, spaces, hyphens, or
    // apostrophes
    private static boolean isNameValid(String name) {
        if (name.matches("^[a-zA-Z' -]+$") == false) {
            System.out.println("Invalid name!");
            return false;
        }
        return true;
    }

    // Method to validate if the phone number matches a specific format
    private static boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber.matches("\\+?\\d{1,4}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}") == false) {
            System.out.println("Invalid phone number!");
            return false;
        }
        return true;
    }

    public static void main(String s[]) {
        try {
            // Create a Scanner object to read user input
            Scanner scanner = new Scanner(System.in);
            // Create a HashMap to store names (keys) and phone numbers (values)
            HashMap<String, String> phonebook = new HashMap<>();

            // Infinite loop to keep the program running until the user chooses to exit
            while (true) {
                // Display the menu options to the user
                System.out.println(
                        "Press 1 to add an entry in the phonebook," +
                                "\n2 to view all the entries" +
                                "\n3 to search for entries with name" +
                                "\n4 to delete an entry" +
                                "\nAny other key to exit");

                // Read the user's choice
                String userAction = scanner.nextLine();

                // Option 1: Add a name-number entry to the phonebook
                if (userAction.equals("1")) {
                    // Prompt the user to enter the name
                    System.out.println("Enter a name");
                    String name = scanner.nextLine();
                    // Validate the name format
                    if (!isNameValid(name)) {
                        continue; // Skip to the next iteration if the name is invalid
                    }
                    // Check if the name already exists in the phonebook
                    if (phonebook.containsKey(name)) {
                        System.out.println("This name already exists! Do you want to replace the number? y/n");
                        String repChoice = scanner.nextLine();
                        // If the user chooses not to replace, skip to the next iteration
                        if (repChoice.equalsIgnoreCase("n")) {
                            continue;
                        }
                    }

                    // Prompt the user to enter the phone number
                    System.out.println("Enter the phone number");
                    String phoneNumber = scanner.nextLine();
                    // Validate the phone number format
                    if (!isPhoneNumberValid(phoneNumber)) {
                        continue; // Skip to the next iteration if the phone number is invalid
                    }
                    // Add the name and phone number to the HashMap
                    phonebook.put(name, phoneNumber);
                    System.out.println("The name and number have been added to the phonebook.");
                }
                // Option 2: View all the entries in the phonebook
                else if (userAction.equals("2")) {
                    // Iterate through the HashMap and print all key-value pairs
                    for (String name : phonebook.keySet()) {
                        System.out.println(name + ": " + phonebook.get(name));
                    }
                }
                // Option 3: Search for an entry by name
                else if (userAction.equals("3")) {
                    // Prompt the user to enter the name to search
                    System.out.println("Enter the name you want to search");
                    String keyName = scanner.nextLine();
                    // Check if the name exists in the phonebook
                    if (phonebook.containsKey(keyName)) {
                        // Display the phone number associated with the name
                        System.out.println("The phone number you are looking for is " +
                                phonebook.get(keyName));
                    } else {
                        System.out.println("No such name found in the phonebook.");
                    }
                }
                // Option 4: Delete an entry by name
                else if (userAction.equals("4")) {
                    // Prompt the user to enter the name to delete
                    System.out.println("Enter the name you want to delete ");
                    String keyName = scanner.nextLine();
                    // Check if the name exists in the phonebook
                    if (phonebook.containsKey(keyName)) {
                        // Remove the entry from the HashMap
                        phonebook.remove(keyName);
                        System.out.println("The entry has been removed.");
                    } else {
                        System.out.println("No such name found in the phonebook.");
                    }
                }
                // Exit the program if the user enters any other key
                else {
                    break;
                }
            }
            scanner.close(); // Close the Scanner to prevent resource leaks
        } catch (NumberFormatException nfe) {
            // Handle invalid number input (for example, non-integer input for priority or
            // index)
            System.out.println("Invalid input. Please enter a valid number.");
        }
    }
}