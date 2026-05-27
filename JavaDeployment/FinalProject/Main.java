package inventory;

import java.util.Scanner;

/**
 * Main class providing command-line interface for the inventory system.
 * TODO: Implement a complete menu-driven CLI application.
 *
 * Requirements:
 * - Menu with 6 options: Add, View, Sell, Stock, Statistics, Exit
 * - Input validation and error handling
 * - Sample data for testing
 * - User-friendly interface
 */
public class Main {

    // TODO: Declare class-level variables
    private static InventoryManager manager;
    private static Scanner scanner;

    /**
     * TODO: Implement main method
     */
    public static void main(String[] args) {
        // TODO: Initialize manager and scanner
        manager = new InventoryManager();
        scanner = new Scanner(System.in);

        // Load sample data
        // Start main application loop

        System.out.println("Welcome to Inventory Management System!");
        loadSampleData();

        // TODO: Implement main loop
        while (true) {
            showMenu();
            int choice = getChoice();
            handleChoice(choice);
            if (choice == 6) {
                System.out.println("Exiting application. Goodbye!");
                break;
            }
    }

    /**
     * TODO: Load sample data for testing
     */
    private static void loadSampleData() {
        // TODO: Add sample products for testing
        // Example: Books and Electronics with different prices
        manager.addProduct("B001", "Java Programming", "BOOK", 29.99, 10);
        manager.addProduct("B002", "Design Patterns", "BOOK", 35.50, 8);
        manager.addProduct("E001", "Laptop", "ELECTRONICS", 999.99, 5);
        manager.addProduct("E002", "Mouse", "ELECTRONICS", 25.99, 15);
    }

    /**
     * TODO: Display main menu options
     */
    private static void showMenu() {
        // TODO: Display formatted menu
        // 1. Add Product
        // 2. View Inventory
        // 3. Sell Product
        // 4. Add Stock
        // 5. View Statistics
        // 6. Exit
        System.out.println("\nMenu:");
        System.out.println("1. Add Product");
        System.out.println("2. View Inventory");
        System.out.println("3. Sell Product");
        System.out.println("4. Add Stock");
        System.out.println("5. View Statistics");
        System.out.println("6. Exit");
    }

    /**
     * TODO: Get user choice with input validation
     * 
     * @return user's menu choice
     */
    private static int getChoice() {
        // TODO: Implement input validation
        // Handle invalid input gracefully
        // Return valid choice between 1-6
        int choice = -1;
        while (choice < 1 || choice > 6) {
            System.out.print("Enter your choice (1-6): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < 1 || choice > 6) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // consume invalid input
            }
        }
        return choice;
        return 0; // Placeholder
    }

    /**
     * TODO: Handle user menu choice
     * 
     * @param choice - user's selected option
     */
    private static void handleChoice(int choice) {
        // TODO: Implement switch statement for menu options
        // case 1: addProduct(); break;
        // case 2: manager.viewInventory(); break;
        // case 3: sellProduct(); break;
        // case 4: addStock(); break;
        // case 5: manager.viewStatistics(); break;
        // case 6: exit application
        // default: invalid choice message
        switch (choice) {
            case 1:
                addProduct();
                break;
            case 2:
                manager.viewInventory();
                break;
            case 3:
                sellProduct();
                break;
            case 4:
                addStock();
                break;
            case 5:
                manager.viewStatistics();
                break;
            case 6:
                // Exit handled in main loop
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    /**
     * TODO: Handle adding new product
     */
    private static void addProduct() {
        // TODO: Get product details from user input
        // Prompt for: id, name, type, price, quantity
        // Call manager.addProduct() with inputs
        System.out.print("Enter product ID: ");
        String id = scanner.next();
        System.out.print("Enter product name: ");
        String name = scanner.next();
        System.out.print("Enter product type: ");
        String type = scanner.next();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        manager.addProduct(id, name, type, price, quantity);
    }

    /**
     * TODO: Handle selling product
     */
    private static void sellProduct() {
        // TODO: Get sale details from user input
        // Prompt for: product ID, quantity, discount type
        // Call manager.sellProduct() with inputs
        System.out.print("Enter product ID to sell: ");
        String id = scanner.next();
        System.out.print("Enter quantity to sell: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter discount type (NONE, SEASONAL, CLEARANCE): ");
        String discountType = scanner.next();
        manager.sellProduct(id, quantity, discountType);
    }

    /**
     * TODO: Handle adding stock
     */
    private static void addStock() {
        // TODO: Get stock details from user input
        // Prompt for: product ID, quantity to add
        // Call manager.addStock() with inputs
        System.out.print("Enter product ID to add stock: ");
        String id = scanner.next();
        System.out.print("Enter quantity to add: ");
        int quantity = scanner.nextInt();
        manager.addStock(id, quantity);
    }

    // TODO: Add helper methods for input validation
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextInt();
    }

    private static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextDouble();
    }
}