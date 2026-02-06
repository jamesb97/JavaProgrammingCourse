import java.util.Scanner; // Importing the Scanner class to read input from the user

public class BookMenu {

    // A helper method to compare two books and return the one with the higher price
    private static Book getExpensiveBook(Book book1, Book book2) {
        if (book1.getPrice() < book2.getPrice()) { // Compare prices of the two books
            return book2; // Return the second book if its price is higher
        } else {
            return book1; // Return the first book otherwise
        }
    }

    public static void main(String s[]) {
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for reading user input
        Book[] books = new Book[10]; // Create an array to store up to 10 Book objects
        int bkIdx = 0; // Initialize the index to keep track of added books

        // Infinite loop to repeatedly display the menu until the user chooses to exit
        while (true) {
            System.out.println("Press 1 to view books, 2 to add books, " +
                    "3 to compare prices of books, any other key to exit");
            String userAction = scanner.nextLine(); // Read user input for menu selection

            // Option 1: View books
            if (userAction.equals("1")) {
                for (int i = 0; i < books.length; i++) { // Iterate through the books array
                    if (books[i] != null) { // Check if the current slot contains a Book object
                        System.out.println(books[i]); // Print the Book details (uses the Book class's toString method)
                    }
                }
            }
            // Option 2: Add a new book
            else if (userAction.equals("2")) {
                if (bkIdx == 10) { // Check if the books array is already full
                    System.out.println("10 books added already. Cannot add any more books!");
                    continue; // Skip the rest of this iteration and prompt the menu again
                }
                // Prompt the user for book details
                System.out.println("Enter book title");
                String tmpTitle = scanner.nextLine();
                System.out.println("Enter book author");
                String tmpAuthor = scanner.nextLine();
                System.out.println("Enter book price");
                float tmpPrice = Float.parseFloat(scanner.nextLine()); // Convert the price from String to float

                // Create a new Book object and store it in the books array at the current index
                Book bkTmp = new Book(tmpTitle, tmpAuthor, tmpPrice);
                books[bkIdx++] = bkTmp;
            }
            // Option 3: Compare prices of two books
            else if (userAction.equals("3")) {
                // Prompt the user for the indices of the books to compare
                System.out.println("Enter index of first book to compare");
                int book1Idx = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter index of second book to compare");
                int book2Idx = Integer.parseInt(scanner.nextLine());

                // Check if the selected indices contain valid Book objects
                if (books[book1Idx] != null && books[book2Idx] != null) {
                    // Compare the books and print the details of the more expensive book
                    System.out.println("The details of expensive book is \n" +
                            getExpensiveBook(books[book1Idx], books[book2Idx]));
                } else {
                    System.out.println("One of the books is null"); // Error message if an index is empty
                }
            }
            // Exit the program for any other input
            else {
                break; // Break the loop to exit the program
            }
        }
        scanner.close(); // Close the Scanner to free up resources
    }
}