package ObjectOriented.my_datetime_proj.src;

// Import the LocalDate class from the java.time package to work with dates
import java.time.LocalDate;
// Import the LocalTime class from the java.time package to work with times
import java.time.LocalTime;

public class DateTimePrinter {

    public static void main(String s[]) {
        // Print the current date to the console
        // LocalDate.now() retrieves the current date from the system clock
        System.out.println("The date is " + LocalDate.now());

        // Print the current time to the console
        // LocalTime.now() retrieves the current time from the system clock
        System.out.println("The time is " + LocalTime.now());
    }
}