package ObjectOriented.my_datetime_proj.src;

// Import the LocalDate class from the java.time package to work with dates
import java.time.LocalDate;
// Import the LocalTime class from the java.time package to work with times
import java.time.LocalTime;
// Import the LocalDateTime class from the java.time package to work with both date and time
import java.time.LocalDateTime;
// Import the DateTimeFormatter class to format date and time
import java.time.format.DateTimeFormatter;
// Import the Scanner class to read user input
import java.util.Scanner;

// Define a class named DateTimesPrinter
public class DateTimesPrinter {

    // Main method, the entry point of the program
    public static void main(String s[]) {
        // Get the current date using LocalDate.now()
        LocalDate todayDate = LocalDate.now();

        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the preferred date format
        System.out.println("Enter the format you would like to print the date in\n" +
                "dd for date, \n" +
                "M for month, MM for zero-padded month, MMM for abbreviated month, MMMM for full name, \n" +
                "yy or yyyy for year");

        // Read the date format input from the user
        String strDateFormat = scanner.nextLine();

        // Create a DateTimeFormatter object using the user-provided format
        DateTimeFormatter newdateformat = DateTimeFormatter.ofPattern(strDateFormat);

        // Format and print the current date using the specified format
        System.out.println("\nThe date is " + todayDate.format(newdateformat));

        // Get the current time using LocalTime.now()
        LocalTime nowTime = LocalTime.now();

        // Prompt the user to enter the preferred time format
        System.out.println("\n\nEnter the format you would like to print the time in\n" +
                "H for Hour of day (0-23), HH for Zero-padded hour of day (00-23), \n" +
                "h for Hour of am/pm (1-12), hh for Zero-padded hour of am/pm (01-12) \n" +
                "m for Minute of hour (0-59)\n" +
                "mm for Zero-padded minute of hour (00-59)\n" +
                "s for Second of minute (0-59), ss for Zero-padded second of minute (00-59)");

        // Read the time format input from the user
        String strTimeFormat = scanner.nextLine();

        // Create a DateTimeFormatter object using the user-provided format
        DateTimeFormatter newtimeformat = DateTimeFormatter.ofPattern(strTimeFormat);

        // Format and print the current time using the specified format
        System.out.println("The time is " + nowTime.format(newtimeformat));

        // Get the current date and time using LocalDateTime.now()
        LocalDateTime nowDateTime = LocalDateTime.now();

        // Print the current date and time in the default format
        System.out.println(nowDateTime);

        // Prompt the user to enter the preferred date and time format
        System.out.println("\n\nEnter the format you would like to print the date and time in\n");

        // Read the date and time format input from the user
        String strDateTimeFormat = scanner.nextLine();

        // Create a DateTimeFormatter object using the user-provided format
        DateTimeFormatter newDateTimeformat = DateTimeFormatter.ofPattern(strDateTimeFormat);

        // Format and print the current date and time using the specified format
        System.out.println("The date and time is " + nowDateTime.format(newDateTimeformat));
        // Close the scanner to prevent resource leaks
        scanner.close();
    }
}