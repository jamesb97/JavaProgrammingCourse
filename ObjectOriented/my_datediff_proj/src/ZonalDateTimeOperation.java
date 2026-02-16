package ObjectOriented.my_datediff_proj.src;

// Import necessary classes for date-time manipulation and user input
import java.time.ZonedDateTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class ZonalDateTimeOperation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get the current date and time in the default time zone
        ZonedDateTime dateTime = ZonedDateTime.now();

        // Print the current date and time in the default format
        System.out.println("\nDefault Format: " + dateTime);

        // Loop indefinitely until the user chooses to exit
        while (true) {
            // Display the menu options to the user
            System.out.println("Press 1 to find the difference between two dates," +
                    "\nPress 2 for adding to or deleting from the current date," +
                    "\nAny other key to exit");

            // Read the user's choice
            String userAction = scanner.nextLine();

            if (userAction.equals("1") || userAction.equals("2")) {
                // Prompt the user for the date format
                System.out.println(
                        "Enter the format in which you want to feed the date time along with zone (e.g., dd MM yyyy HH:mm:ss XXX)");
                String dateFormatStr = scanner.nextLine();

                // Create a DateTimeFormatter with the specified format
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(dateFormatStr.strip());

                // Prompt the user for the first date
                System.out.println("Input the date in " + dateFormatStr + " format:");
                String rawDate1 = scanner.nextLine();

                try {
                    // Parse the first date as ZonedDateTime
                    ZonedDateTime localDateTime1 = ZonedDateTime.parse(rawDate1, dateFormat);

                    if (userAction.equals("1")) {
                        // Prompt the user for the second date
                        System.out.println("Input the another date in " + dateFormatStr + " format:");
                        String rawDate2 = scanner.nextLine();

                        // Parse the second date as ZonedDateTime
                        ZonedDateTime localDateTime2 = ZonedDateTime.parse(rawDate2, dateFormat);

                        // Print the parsed dates
                        System.out.println("\nThe given date is " + localDateTime1.format(dateFormat));
                        System.out.println("\nThe other given date is " + localDateTime2.format(dateFormat));

                        // Check if the dates are equal
                        if (localDateTime1.isEqual(localDateTime2)) {
                            System.out.println("Both dates are the same");
                        } else {
                            // Calculate the difference in days
                            long diffInDays = ChronoUnit.DAYS.between(localDateTime1, localDateTime2);
                            System.out.println("The difference is " + diffInDays + " day(s)");
                        }
                    } else if (userAction.equals("2")) {
                        // Prompt the user to select a unit of time to modify
                        System.out.println("Enter which unit of time you want to add/delete:\n" +
                                "d - days, M - months, y - years, w - weeks");
                        String unitToChange = scanner.nextLine();

                        // Prompt the user to enter the quantity of the selected unit
                        System.out.println("Enter the quantity to change (e.g., 3):");
                        long qtyToChange = Long.parseLong(scanner.nextLine());

                        // Prompt the user to choose whether to add or delete the selected quantity
                        System.out.println("\nPress 'a' to add, 'd' to delete," +
                                "\nAny other key to go back to the main menu");
                        String addOrDel = scanner.nextLine();

                        // Variable to store the modified date and time
                        ZonedDateTime newDateTime = localDateTime1;

                        // Handle the user's choice to add or delete time
                        if (addOrDel.equals("a")) {
                            // Add the specified quantity of the selected unit
                            switch (unitToChange) {
                                case "d":
                                    newDateTime = localDateTime1.plusDays(qtyToChange);
                                    break;
                                case "M":
                                    newDateTime = localDateTime1.plusMonths(qtyToChange);
                                    break;
                                case "y":
                                    newDateTime = localDateTime1.plusYears(qtyToChange);
                                    break;
                                case "w":
                                    newDateTime = localDateTime1.plusWeeks(qtyToChange);
                                    break;
                                default:
                                    System.out.println("Invalid input");
                                    continue; // Go back to the main menu
                            }
                        } else if (addOrDel.equals("d")) {
                            // Subtract the specified quantity of the selected unit
                            switch (unitToChange) {
                                case "d":
                                    newDateTime = localDateTime1.minusDays(qtyToChange);
                                    break;
                                case "M":
                                    newDateTime = localDateTime1.minusMonths(qtyToChange);
                                    break;
                                case "y":
                                    newDateTime = localDateTime1.minusYears(qtyToChange);
                                    break;
                                case "w":
                                    newDateTime = localDateTime1.minusWeeks(qtyToChange);
                                    break;
                                default:
                                    System.out.println("Invalid input");
                                    continue; // Go back to the main menu
                            }
                        }

                        // Print the modified date and time
                        System.out.println("The new date and time is: " + newDateTime.format(dateFormat));
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid input. Please try again! " + e.getMessage());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid quantity. Please enter a valid number.");
                }
            } else {
                // Exit the program
                break;
            }
        }

        // Close the scanner
        scanner.close();
    }
}