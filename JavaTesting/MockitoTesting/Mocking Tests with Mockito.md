# Mocking Tests with Mockito Lab

## Overview

In this lab, will learn how to use Mockito to create mock objects for testing. Will build a simple order processing system and write tests using mocks to isolate the code from external dependencies.

- Java Version: Java 21
- Framework: JUnit 5 + Mockito

## Objectives

- Set up Mockito with Maven and JUnit 5
- Create and use mock objects
- Define mock behavior with stubbing
- Verfy method interactions
- Test business logic in isolation

## Verify the environment and command-line tools

1. Open a terminal window by using the menu in the editor: `Terminal > New Terminal`.

2. Verify that `java` is installed.
   `java --version`

3. Verify that Maven is installed.
   `mvn --version`

## Step 1: Create application

1. Open a new terminal window by using the menu in the editor: `Terminal > New Terminal`.

2. If not in the current project folder, copy and paste the following code to change into the project folder.
   `cd /home/project`

3. Run the following command to clone the Git repository that contains the starter code needed for this project if the Git repository doesn't already exist.
   `git clone https://github.com/ibm-developer-skills-network/wstgm-mocking-tests-with-mockito mockitolab`

4. Change to the directory to start working on the lab.
   `cd mockitolab`

5. List the contents of this directory to see the artifacts for this lab.
   `ls`

## Step 2: Check the pom.xml file

Let's understand the contents of the `pom.xml` file.

This is a `Maven POM (Project Object Model) file` - essentially a configuration file that tells Maven how to build and manage your Java project.

Here are the key points:

- **Project Identification** - The `<groupId></groupId>`, `<artifactId>`, and `<version>` tags uniquely identify the project, like giving it a full name and verison number that Maven uses to organize the code
- **Java Version Configuration** - The `<properties>` section sets the project to use Java 21 and UTF-8 encoding, telling Maven which version of Java to compile the code with
- **Test Dependencies** - The `<dependencies>` section automatically downloads JUnit 5(for writing tests), Mockito Core (for creating mocks), and Mockito JUnit extension (to make them work together) from the internet when building
- **Dependency Scope** - All testing libraries have `<scope>test</scope>`, which means they're only available when running tests, not in the main application code, keeping the final program lightweight
- **Build Plugins** - The `<plugin>` section configures Maven to compile the Java 21 code properly and run the tests using the Surefire plugin, essentially telling Maven how to build and test the project

## Step 3: Understand the domain classes

### Domain classes summary

The domain classes represent a simple e-commerce order processing system. **PaymentGateway** and **EmailService** are external services we don't want to call during testing (imagine charging real credit cards or sending actual emails!), while **OrderService** contains our business logic that coordinates between these services. We'll mock the external services to test our business logic safely.

### PaymentGateway interface

- **Purpose** - Represents an external payment processor (like PayPal or Stripe) that handles credit card transactions
- **Key Methods** - `processPayment()` charges money and `refundPayment()` returns money to customers
- **Why mock it** - Definitely don't want to charge real credit cards during testing! Mocking lets us simulate both successful and failed payments
- **Real-world example** - In production, this might call Stripe's API; in tests, we control whether payments succeed or fail

### EmailService interface

- **Purpose** - Represents an external email service (like SendGrid or AWS SES) for sending notifications to customers
- **Key methods** - `sendEmail()` for basic emails and `sendEmailWithSubject()` for emails with custom subject lines
- **Why mock it** - Prevents sending hundred of test emails to real customers during development and testing
- **Testing benefit** - We can verify the correct email address content was "sent" without actually sending anything

### OrderService class (Our business logic)

- **Purpose** - Contains the core business logic for processing customer orders, coordinating between payment and email services
- **Key methods** - `processOrder()` handles regular orders and `processPremiumOrder()` handles high-value orders with special treatment
- **Dependencies** - Takes PaymentGateway and EmailService as constructor parameters (dependency injection pattern)
- **What we test** - This is our main focus - we test that it handles payments correctly, sends appropriate emails, and validates input properly
- **Business flow** - Validates input -> processes payment -> sends confirmation email -> returns status message

## Step 4: Create basic tests for domain classes

Let's replace the content of "OrderServiceTest.java" with the following

`OrderServiceTest.java`

