# Developing String Utility with TDD Lab

## Overview

In this lab, will apply the fundamentals of Test-Driven-Development (TDD) by building a `StringUtils` class. Will follow the TDD workflow known as the Red-Green-Refactor cycle:

- `Red`: Write a failing test that defines a desired improvement
- `Green`: Write the minimum code required to make the test pass
- `Refactor`: Improve the existing implementation while keeping all tests passing

## Objectives

By the end of this lab:

1. Apply the TDD cycle (Red-Green-Refactor) to develop a Java utility class
2. Write unit tests using JUnit 5 with annotations, assertions, and the Given-When-Then structure
3. Implement Java methods that pass tests before writing complete logic
4. Refactor working code without breaking tests
5. Explain how writing tests first improves method design and error handling
6. Evaluate how automated tests enable safe, incremental code changes

## Verify the environment and command-line tools

1. Open a new terminal window by using the menu in the editor: `Terminal > New Terminal`

2. Verify that `java` is installed
   `java --version`

3. Verify that maven is installed
   `mvn --version`

## Step 1: Create the application

1. Open a new terminal window by selecting `Terminal > New Terminal` from the menu

2. If not already in the project directory, navigate to it using the following command:
   `cd /home/project`

3. Clone the git repository containing the starter code
   `git clone https://github.com/ibm-developer-skills-network/kojlu-tdd-string-utility tddlab`

4. Move into the project directory
   `cd tddlab`

5. List the contents of the directory to verify that the files were cloned successfully:
   `ls`

## Step 2: Check the pom.xml file

Let's review the contents of the `pom.xml` file

The `pom.xml` file is a `Maven Project Object Model (POM)` file. It defines how Maven builds and manages the Java project.

### Key elements include

#### Maven properties

- Sets Java 21 as the source and target version
- Specifies the JUnit 5 version

#### JUnit dependencies

- `junit-jupiter-api`: Provides annotations and assertions for writing tests
- `junit-jupiter-engine`: Executes the tests at runtime

#### Maven plugins

- `maven-compiler-plugin`: Compiles the code using Java 21
- `maven-surefire-plugin`: Runs the unit tests during the build

## Step 3: First Test - isEmpty() Method (RED)

Replace the contents of `StringUtilsTest.java` with the following code

```
package com.example.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for StringUtils following TDD principles.
 * Each test method follows the pattern: Given-When-Then
 */
class StringUtilsTest {

    /**
     * Test: isEmpty() should return true for null string
     * This is our first RED test - it will fail because StringUtils doesn't exist yet
     */
    @Test
    void isEmpty_ShouldReturnTrue_WhenStringIsNull() {
        // Given: a null string
        String input = null;

        // When: checking if string is empty
        boolean result = StringUtils.isEmpty(input);

        // Then: result should be true
        assertTrue(result, "isEmpty() should return true for null string");
    }

    /**
     * Test: isEmpty() should return true for empty string
     */
    @Test
    void isEmpty_ShouldReturnTrue_WhenStringIsEmpty() {
        // Given: an empty string
        String input = "";

        // When: checking if string is empty
        boolean result = StringUtils.isEmpty(input);

        // Then: result should be true
        assertTrue(result, "isEmpty() should return true for empty string");
    }

    /**
     * Test: isEmpty() should return false for non-empty string
     */
    @Test
    void isEmpty_ShouldReturnFalse_WhenStringHasContent() {
        // Given: a string with content
        String input = "Hello";

        // When: checking if string is empty
        boolean result = StringUtils.isEmpty(input);

        // Then: result should be false
        assertFalse(result, "isEmpty() should return false for non-empty string");
    }
}
```

Save the file, then run the test using the following command:
`mvn test`

The test will fail because the StringUtils class does not exist yet. The completes the **RED** phase.

## Step 4: Minimal implementation (GREEN)

Replace the contents of `StringUtils.java` with the following code

```
package com.example.utils;

/**
 * Utility class for common string operations.
 * Built using Test-Driven Development principles.
 */
public class StringUtils {

    /**
     * Private constructor to prevent instantiation of utility class
     */
    private StringUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Checks if a string is null or empty.
     *
     * @param str the string to check
     * @return true if string is null or empty, false otherwise
     */
    public static boolean isEmpty(String str) {
        // Minimal implementation to make tests pass
        return str == null || str.isEmpty();
    }
}
```

Save the file, then run the tests again:
`mvn test`

Should now see test results indicating that all tests have passed.
All tests have passed. This completes the GREEN phase.

