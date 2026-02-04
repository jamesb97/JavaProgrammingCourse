import java.util.Scanner;

// Custom exception class for item not found
class ItemNotFoundException extends Exception {
    public ItemNotFoundException(String message) {
        super(message);
    }
}

public class ShoppingCart {
    public static void main(String[] args) {
        // Initialize arrays for items and prices
        String[] item = new String[10];
        float[] price = new float[10];

        // Populate the arrays with some sample data
        item[0] = "Apple";
        price[0] = 0.50f;
        item[1] = "Banana";
        price[1] = 0.30f;
        item[2] = "Bread";
        price[2] = 2.00f;
        item[3] = "Milk";
        price[3] = 1.50f;
        item[4] = "Eggs";
        price[4] = 2.50f;
        item[5] = "Cheese";
        price[5] = 3.00f;
        item[6] = "Chicken";
        price[6] = 5.00f;
        item[7] = "Rice";
        price[7] = 1.00f;
        item[8] = "Pasta";
        price[8] = 1.20f;
        item[9] = "Tomato";
        price[9] = 0.80f;

        Scanner scanner = new Scanner(System.in);

        // Outer infinite loop for multiple users
        while (true) {

            // Inner infinite loop for a single user's shopping
            float totalBill = 0.0f;
            while (true) {
                try {
                    System.out.println("Enter the name of the item (or type 'finish' to end shopping):");
                    String inputItem = scanner.nextLine();

                    // Check if the user wants to finish shopping
                    if (inputItem.equalsIgnoreCase("finish")) {
                        System.out.println("Your total bill is: $" + totalBill);
                        System.out.println("Thank you for shopping with us!");
                        break; // Exit the inner loop
                    }

                    // Find the index of the item in the array
                    int itemIndex = -1;
                    for (int i = 0; i < item.length; i++) {
                        if (item[i].equalsIgnoreCase(inputItem)) {
                            itemIndex = i;
                            break;
                        }
                    }

                    // If the item is not found, throw the custom exception
                    if (itemIndex == -1) {
                        throw new ItemNotFoundException("Item '" + inputItem + "' not found. Please try again.");
                    }

                    // Ask for the quantity of the item
                    System.out.println("Enter the quantity of " + item[itemIndex] + ":");
                    int quantity = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    // Calculate the cost for the item and add it to the total bill
                    float itemCost = price[itemIndex] * quantity;
                    totalBill += itemCost;

                    System.out.println("Added " + quantity + " x " + item[itemIndex] + " to the bill. Current total: $"
                            + totalBill);
                } catch (ItemNotFoundException e) {
                    System.out.println(e.getMessage()); // Print the exception message
                } catch (Exception e) {
                    System.out.println("Invalid input. Please try again.");
                    scanner.nextLine(); // Clear the invalid input
                }

            }
            String userInput = scanner.nextLine();
            // Exit the program if the user types "exit"
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for using the shopping cart. Goodbye!");
                break;
            }

        }

        scanner.close();
    }
}