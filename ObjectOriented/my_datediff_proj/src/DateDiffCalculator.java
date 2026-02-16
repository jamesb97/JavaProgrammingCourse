package ObjectOriented.my_datediff_proj.src;

// Import necessary classes for date manipulation, formatting, and user input
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.format.DateTimeParseException;
import java.time.Period;

// Define a class named DateDiffCalculator
public class DateDiffCalculator {

    // Method to calculate and return the difference between two dates as a string
    public static String getDiff(LocalDate d1, LocalDate d2) {
        // Calculate the period (difference) between the two dates
        Period period = d1.until(d2);

        // Initialize the result string
        String diffStr = "The difference is ";

        // Add years to the result string if the difference in years is not zero
        if (period.getYears() != 0) {
            diffStr = diffStr + period.getYears() + " years, ";
        }

        // Add months to the result string if the difference in months is not zero
        if (period.getMonths() != 0) {
            diffStr = diffStr + period.getMonths() + " months, ";
        }

        // Add days to the result string if the difference in days is not zero
        if (period.getDays() != 0) {
            diffStr = diffStr + period.getDays() + " day(s)";
        }

        // Return the formatted difference string
        return diffStr;
    }

    // Main method, the entry point of the program
    public static void main(String s[]) {
        // Get the current date
        LocalDate todayDate = LocalDate.now();

        // Define a date formatter for the "dd/MM/yyyy" format
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Print the current date in the specified format
        System.out.println("\nThe date is " + todayDate.format(dateformat));

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to input a date in the "dd/MM/yyyy" format
        System.out.println("Input the date in dd/MM/yyyy format");
        String rawDate = scanner.nextLine();

        // Try to parse the user input into a LocalDate object
        try {
            LocalDate givenDate = LocalDate.parse(rawDate, dateformat);

            // Print the given date in the specified format
            System.out.println("\nThe given date is " + givenDate.format(dateformat));

            // Check if the given date is the same as the current date
            if (givenDate.isEqual(todayDate)) {
                System.out.println("Both dates are the same");
            } else {
                // If the given date is before the current date, calculate the difference
                if (givenDate.isBefore(todayDate)) {
                    System.out.println(getDiff(givenDate, todayDate));
                } else {
                    // If the given date is after the current date, calculate the difference
                    System.out.println(getDiff(todayDate, givenDate));
                }
            }
        } catch (DateTimeParseException dte) {
            // Handle invalid date format input
            System.out.println("Invalid input. Please try again!");
        }
        scanner.close(); // Close the scanner to prevent resource leaks
    }
}