```
package com.example;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Test class for OrderService using Mockito mocks.
 * @ExtendWith(MockitoExtension.class) - Enables Mockito in JUnit 5
 * @Mock - Creates mock objects automatically
 * @InjectMocks - Automatically injects mocks into the test subject
 */
@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    // Mock objects - these are fake implementations that we control
    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private EmailService emailService;

    // The object we're testing - mocks will be automatically injected
    @InjectMocks
    private OrderService orderService;

    /**
     * Test 1: Basic successful order processing
     * This test verifies the happy path where everything works correctly
     */
    @Test
    void testSuccessfulOrder() {
        // ARRANGE - Set up test data and mock behavior
        String customerEmail = "john@example.com";
        double amount = 50.0;

        // Tell the payment gateway mock to return true when processPayment is called
        when(paymentGateway.processPayment(amount)).thenReturn(true);

        // ACT - Execute the method we're testing
        String result = orderService.processOrder(customerEmail, amount);

        // ASSERT - Verify the results
        assertEquals("Order processed successfully", result);

        // Verify that the email service was called with the correct parameters
        verify(emailService).sendEmail(customerEmail, "Order confirmed! Total: $" + amount);

        // Verify that payment gateway was called exactly once
        verify(paymentGateway, times(1)).processPayment(amount);
    }

    /**
     * Test 2: Failed payment handling
     * This test verifies that our service handles payment failures correctly
     */
    @Test
    void testFailedPayment() {
        // ARRANGE
        String customerEmail = "jane@example.com";
        double amount = 75.0;

        // Tell the payment gateway mock to return false (payment failed)
        when(paymentGateway.processPayment(amount)).thenReturn(false);

        // ACT
        String result = orderService.processOrder(customerEmail, amount);

        // ASSERT
        assertEquals("Payment failed", result);

        // Verify that NO email was sent since payment failed
        verify(emailService, never()).sendEmail(anyString(), anyString());

        // Verify payment was attempted
        verify(paymentGateway).processPayment(amount);
    }

    /**
     * Test 3: Input validation - invalid amount
     * This test verifies that our service validates input correctly
     */
    @Test
    void testInvalidAmount() {
        // ARRANGE
        String customerEmail = "test@example.com";
        double invalidAmount = -10.0;

        // ACT
        String result = orderService.processOrder(customerEmail, invalidAmount);

        // ASSERT
        assertEquals("Invalid amount", result);

        // Verify that neither payment nor email services were called
        verify(paymentGateway, never()).processPayment(anyDouble());
        verify(emailService, never()).sendEmail(anyString(), anyString());
    }

    /**
     * Test 4: Input validation - invalid email
     */
    @Test
    void testInvalidEmail() {
        // ARRANGE
        String invalidEmail = "";
        double amount = 50.0;

        // ACT
        String result = orderService.processOrder(invalidEmail, amount);

        // ASSERT
        assertEquals("Invalid email", result);

        // Verify no external services were called
        verify(paymentGateway, never()).processPayment(anyDouble());
        verify(emailService, never()).sendEmail(anyString(), anyString());
    }
}
```

### Understanding the tests

**OrderServiceTest** is our test class that verifies the **OrderService** business logic works correctly without calling real external services. Think of it as a "controlled laboratory" where we can simulate different scenarios safely.

### Test class setup

- `@ExtendWith(MockitoExtension.class)` - Tells JUnit 5 to use Mockito for creating fake objects
- `@Mock` annotations create "stunt doubles" for PaymentGateway and EmailService that we control
- `@InjectMocks` automatically gives OrderService these fake objects so we can test it in isolation

### Test method pattern (AAA structure)

Each test follows the same simple pattern:

- **ARRANGE** - Set up test data and tell mocks how to behave using `when().thenReturn()`
- **ACT** - Call the method we're actually testing (like `processOrder()`)
- **ASSERT** - Check the result and verify our mocks were called correctly using `assertEquals()` and `verify()`

### What each test does

- **testSuccessfulOrder()** - Happy path: payment succeeds, email gets sent, proper success message returned
- **testFailedPayment()** - Payment fails, no email sent, failure message returned
- **testInvalidAmount()** - Bad input validation, no external services called
- **Advanced tests** - Show argument matchers (`anyString()`), multiple behaviors, and premium order handling

### Key Mockito concepts in action

- **Stubbing** - `when(paymentGateway.processPayment(50,0)).thenReturn(true)` - "When someone calls this method with 50.0, return true"
- **Verification**: `verify(emailService).sendEmail(...)` - "Make sure the email service was called with these exact parameters"
- **Never called**: `verify(emailService, never()).sendEmail(...)` - "Make sure email was NOT send when payment failed"

