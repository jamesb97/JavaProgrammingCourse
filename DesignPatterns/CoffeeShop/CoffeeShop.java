package CoffeeShop;

// Client code - uses factory to create coffees
public class CoffeeShop {

    public static void main(String[] args) {
        System.out.println("☕ Welcome to Coffee Factory Demo!");

        // Order different coffees using factory
        orderCoffee("espresso");
        orderCoffee("latte");
        orderCoffee("cappuccino");
        orderCoffee("mocha"); // This will fail - not supported

        System.out.println("\n✅ Factory Pattern Demo Complete!");
    }

    // Helper method to process orders
    private static void orderCoffee(String coffeeType) {
        System.out.println("\n📝 Customer orders: " + coffeeType);

        // Use factory instead of 'new' - this is the key!
        Coffee coffee = CoffeeFactory.createCoffee(coffeeType);

        if (coffee != null) {
            coffee.brew();
            System.out.println("💰 Price: $" + coffee.getPrice());
        }
    }
}