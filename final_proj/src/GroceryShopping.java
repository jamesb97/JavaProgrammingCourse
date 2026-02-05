import java.util.Scanner;

public class GroceryShopping {
    public static void main(String s[]) {
        // Create a String array of items to buy
        String[] itemsToBuy = { "Apples", "Bananas", "Carrots", "Dairy Milk", "Eggs", "Flour", "Grapes", "Honey",
                "Ice Cream", "Juice", "Kiwi", "Lettuce", "Sugar" };
        // Create a float array of item prices
        String[] item = new String[10];
        float[] price = new float[10];
        // Create an integer array for stock inventory
        int[] stock = new int[10];
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
        // Populate stock array with sample data
        stock[0] = 50;
        stock[1] = 40;
        stock[2] = 30;
        stock[3] = 25;
        stock[4] = 35;
        stock[5] = 20;
        stock[6] = 15;
        stock[7] = 18;
        stock[8] = 22;
        stock[9] = 28;
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

                // Check if sufficient stock is available
                if (quantity > stock[itemIndex]) {
                    System.out.println(item[itemIndex] + " is out of stock. Available quantity: " + stock[itemIndex]);
                    continue;
                }

                // Calculate the cost for the item and add it to the total bill
                float itemCost = price[itemIndex] * quantity;
                totalBill += itemCost;

                // Decrease the stock for the item
                stock[itemIndex] -= quantity;

                System.out.println(
                        "Added " + quantity + " x " + item[itemIndex] + " to the bill. Current total: $" + totalBill);
                System.out.println("Stock remaining for " + item[itemIndex] + ": " + stock[itemIndex]);
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
        // Close the scanner
        scanner.close();
    }

    // Method to search for an item in the array
    public static void searchItem(String[] items, String itemName) {
        for (int i = 0; i < items.length; i++) {
            if (items[i].equalsIgnoreCase(itemName)) {
                System.out.println("Item found at index: " + i);
                return;
            }
        }
        System.out.println("Item not found");
    }

    // Calculate the average price that takes a float array of prices and returns
    // the average price
    public static float calculateAveragePrice(float[] prices) {
        float sum = 0.0f;
        for (float price : prices) {
            sum += price;
        }
        return sum / prices.length;
    }

    // Filter items below a certain price
    public static void filterItemsBelowPrice(String[] items, float[] prices, float threshold) {
        System.out.println("Items below price $" + threshold + ":");
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < threshold) {
                System.out.println(items[i] + " - $" + prices[i]);

            }
        }
    }

    // Total bill with discount
    public static float totalBillWithDiscount(float totalBill, float discountPercentage) {
        float discountAmount = (discountPercentage / 100) * totalBill;
        // Check if the total bill is greater than 100, if so apply a 10% discount
        if (totalBill > 100) {
            System.out.println("A discount of " + discountPercentage + "% has been applied.");
        } else {
            System.out.println("No discount applied as total bill is less than or equal to $100.");
            discountAmount = 0;
        }
        System.out.println("Total bill after discount: $" + (totalBill - discountAmount));
        return totalBill - discountAmount;
    }

    // Implement inventory management

    // Method to sort items by price in ascending order
    public static void sortItemsByPrice(String[] items, float[] prices) {
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = 0; j < prices.length - i - 1; j++) {
                if (prices[j] > prices[j + 1]) {
                    // Swap prices
                    float tempPrice = prices[j];
                    prices[j] = prices[j + 1];
                    prices[j + 1] = tempPrice;
                    // Swap corresponding items
                    String tempItem = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = tempItem;
                }
            }
        }
    }
}