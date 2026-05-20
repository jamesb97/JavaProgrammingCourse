package com.calculator;

import java.text.DecimalFormat;

public class Calculator {
    private final ConfigManager config;
    private final DecimalFormat formatter;

    public Calculator(ConfigManager config) {
        this.config = config;
        int precision = config.getIntProperty("app.precision", 2);
        String pattern = "#." + "#".repeat(precision);
        this.formatter = new DecimalFormat(pattern);
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
    }

    public String formatResult(double result) {
        return formatter.format(result);
    }
}