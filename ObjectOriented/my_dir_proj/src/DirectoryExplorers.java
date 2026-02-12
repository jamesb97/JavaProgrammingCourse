package ObjectOriented.my_dir_proj.src;

// Import the File class for file and directory operations
import java.io.File;
// Import the Scanner class for user input
import java.util.Scanner;
// Import the IOException class for handling file I/O exceptions
import java.io.IOException;

public class DirectoryExplorers {

    public static void main(String s[]) {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Loop indefinitely until the user chooses to exit
        while (true) {
            // Display the menu options to the user
            System.out.println("\nPress 1 for File Management," + "\nAny other key to exit");

            // Read the user's choice
            String userAction = scanner.nextLine();

            // Option 1: File Management
            if (userAction.equals("1")) {
                // Prompt the user to enter the name of the file or directory with the path
                System.out.println("Enter the name of the file or directory with the path");

                // Read the file name with path
                String fileName = scanner.nextLine();

                // Create a File object representing the file or directory
                File file = new File(fileName);

                // Check if the file or directory exists
                if (file.exists()) {
                    // Check if the file is a file or directory
                    if (file.isFile()) {
                        System.out.println(fileName + " is a file");
                    } else {
                        System.out.println(fileName + " is a directory");
                    }
                } else {
                    // If the file or directory does not exist, prompt the user to create it
                    System.out.println(fileName + " is a not a valid file or directory");
                    System.out.println("To create a file with given name press 1\n"
                            + "To create a directory with given name press 2\n"
                            + "To do nothing and continue, press any other key");

                    // Read the user's choice
                    String createChoice = scanner.nextLine();

                    // Create a file
                    if (createChoice.equals("1")) {
                        // Get the parent directory
                        String parentDirStr = file.getParent();
                        File parentDir = new File(parentDirStr);

                        // Create the parent directory if it does not exist
                        if (!parentDir.exists()) {
                            boolean created = parentDir.mkdirs();
                            if (!created) {
                                System.out.println("The parent directory could not be created");
                                continue;
                            }
                        }

                        // Create the file
                        try {
                            file.createNewFile();
                            System.out.println("File successfully created!");
                        } catch (IOException ioe) {
                            System.out.println("Unable to create file. " + ioe.getMessage());
                        }
                    }
                    // Create a directory
                    else if (createChoice.equals("2")) {
                        // Create the directory
                        boolean created = file.mkdirs();
                        if (created) {
                            System.out.println("The directory has been created");
                        } else {
                            System.out.println("The directory couldn't be created");
                        }
                    }
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