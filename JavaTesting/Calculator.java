package com.example;

/**
 * Simple calculator for demonstration
 */
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero!");
        }
        return (double) a / b;
    }

    public boolean isEven(int number) {
        return number % 2 == 0;
    }

    public double sqrt(double number) {
        if (number < 0) {
            throw new IllegalArgumentException("Cannot take square root of a negative number!");
        }
        return Math.sqrt(number);
    }
}