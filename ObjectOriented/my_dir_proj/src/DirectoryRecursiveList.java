package ObjectOriented.my_dir_proj.src;

// Import the File class for file and directory operations
import java.io.File;

// Import the Scanner class for user input
import java.util.Scanner;

// Import the IOException class for handling file I/O exceptions
import java.io.IOException;

public class DirectoryRecursiveList {

    /**
     * Method to recursively list the contents of a directory
     * 
     * @param dirObj the directory object
     */
    private static void directoryList(File dirObj) {
        // Print a message indicating that the directory is being listed recursively
        System.out.println("Printing " + dirObj + " recursively");

        // Get a list of files and subdirectories in the directory
        File files[] = dirObj.listFiles();

        // Iterate over the list of files and subdirectories
        for (int i = 0; i < files.length; i++) {
            // Check if the current item is a file
            if (files[i].isFile()) {
                // Print the absolute path of the file
                System.out.println(files[i].getAbsolutePath());
            } else {
                // If the current item is a subdirectory, recursively list its contents
                directoryList(files[i]);
            }
        }
    }

    public static void main(String s[]) {
        Scanner scanner = new Scanner(System.in);

        // Loop indefinitely until the user chooses to exit
        while (true) {
            // Display the menu options to the user
            System.out.println("\nPress 1 to recursively list a directory," + "\nAny other key to exit");

            // Read the user's choice
            String userAction = scanner.nextLine();

            // Option 1: Recursively list a directory
            if (userAction.equals("1")) {
                // Prompt the user to enter the name of the directory with the path
                System.out.println("Enter the name of the directory with the path");

                // Read the directory name with path
                String dirName = scanner.nextLine();

                // Create a File object representing the directory
                File dirObj = new File(dirName);

                // Check if the directory exists and is a valid directory
                if (dirObj.exists() && dirObj.isDirectory()) {
                    // Recursively list the contents of the directory
                    directoryList(dirObj);
                } else {
                    // If the directory does not exist, or is not a valid directory
                    System.out.println(dirName + " is not a valid directory");
                    continue;
                }
            }
            // Exit the program
            else {
                System.out.println("Bye!");
                break;
            }
        }
        scanner.close();
    }
}