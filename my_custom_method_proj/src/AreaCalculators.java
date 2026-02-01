import java.util.Scanner;

public class AreaCalculators {

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

    private static float sphereVolume(float radius) {
        return (4 / 3) * pi * (radius * radius * radius);
    }

    private static float cubeVolume(float sideLength) {
        return sideLength * sideLength * sideLength;
    }

    private static float cuboidVolume(float length, float breadth, float height) {
        return length * breadth * height;
    }

    public static void main(String s[]) {
        System.out.println("Welcome to the area and volume calculator!");

        while (true) {
            System.out.println("\nEnter 1 for circle area\n" +
                    "Enter 2 for square area\n" +
                    "Enter 3 for rectangle area\n" +
                    "Enter 4 for sphere volume\n" +
                    "Enter 5 for cube volume\n" +
                    "Enter 6 for cuboid volume");

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
                System.out.println("The area of rectangle of length " + length + "and breadth " + breadth + " is "
                        + rectangle(length, breadth));
            } else if (choice == 4) {
                System.out.println("Enter the radius of the sphere");
                float radius = Float.parseFloat(scanner.nextLine());
                System.out.println("The volume of sphere of radius " + radius + "is " + sphereVolume(radius));
            } else if (choice == 5) {
                System.out.println("Enter the side length of the cube");
                float sideLength = Float.parseFloat(scanner.nextLine());
                System.out.println("The volume of cube of side length " + sideLength + "is " + cubeVolume(sideLength));
            } else if (choice == 6) {
                System.out.println("Enter the length of the cuboid");
                float length = Float.parseFloat(scanner.nextLine());
                System.out.println("Enter the breadth of the cuboid");
                float breadth = Float.parseFloat(scanner.nextLine());
                System.out.println("Enter the height of the cuboid");
                float height = Float.parseFloat(scanner.nextLine());
                System.out.println("The volume of cuboid of length " + length + ", breadth " + breadth + ", and height "
                        + height + " is " + cuboidVolume(length, breadth, height));
            } else {
                System.out.println("Invalid choice");
                break;
            }
            scanner.close();
        }
    }
}