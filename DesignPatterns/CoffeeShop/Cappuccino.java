package CoffeeShop;

// Another concrete product
public class Cappuccino implements Coffee {

    @Override
    public void brew() {
        System.out.println("☕ Brewing rich Cappuccino with frothy milk");
    }

    @Override
    public double getPrice() {
        return 3.50;
    }
}