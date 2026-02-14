package ObjectOriented.my_datetime_proj.src;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ZonedDateTimesPrinter {

    public static void main(String s[]) {
        Scanner scanner = new Scanner(System.in);
        ZonedDateTime nowDateTime = ZonedDateTime.now();
        System.out.println("\nDefault Format " + nowDateTime);
        System.out.println("\n\nEnter the format you would like to print the time in\n" +
                "dd for date, \n" +
                "M for month, MM for zero-padded month, MMM for abreviated month, MMMM for full name, \n" +
                "yy or yyyy for year\n" +
                "H for Hour of day (0-23), HH for Zero-padded hour of day (00-23), \n" +
                "h for Hour of am/pm (1-12), hh for Zero-padded hour of am/pm (01-12) \n" +
                "m for Minute of hour (0-59)\n" +
                "mm for Zero-padded minute of hour (00-59)\n" +
                "s for Second of minute (0-59), ss for Zero-padded second of minute (00-59)\n" +
                "K for Hour of day (0-11), KK: Zero-padded hour of day (00-11), k: Hour of day (1-24)\n" +
                "z for Time zone (for example, PDT, EST), zzz: Time zone (for example, Pacific Daylight Time, Eastern Standard Time)\n"
                +
                "Z for Time zone offset (for example, +0800, -0500)\n" +
                "a for displaying AM/PM\n");

        String strDateTimeFormat = scanner.nextLine();
        DateTimeFormatter newDateTimeformat = DateTimeFormatter.ofPattern(strDateTimeFormat);
        System.out.println("The date and time is " + nowDateTime.format(newDateTimeformat));
        // Close the scanner to prevent resource leaks
        scanner.close();
    }
}