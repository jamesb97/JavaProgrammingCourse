package CoffeeShop;

// Another concrete product
public class Latte implements Coffee {

    @Override
    public void brew() {
        System.out.println("☕ Brewing creamy Latte with steamed milk");
    }

    @Override
    public double getPrice() {
        return 4.00;
    }
}