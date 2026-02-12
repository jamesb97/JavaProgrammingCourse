package ObjectOriented.my_fileio_proj.src;

// Import necessary classes for file operations and user input
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

// Define the main class
public class ReadFileExample {
    public static void main(String[] args) {
        // Create a Scanner object to read user input from the console
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter the name of the file they want to read
        System.out.println("Enter the name of the file you want to read.");

        // Get the file path from the user input and convert it to a Path object
        Path filePath = Paths.get(scanner.nextLine());

        try {
            // Read the entire content of the file as a single string
            String content = Files.readString(filePath);

            // Print the file content to the console
            System.out.println(content);
        } catch (IOException e) {
            // Handle the case where an I/O error occurs (e.g., file not found or cannot be
            // read)
            System.err.println("Error reading file: " + e.getMessage());
        }
        scanner.close();
    }
}