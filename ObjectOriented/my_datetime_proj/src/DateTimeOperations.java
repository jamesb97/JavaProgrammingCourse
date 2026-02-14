package ObjectOriented.my_datetime_proj.src;

// Import necessary classes for date-time manipulation and user input
import java.time.ZonedDateTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

public class DateTimeOperations {

    public static void main(String s[]) {
        Scanner scanner = new Scanner(System.in);

        // Get the current date and time in the default time zone
        ZonedDateTime dateTime = ZonedDateTime.now();

        // Print the current date and time in the default format
        System.out.println("\nDefault Format: " + dateTime);

        // Loop indefinitely until the user chooses to exit
        while (true) {
            // Display the menu options to the user
            System.out.println("\nPress 1 for adding to or deleting from the current date/time," +
                    "\nPress 2 for changing the timezone," +
                    "\nAny other key to exit");

            // Read the user's choice
            String userAction = scanner.nextLine();

            // Option 1: Add or delete time units
            if (userAction.equals("1")) {
                // Prompt the user to select a unit of time to modify
                System.out.println("Enter which unit of time you want to add/delete:\n" +
                        "d - days, M - months, y - years, h - hours, m - minutes, s - seconds, w - weeks");
                String unitToChange = scanner.nextLine();

                // Prompt the user to enter the quantity of the selected unit
                System.out.println("Enter the quantity to change (for example, 3):");
                long qtyToChange = Long.parseLong(scanner.nextLine());

                // Prompt the user to choose whether to add or delete the selected quantity
                System.out.println("\nPress 'a' to add, 'd' to delete," +
                        "\nAny other key to go back to the main menu");
                String addOrDel = scanner.nextLine();

                // Variable to store the modified date and time
                ZonedDateTime newDateTime = null;

                // Handle the user's choice to add or delete time
                if (addOrDel.equals("a")) {
                    // Add the specified quantity of the selected unit
                    switch (unitToChange) {
                        case "d":
                            newDateTime = dateTime.plusDays(qtyToChange);
                            break;
                        case "M":
                            newDateTime = dateTime.plusMonths(qtyToChange);
                            break;
                        case "y":
                            newDateTime = dateTime.plusYears(qtyToChange);
                            break;
                        case "h":
                            newDateTime = dateTime.plusHours(qtyToChange);
                            break;
                        case "m":
                            newDateTime = dateTime.plusMinutes(qtyToChange);
                            break;
                        case "s":
                            newDateTime = dateTime.plusSeconds(qtyToChange);
                            break;
                        case "w":
                            newDateTime = dateTime.plusWeeks(qtyToChange);
                            break;
                        default:
                            System.out.println("Invalid input");
                            continue; // Go back to the main menu
                    }
                } else if (addOrDel.equals("d")) {
                    // Subtract the specified quantity of the selected unit
                    switch (unitToChange) {
                        case "d":
                            newDateTime = dateTime.minusDays(qtyToChange);
                            break;
                        case "M":
                            newDateTime = dateTime.minusMonths(qtyToChange);
                            break;
                        case "y":
                            newDateTime = dateTime.minusYears(qtyToChange);
                            break;
                        case "h":
                            newDateTime = dateTime.minusHours(qtyToChange);
                            break;
                        case "m":
                            newDateTime = dateTime.minusMinutes(qtyToChange);
                            break;
                        case "s":
                            newDateTime = dateTime.minusSeconds(qtyToChange);
                            break;
                        case "w":
                            newDateTime = dateTime.minusWeeks(qtyToChange);
                            break;
                        default:
                            System.out.println("Invalid input");
                            continue; // Go back to the main menu
                    }
                } else {
                    // Handle invalid input
                    System.out.println("Invalid input");
                    continue; // Go back to the main menu
                }

                // Prompt the user to enter a custom format for the modified date and time
                System.out.println("\n\nEnter the format you would like to print the date and time in:");
                String strDateTimeFormat = scanner.nextLine();

                // Create a DateTimeFormatter object using the user-provided format
                DateTimeFormatter newDateTimeformat = DateTimeFormatter.ofPattern(strDateTimeFormat);

                // Print the modified date and time in the specified format
                System.out.println("The date and time is: " + newDateTime.format(newDateTimeformat));
            }

            // Option 2: Change the time zone
            else if (userAction.equals("2")) {
                // Display all available time zone IDs
                System.out.println("Check the following Zone options:");
                Object[] zoneIds = ZoneId.getAvailableZoneIds().toArray();
                for (Object zoneId : zoneIds) {
                    System.out.println(zoneId);
                }

                // Prompt the user to enter a new time zone
                System.out.println("\n\nEnter the time zone you want to get the current time for:");
                String newZone = scanner.nextLine();

                // Convert the current date and time to the new time zone
                ZonedDateTime newZoneDateTime = dateTime.withZoneSameInstant(ZoneId.of(newZone));

                // Define a default format for displaying the date and time
                DateTimeFormatter newDateTimeformat = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm:ss a zzz");

                // Print the date and time in the new time zone using the default format
                System.out.println("The date and time is: " + newZoneDateTime.format(newDateTimeformat));
            }

            // Exit the program
            else {
                break;
            }
        }
        scanner.close(); // Close the scanner to prevent resource leaks
    }
}