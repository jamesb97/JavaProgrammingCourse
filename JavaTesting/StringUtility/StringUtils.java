package com.example.util;

/**
 * Utility class for string operations
 */
public class StringUtils {

    /**
     * Reverses the given string
     *
     * @param input the string to reverse
     * @return the reversed string
     */
    public static String reverse(String input) {
        if (input == null) {
            return null;
        }
        return new StringBuilder(input).reverse().toString();
    }

    /**
     * Checks if a string is a palindrome (reads the same forwards and backwards).
     * This method is case-insensitive.
     *
     * @param str the string to check
     * @return true if string is a palindrome, false otherwise (including null)
     */
    public static boolean isPalindrome(String str) {
        // Handle null input - design decision: null is not a palindrome
        if (str == null) {
            return false;
        }

        // Handle empty string - design decision: empty string is a palindrome
        if (str.isEmpty()) {
            return true;
        }

        // Convert to lowercase for case-insensitive comparison
        String normalized = str.toLowerCase();

        // Compare string with its reverse
        String reversed = new StringBuilder(normalized).reverse().toString();
        return normalized.equals(reversed);
    }
}