## Step 5: Add reverse() method (RED-GREEN-REFACTOR)

Add the following test methods to `StringUtilsTest.java` to validate the behavior of a new `reverse()` method.

Open the `StringUtilsTest.java` file

```
/**
 * Test: reverse() should return null for null input
 */
@Test
void reverse_ShouldReturnNull_WhenInputIsNull() {
    // Given: a null string
    String input = null;

    // When: reversing the string
    String result = StringUtils.reverse(input);

    // Then: result should be null
    assertNull(result, "reverse() should return null for null input");
}

/**
 * Test: reverse() should return empty string for empty input
 */
@Test
void reverse_ShouldReturnEmpty_WhenInputIsEmpty() {
    // Given: an empty string
    String input = "";

    // When: reversing the string
    String result = StringUtils.reverse(input);

    // Then: result should be empty
    assertEquals("", result, "reverse() should return empty string for empty input");
}

/**
 * Test: reverse() should reverse normal string
 */
@Test
void reverse_ShouldReverseString_WhenInputIsValid() {
    // Given: a normal string
    String input = "hello";

    // When: reversing the string
    String result = StringUtils.reverse(input);

    // Then: result should be reversed
    assertEquals("olleh", result, "reverse() should return reversed string");
}
```

### Run tests (RED)

Save the file, then run the test suite:

`mvn test`

Should now see the test results indicating that the new tests fail because the reverse() method is not yet implemented.

The test will fail because the `reverse()` method does not exist. This confirms the RED phase is complete.

## Step 6: Implement reverse() method in StringUtils.java

Add the following method to `StringUtils.java`

```
/**
 * Reverses a string.
 *
 * @param str the string to reverse
 * @return the reversed string, or null if input is null
 */
public static String reverse(String str) {
    // Handle null input
    if (str == null) {
        return null;
    }

    // Handle empty string
    if (str.isEmpty()) {
        return "";
    }

    // Reverse the string using StringBuilder for efficiency
    return new StringBuilder(str).reverse().toString();
}
```

### Run tests (GREEN)

Save the file, then run the test suite:
`mvn test`

Should now see test results showing that all tests pass, including the ones for the reverse() method.

## Optional Challenge - Implement isPalindrome() method

### Add Palindrome Method

A palindrome is a string that reads the same forwards and backwards (e.g., "racecar", "madam").
In this challenge, the goal is to build an `isPalindrome()` method using the TDD approach practiced so far.

Begin by writing tests for the method, then create a minimal implementation to make them pass, and finally refactor if needed, all while keeping tests green.

### Suggested steps

1. Write tests for edge cases:

- Null input
- Empty string
- Single-character input

2. Add tests for typical cases:

- Palindromes like "racecar" or "madam"
- Non-palindrome like "hello" or "world"

3. Decide how to handle special cases:

- Should "RaceCar" be treated the same as "racecar"? (Case sensitivity)
- Should spaces and punctuation be ignored? (e.g., "A man a plan a canal Panama")

### What to do

- Add test methods to `StringUtilsTest.java`
- Implement the `isPalindrome()` method in `StringUtils.java`
- Follow the same TDD cycle: write failing tests, add minimal implementation, and refactor if needed

### Sample test scaffolding

Start with these basic test cases to think through your design. Add the following to `StringUtilsTest.java`

```
@Test
void isPalindrome_ShouldReturnTrue_WhenStringIsNull() {
    // What should happen with null? Design decision needed!
}

@Test
void isPalindrome_ShouldReturnTrue_WhenStringIsPalindrome() {
    assertTrue(StringUtils.isPalindrome("racecar"));
}

@Test
void isPalindrome_ShouldReturnFalse_WhenStringIsNotPalindrome() {
    assertFalse(StringUtils.isPalindrome("hello"));
}
```

### Sample solution

`StringUtilsTest.java`

```
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
}
```

### Implementation in StringUtils.java

Add the following method to `StringUtils.java`

```
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
```

Run the test suite to verify the solution
`mvn test`

If implemented correctly, all the tests for isPalindrome() should now pass.

## Conclusion

Have successfully practiced TDD by writing failing tests (RED), adding just enough code to pass (GREEN), and safely refactoring the implementation.

In this lab

- Followed the Test-Driven Development (TDD) cycle using Red-Green-Refactor
- Written failing unit tests before implementation
- Developed the `StringUtils` utility class using Java and JUnit 5
- Implemented and tested methods like `isEmpty()`, `reverse()`, and `isPalindrome()`
- Refactored code while keeping tests passing
- Observed how writing tests first improves design and reliability
