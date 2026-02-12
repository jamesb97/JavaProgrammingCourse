package ObjectOriented.my_fileio_proj.src;

// Import necessary classes for file reading and user input
import java.io.FileReader;
import java.util.Scanner;
import java.io.FileNotFoundException;

// Define the main class
class ReadFileExampleWithScanner {
    public static void main(String[] args) {
        // Specify the file path
        try {
            // Create a Scanner object to read user input from the console
            Scanner scanner = new Scanner(System.in);
            // Prompt the user to enter the name of the file they want to read
            System.out.println("Enter the name of the file you want to read.");
            // Create a new Scanner object to read from the file specified by the user
            // FileReader is used to read the file, and the file name is obtained from the
            // user input
            Scanner fileScanner = new Scanner(new FileReader(scanner.nextLine()));
            // Loop through the file line by line
            while (fileScanner.hasNext()) {
                // Read the next line from the file
                String fileLine = fileScanner.nextLine();
                // Print the line to the console
                System.out.println(fileLine);
            }
            // Close the file scanner to free up resources (optional but recommended)
            fileScanner.close();
            scanner.close();
        } catch (FileNotFoundException e) {
            // Handle the case where the file is not found
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}