import java.util.Scanner;

public class ExceptionTrials {
    public static void main(String s[]) {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Initialize an array to store up to 5 strings
        String strArr[] = new String[5];

        // Variable to track the current index for adding strings
        int strIdx = 0;

        // Infinite loop to keep the program running until the user chooses to exit
        while (true) {
            // Display the menu options to the user
            System.out.println(
                    "Press 1 to add String, " +
                            "\n2 to get String from a particular index, " +
                            "\n3 to get the length string in any index, " +
                            "\n4 to get all the strings in the array " +
                            "\nany other key to exit");

            // Read the user's choice
            String userAction = scanner.nextLine();

            // Option 1: Add a string to the array
            if (userAction.equals("1")) {
                // Check if the array is already full
                if (strIdx == 5) {
                    System.out.println("There are already 5 strings in the array!");
                } else {
                    // Prompt the user to enter a string
                    System.out.println("Enter the String ");
                    String inputStr = scanner.nextLine();

                    // Add the string to the array and increment the index
                    strArr[strIdx++] = inputStr;
                }
            }

            // Option 2: Retrieve a string from a specific index
            else if (userAction.equals("2")) {
                try {
                    // Prompt the user to enter the index
                    System.out.println("Enter the index you want to retrieve ");
                    int retIdx = Integer.parseInt(scanner.nextLine());
                    System.out.println("String at index " + retIdx + ": " + strArr[retIdx]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid integer.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid index! Please enter an index between 0 and " + (strArr.length - 1));
                }
            }

            // Option 3: Get the length of a string at a specific index
            else if (userAction.equals("3")) {
                try {
                    // Prompt the user to enter the index
                    System.out.println("Enter the index you check the length of ");
                    int retIdx = Integer.parseInt(scanner.nextLine());
                    System.out.println("Length of string at index " + retIdx + ": " + strArr[retIdx].length());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid integer.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid index! Please enter an index between 0 and " + (strArr.length - 1));
                } catch (NullPointerException e) {
                    System.out.println("No string exists at the specified index!");
                }
            }

            // Option 4: Get all the strings in the array
            else if (userAction.equals("4")) {
                System.out.println("Strings in the array:");
                for (int i = 0; i < strIdx; i++) {
                    if (strArr[i] != null) {
                        System.out.println("Index " + i + ": " + strArr[i]);
                    } else {
                        System.out.println("Index " + i + ": (null)");
                    }
                }
            }

            // Exit the program if the user enters any other key
            else {
                break;
            }
        }

        // Close the scanner to avoid resource leak
        scanner.close();
    }
}