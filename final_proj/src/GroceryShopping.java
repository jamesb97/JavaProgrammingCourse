import java.util.Scanner;

public class GroceryShopping {
    public static void main(String s[]) {
        // Create a String array of items to buy
        String[] itemsToBuy = { "Apples", "Bananas", "Carrots", "Dairy Milk", "Eggs", "Flour", "Grapes", "Honey",
                "Ice Cream", "Juice", "Kiwi", "Lettuce", "Sugar" };
        // Create a float array of item prices
        String[] item = new String[10];
        float[] price = new float[10];
        // Populate the arrays with some sample data
        item[0] = "Apples";
        price[0] = 0.50f;
        item[1] = "Bananas";
        price[1] = 0.30f;
        item[2] = "Carrots";
        price[2] = 2.00f;
        item[3] = "Dairy Milk";
        price[3] = 1.50f;
        item[4] = "Eggs";
        price[4] = 2.50f;
        item[5] = "Flour";
        price[5] = 3.00f;
        item[6] = "Grapes";
        price[6] = 5.00f;
        item[7] = "Honey";
        price[7] = 1.00f;
        item[8] = "Ice Cream";
        price[8] = 1.20f;
        item[9] = "Juice";
        price[9] = 0.80f;
        item[10] = "Kiwi";
        price[10] = 0.90f;
        item[11] = "Lettuce";
        price[11] = 1.10f;
        item[12] = "Sugar";
        price[12] = 2.20f;

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Create an infinite loop to allow the user to add items to their grocery list
        while (true) {
            /*
             * Enter the code for adding grocery list here
             */
            String userInput = scanner.nextLine();
            // Exit the program if the user types "exit"
            if (userInput.equalsIgnoreCase("exit")) {
                System.out.println("Thank you for using the shopping cart. Goodbye!");
                break;
            } else {
                System.out.println("You have added " + userInput + " to your grocery list.");
            }
            // Close out the loop and program
            break;
        }
        // Custom exception class for item not found
        class ItemNotFoundException extends Exception {
            public ItemNotFoundException(String message) {
                super(message);
            }
        }
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

                System.out.println(
                        "Added " + quantity + " x " + item[itemIndex] + " to the bill. Current total: $" + totalBill);
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        // Close the scanner
        scanner.close();
    }
}