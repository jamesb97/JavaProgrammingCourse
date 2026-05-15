# Writing Your First JUnit Test Lab

## Overview

In this lab, will learn how to write and run automated tests for Java code using JUnit 5 and AssertJ, which are industry-standard testing tools. Will practice writing test methods that verify if the code works correctly and learn to use Maven to manage dependencies and execute tests.

This builds the foundation for test-driven development and quality assurance practices used in professional Java development.

## Objectives

At the end of the lab, will be able to

- Write unit tests using JUnit 5 annotations such as `@Test`, `@BeforeEach`, and `@AfterEach`
- Structure test methods using the Arrange-Act-Assert pattern
- Replace basic assertions with AssertJ's fluent style like, `assertThat(result).isEqualTo(expected)`
- Get familiar with AssertJ assertions such as `isNotNull()`, `contains()`, and `hasSize()`
- Test various data types, including strings, collections, objects, and exceptions

## Verify the environment and command-line tools

1. Open a terminal window by using the menu in the editor: `Terminal > New Terminal`

2. Verify that `java` is installed
   `java --version`

3. Verify that Maven is installed
   `mvn --version`

## Step 1: Create the application

1. Open a terminal window by using the menu in the editor: `Terminal > New Terminal`

2. If not in the current project folder, copy and paste the following code to change into the project folder.
   `cd /home/project`

3. Run the following command to clone the Git repository that contains the starter code needed for this project if the Git repository doesn't already exist.
   `git clone https://github.com/ibm-developer-skills-network/klylh-writing-your-first-junit-test junitlab`

4. Change into the directory to start working on the lab
   `cd junitlab`

5. List the contents of this directory to see the artifacts for the lab
   `ls`

## Step 2: Check the pom.xml file

In this step, will review the contents of the `pom.xml` file to understand how it manages the project's dependencies and build process.

This is a `Maven Project Object Model (pom.xml)` file. It acts as a configuration file that tells Maven how to build, test, and manage Java project.

Here are the key sections

### Basics

- Defines the projects identify as junit5-assertj-demo version 1.0.0
- Sets Java version to 11
- Describes the project purpose as a JUnit 5 and AssertJ demo

### Key Dependencies (Libraries)

- Adds `JUnit 5` as the main testing framework
- Uses `AssertJ` for fluent, readable assertions like `assertThat(result).isEqualTo(5)`
- Includes `Mockito` for mocking dependencies during testing
- Marks all libraries as `test` scope to ensure they run only during tests

### Build plugins (Important tools)

- Uses the Compiler plugin to compile Java code
- Runs unit tests with the Surefire plugin, supporting parallel execution
- Manages integration tests using the Failsafe plugin

## Step 3: Understand Calculator class

Let's explore the contents of the `Calculator` class to understand its structure and purpose

This is a simple Java class called `Calculator` that demonstrates the calculator functions.

### What this class does

This class defines a basic calculator that performs common arithmetic operations, similar to a simple digital calculator.

### Important aspects of this class

- Package declaration: `package com.example` organizes the code into a folder structure.
- Class definition: `public class Calculator` creates a blueprint for a reusable calculator object.
- Method types: The class includes five methods:
- `add()` - Adds two numbers
- `subtract()` - Subtracts one number from another
- `multiply()` - Multiplies two numbers
- `divide()` - Divides one number by another
- `isEven()` - Checks if a number is even

- `Error Handling`: THe `divide()` method checks for divide-by-zero errors and throws an exception instead of failing silently.

- Return types:

- Most methods return `int`
- `divide()` returns double
- `isEven()` returns boolean

`Access level`: All methods are `public`, meaning they can be used from other parts of the program.

## Step 4: Understand Calculator tests

Let's review the content of the `CalculatorTest` class with the following

This Java file defines a set of tests for verifying the behavior of the `Calculator` class using JUnit5 and AssertJ.

### Breakdown of the CalculatorTest structure

#### Structure and purpose of CalculatorTest

- Test class: `CalculatorTest` contains unit tests for all methods in the `Calculator` class

- Frameworks used:

- `JUnit5` - provides annotations and structure for test execution
- `AssertJ` - allows expressive and readable assertions

#### Setup methods

- `@BeforeEach` - Instantiates a fresh `Calculator` object before each test
- `@AfterEach` - (optional) Cleans up resources after each test

#### Core tests

- `testAdd()` - Verifies that `add(2,3)` returns 5
- `testSubtract()` - Verifies that `subtract(5,3)` returns 2
- `testMultiply()` - Verifies that `multiply(4, 5)` returns 20
- `testDivide()` - Asserts correct division output
- `testDivideByZero()` - Confirms an exception is thrown with the expected message when dividing by zero

#### Parameterized tests

- `@ValueSource` - Runs `isEven()` for multiple even values
- `@CsvSource` - Runs multiple `add()` test cases with different inputs and expected results
- Parameterized tests improve test coverage without code repetition

#### Assertion Styles

- `assertThat(result).isEqualTo(expected)` - Fluent syntax using AssertJ
- `assertThatThrownBy()` - Validates that the correct exception and message are thrown

## Step 5: Run the application

In this step, will run the test suite using Maven to verify that the `Calculator` class behaves as expected.

Run the tests

Use the following command in the terminal:
`mvn test`

This command tells Maven to compile the test classes and run all the unit tests defined in the project.

### Expected output

Should see an output similar to the following, including:

- Correct exception message when dividing by zero
- Parameterized test results with different values
- Successful results for all valid operations
- Summary showing that all tests passed

This will show that the tests have been successful as defined in the tests using Junit and AssetJ.

## Challenge (Optional)

### Square root challenge

The task is to extend the `Calculator` class by adding a new method for calculating square roots, then write tests to verify it behaves correctly.

### What needs to be added

Add this method to the `Calculator.java` file:

`public double sqrt(double number)`

### Requirements

- Return the square root of the input number
- Throw an `IllegalArgumentException` for negative input
- Use the error message: "Cannot calculate square root of negative number!"
- Write tests for all edge cases and typical scenarios

### Hints

#### Implementation

- Uses Java's built-in `Math.sqrt()` function
- Add input validation before using `Math.sqrt()`
- Handle the special case when input is 0

#### Testing

- Perfect squares: √16 = 4.0, √25 = 5.0
- Non-perfect squares: √2 ~ 1.414
- Edge cases: √0 = 0.0, √1 = 1.0
- Exception case: √(-4) should raise `IllegalArgumentException`
- Use `assertThat(result).isCloseTo(expected, within(0.01))` for decimal comparison

#### Test cases to consider

- √16 should equal 4.0
- √25 should equal 5.0
- √2 should be approximately 1.414
- √0 should equal 0.0
- √1 should equal 1.0
- √(-4) should throw `IllegalArgumentException`

#### Try it

1. Add the method to `Calculator.java`
2. Write the tests in `CalculatorTest.java`
3. Run the tests and make sure that they all pass!

## Conclusion

Have successfully written and tested a working `Calculator` using JUnit5 and AssertJ.
Practiced core Java concepts, structured test methods, handled exceptions, and applied test-driven practices.

In this lab:

- Explored the structure and role of `pom.xml` for Java testing
- Analyzed the core logic in the `Calculator` class
- Understood how to structure and run JUnit5 tests using AssertJ
- Practiced writing parameterized tests and exception validations
- Executed tests using Maven and reviewed the output
- Extended functionality by adding and testing new `sqrt()` method
