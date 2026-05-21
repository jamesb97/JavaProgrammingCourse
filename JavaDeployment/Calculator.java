package com.calculator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Calculator {
    private final ConfigManager config;
    private final DecimalFormat formatter;
    private final List<String> history = new ArrayList<>();
    // private final List<CalculationListener> listeners = new ArrayList<>();
    private final int historyLimit = 5; // Limit history to last 5 calculations

    public Calculator(ConfigManager config) {
        this.config = config;
        int precision = config.getIntProperty("app.precision", 2);
        String pattern = "#." + "#".repeat(precision);
        this.formatter = new DecimalFormat(pattern);
        this.history = new ArrayList<>();
    }

    public double calculate(double num1, String operation, double num2) {
        switch (operation) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 == 0) {
                    throw new IllegalArgumentException("Cannot divide by zero");
                }
                return num1 / num2;
            default:
                throw new IllegalArgumentException("Invalid operation: " + operation);
        }
        // Add calculation to history
        // String calculation = num1 + " " + operation + " " + num2 + " = " + result;
        // history.add(calculation);
        // if (history.size() > historyLimit) {
        // history.remove(0); // Remove oldest entry to maintain history limit
        // }
        // Notify listeners of new calculation
        // for (CalculationListener listener : listeners) {
        // listener.onCalculation(calculation);
        // }
        addToHistory(num1, operation, num2, result);
        return result;
    }

    // Store the history of calculations in a list or database if needed

    public String formatResult(double result) {
        return formatter.format(result);
    }

    private double addToHistory(double num1, String operation, double num2, double result) {
        String calculation = num1 + " " + operation + " " + num2 + " = " + result;
        history.add(calculation);
        if (history.size() > historyLimit) {
            history.remove(0); // Remove oldest entry to maintain history limit
        }
        return result;
    }

    // Show that history is empty
    public List<String> getHistory() {
        return new ArrayList<>(history);

        System.out.println("History is empty.");
        return new ArrayList<>();
    }
    // System.out.println("=== Calculation History ===");
    // for (int i = 0; i < history.size(); i++) {
    // System.out.println((i + 1) + ". " + history.get(i));
    // }
}