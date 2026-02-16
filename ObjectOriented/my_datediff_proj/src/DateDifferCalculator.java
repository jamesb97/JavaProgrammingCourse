package ObjectOriented.my_datediff_proj.src;

// Import necessary classes for date manipulation, formatting, and user input
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.format.DateTimeParseException;
import java.time.Period;

// Define a class named DateDifferCalculator
public class DateDifferCalculator {

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
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the date format they want to use (e.g., dd/MM/yyyy)
        System.out.println("Enter the format in which you want to feed the dates in:");
        String dateFormatStr = scanner.nextLine();

        // Create a DateTimeFormatter object using the user-provided format
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern(dateFormatStr);

        // Try to parse the user input into LocalDate objects
        try {
            // Prompt the user to input the first date in the specified format
            System.out.println("Input the date in " + dateFormatStr + " format:");
            String rawDate1 = scanner.nextLine();
            // Parse the input into a LocalDate object
            LocalDate givenDate1 = LocalDate.parse(rawDate1, dateformat);

            // Prompt the user to input the second date in the specified format
            System.out.println("Input another date in " + dateFormatStr + " format:");
            String rawDate2 = scanner.nextLine();
            // Parse the input into a LocalDate object
            LocalDate givenDate2 = LocalDate.parse(rawDate2, dateformat);

            // Print the first date in the specified format
            System.out.println("\nThe given date is " + givenDate1.format(dateformat));

            // Print the second date in the specified format
            System.out.println("\nThe other given date is " + givenDate2.format(dateformat));

            // Check if the two dates are the same
            if (givenDate1.isEqual(givenDate2)) {
                System.out.println("Both dates are the same");
            } else {
                // If the first date is before the second date, calculate the difference
                if (givenDate1.isBefore(givenDate2)) {
                    System.out.println(getDiff(givenDate1, givenDate2));
                } else {
                    // If the first date is after the second date, calculate the difference
                    System.out.println(getDiff(givenDate2, givenDate1));
                }
            }
        } catch (DateTimeParseException dte) {
            // Handle invalid date format input
            System.out.println("Invalid input. Please try again!");
        } catch (Exception e) {
            // Handle any other exception such as unknown formats
            System.out.println("Invalid input. Please try again!");
        }
        scanner.close(); // Close the scanner to prevent resource leaks
    }
}