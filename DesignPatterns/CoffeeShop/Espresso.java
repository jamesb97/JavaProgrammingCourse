package CoffeeShop;

// Concrete product - specific coffee type
public class Espresso implements Coffee {

    @Override
    public void brew() {
        System.out.println("☕ Brewing strong Espresso");
    }

    @Override
    public double getPrice() {
        return 2.50;
    }
}