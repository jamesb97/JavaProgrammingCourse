import java.util.Scanner;

public class AreaCalculator {
    private static float pi = 3.142f;

    private static float circle(float radius) {
        return (pi * radius * radius);
    }

    private static float square(float length) {
        return length * length;
    }

    private static float rectangle(float length, float breadth) {
        return length * breadth;
    }

    public static void main(String s[]) {
        System.out.println("Welcome to the area calculator!");
        while (true) {
            System.out.println("\nEnter 1 for circle\n" +
                    "Enter 2 for square\n" +
                    "Enter 3 for rectangle");
            Scanner scanner = new Scanner(System.in);
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 1) {
                System.out.println("Enter the radius of the circle");
                float radius = Float.parseFloat(scanner.nextLine());
                System.out.println("The area of circle of radius " + radius + "is " + circle(radius));
            } else if (choice == 2) {
                System.out.println("Enter the length of the square");
                float length = Float.parseFloat(scanner.nextLine());
                System.out.println("The area of square of side length " + length + " is " + square(length));
            } else if (choice == 3) {
                System.out.println("Enter the length of the rectangle");
                float length = Float.parseFloat(scanner.nextLine());
                System.out.println("Enter the breadth of the rectangle");
                float breadth = Float.parseFloat(scanner.nextLine());
                System.out.println("The area of rectangle of length " + length +
                        "and breadth " + breadth + " is " + rectangle(length, breadth));
            } else {
                System.out.println("Invalid choice");
                break;
            }
            scanner.close();
        }
    }
}