The beauty is that we're testing real business logic without touching real payment systems or sending real emails!

### Run tests

`mvn test`

The test should return all tests have bassed and build succeeds

## Challenge - Implement OrderCancellation Mock Test (Optional)

### The problem

- Add a new feature to the OrderService: Order Cancellation
- The task is to implement order cancellation with the following requirements:
- Add a cancelOrder(String orderId, String customerEmail, double amount) method to OrderService

### This method should

1. Validate the orderId(not null/empty)
2. Call paymentGateway.refundPayment(orderId, amount)
3. If refund succeeds, send cancellation email
4. Return appropriate status messages

### Write comprehensive tests covering

1. Successful cancellation
2. Failed refund
3. Invalid order ID
4. Edge cases

### Hints for implementation

Add the method to OrderService:

1. Think about validation first
2. Handle the refund success/failure cases
3. Send appropriate emails

### Testing strategy

1. Test the happy path first
2. Test failure scenarios
3. Test input validations
4. Use argument matchers where appropriate

### Mock setup

1. Will need to stub paymentGateway.refundPayment()
2. Verify the correct email is sent

### Exemplar code for reference

#### Add this method to OrderService.java

```
/**
 * Cancels an order and processes refund
 * @param orderId the order to cancel
 * @param customerEmail customer's email address
 * @param amount amount to refund
 * @return status message
 */
public String cancelOrder(String orderId, String customerEmail, double amount) {
    // Validate order ID
    if (orderId == null || orderId.trim().isEmpty()) {
        return "Invalid order ID";
    }

    // Validate email
    if (customerEmail == null || customerEmail.trim().isEmpty()) {
        return "Invalid email";
    }

    // Validate amount
    if (amount <= 0) {
        return "Invalid amount";
    }

    // Try to process refund
    boolean refundSuccess = paymentGateway.refundPayment(orderId, amount);

    if (refundSuccess) {
        // Refund successful - send cancellation email
        emailService.sendEmail(customerEmail, "Order " + orderId + " has been cancelled. Refund of $" + amount + " processed.");
        return "Order cancelled successfully";
    } else {
        // Refund failed
        return "Refund failed";
    }
}
```

#### Implementation in OrderServiceTest.java

```
@Test
void testSuccessfulOrderCancellation() {
    // ARRANGE
    String orderId = "ORDER-123";
    String customerEmail = "customer@example.com";
    double amount = 75.0;

    when(paymentGateway.refundPayment(orderId, amount)).thenReturn(true);

    // ACT
    String result = orderService.cancelOrder(orderId, customerEmail, amount);

    // ASSERT
    assertEquals("Order cancelled successfully", result);

    verify(paymentGateway).refundPayment(orderId, amount);
    verify(emailService).sendEmail(customerEmail, "Order " + orderId + " has been cancelled. Refund of $" + amount + " processed.");
}

@Test
void testFailedRefund() {
    // ARRANGE
    String orderId = "ORDER-456";
    String customerEmail = "customer@example.com";
    double amount = 50.0;

    when(paymentGateway.refundPayment(orderId, amount)).thenReturn(false);

    // ACT
    String result = orderService.cancelOrder(orderId, customerEmail, amount);

    // ASSERT
    assertEquals("Refund failed", result);

    verify(paymentGateway).refundPayment(orderId, amount);
    verify(emailService, never()).sendEmail(anyString(), anyString());
}

@Test
void testCancelOrderWithInvalidOrderId() {
    // ARRANGE
    String invalidOrderId = "";
    String customerEmail = "customer@example.com";
    double amount = 50.0;

    // ACT
    String result = orderService.cancelOrder(invalidOrderId, customerEmail, amount);

    // ASSERT
    assertEquals("Invalid order ID", result);

    verify(paymentGateway, never()).refundPayment(anyString(), anyDouble());
    verify(emailService, never()).sendEmail(anyString(), anyString());
}
```

Run the following command in the terminal
`mvn test`

## Conclusion

Have completed a Mockito lab in which:

- Set up Mockito with Maven and JUnit 5
- Created mock objects for external dependencies
- Stubbed mock behavior with when().thenReturn()
- Verified method calls with verify()
- Used argument matchers for flexible testing
- Tested both success and failure scenarios
- Isolated business logic from external services
