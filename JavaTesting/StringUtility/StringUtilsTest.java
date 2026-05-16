package com.example.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Utility test class for string operations
 */
class StringUtilsTest {
    /**
     * Test: isPalindrome() should return false for null input
     * Design decision: null is not a palindrome
     */
    @Test
    void isPalindrome_ShouldReturnFalse_WhenInputIsNull() {
        assertFalse(StringUtils.isPalindrome(null),
                "isPalindrome() should return false for null input");
    }

    /**
     * Test: isPalindrome() should return true for empty string
     */
    @Test
    void isPalindrome_ShouldReturnTrue_WhenInputIsEmpty() {
        assertTrue(StringUtils.isPalindrome(""),
                "isPalindrome() should return true for empty string");
    }

    /**
     * Test: isPalindrome() should return true for single character
     */
    @Test
    void isPalindrome_ShouldReturnTrue_WhenInputIsSingleCharacter() {
        assertTrue(StringUtils.isPalindrome("a"),
                "isPalindrome() should return true for single character");
    }

    /**
     * Test: isPalindrome() should return true for valid palindrome
     */
    @Test
    void isPalindrome_ShouldReturnTrue_WhenInputIsPalindrome() {
        assertTrue(StringUtils.isPalindrome("racecar"),
                "isPalindrome() should return true for 'racecar'");
        assertTrue(StringUtils.isPalindrome("madam"),
                "isPalindrome() should return true for 'madam'");
    }

    /**
     * Test: isPalindrome() should return false for non-palindrome
     */
    @Test
    void isPalindrome_ShouldReturnFalse_WhenInputIsNotPalindrome() {
        assertFalse(StringUtils.isPalindrome("hello"),
                "isPalindrome() should return false for 'hello'");
        assertFalse(StringUtils.isPalindrome("world"),
                "isPalindrome() should return false for 'world'");
    }

    /**
     * Test: isPalindrome() should be case-insensitive
     */
    @Test
    void isPalindrome_ShouldBeCaseInsensitive() {
        assertTrue(StringUtils.isPalindrome("RaceCar"),
                "isPalindrome() should be case-insensitive");
        assertTrue(StringUtils.isPalindrome("MadAm"),
                "isPalindrome() should be case-insensitive");
        assertFalse(StringUtils.isPalindrome("Hello"),
                "isPalindrome() should be case-insensitive");
    }
}