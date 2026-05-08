# Design Patterns Identification Lab

## Overview

In this lab, will learn to recognize when and why design patterns are useful in real-world code, rather than memorizing their implementations.

Begin with a sample code that doesn't use any patterns and contains common design issues. Then, identify how specific design patterns can address those issues and improve the code structure.

## Objective

At the end of this lab:

1. Identify common design patterns in existing code
2. Recognize code problems that patterns solve
3. Understand when and why patterns are needed

## Verify the environment and command-line tools

1. Open a new terminal: `Terminal > New Terminal`.
2. Verify that `java` is installed.
   `java --version`
3. Verify that Maven is installed.
   `mvn --version`

## Step 1: Create the application

1. Open a new terminal window by using the menu in the editor: `Terminal > New Terminal`.

2. If not currently in the project folder, copy and paste the following code to change into the project folder.
   `cd /home/project`

3. Run the following command to clone the Git repository, which contains the starter code need for the project, if the Git repository doesn't already exist.
   `git clone https://github.com/ibm-developer-skills-network/fyrui-identify-design-patterns identifylab`

4. Change to the directory to start working on the lab.
   `cd identifylab`

5. List the contents of this directory to see the artifacts for this lab.
   `ls`

## Step 2: Understand the sample code

### Sample code overview: E-commerce system

The sample code simulates a simplified e-commerce platform. It includes several components that interact with one another but also contain design issues. Here's a breakdown of what the code covers:

- **Database operations:** Manages database connections and executes queries for orders, customers, and products
- **Email services:** Sends email notifications to customers with configurable SMTP settings
- **Multi-channel notifications:** Handles email, SMS, and push notifications through a centralized notification manager
- **Order processing:** Processes customer orders and notifies multiple systems (email confirmation, inventory updates, and billing)
- **System integration:** Demonstrates how different components (database, email, notifications, and order processing) work together in a typical e-commerce workflow
- **Resource management:** Shows common issues like multiple database connections, complex object creation, and event handling that occur in real applications

The sample intentionally includes probelmatic patterns that highlight where design patterns can improve maintainability and extensibility.

## Step 3: Identify the problems

### Code Analysis Questions

#### Question 1

Look at the `DatabaseManager` class and how it is used in `ECommerceApp.java`.
In `ECommerceApp.java`, can see that three `DatabaseManager` objects are instantiated using the same database URL.

What problems are seen in this approach?

- **Answer**: Three separate database connections are created for the same database, wasting resources and potentially causing connection pool issues.

#### Question 2

Look at the `EmailService` constructor. What if you want to create many email services with similar configurations?

- **Answer**: Have to remember and repeat all four parameters every time. It's error prone and hard to maintain when configurations change.

#### Question 3

Look at the `NotificationManager.sendNotification()` method. What happens when you need to add a new notification type?

- **Answer**: Must notify the existing method, adding more if-else conditions. This violates the open-closed principle and makes the method grow longer.

#### Question 4

Look at the `OrderProcessor.processOrder()` method. How does it handle different types of listeners?

- **Answer**: It uses strings to identify listeners and has hardcoded if-else logic for each type. To add a new listener type, you'd need to modify this method, increasing maintenance overhead.

## Step 4: Pattern identification guide

### Pattern 1: Singleton Pattern

#### How to recognize

- Multiple instances of the same class doing the same job
- Resources that should be shared (database connections and configuration)
- Classes that manage global state

#### Key indicators in our code

- Multiple `DatabaseManager` instances connecting to the same database
- Each instance creates its own connection
- No coordination between instances

#### Questions to ask

- Do we really need multiple instances of this class?
- Would sharing one instance be better for performance/resources?
- Does this class manage something that should be unique in the application?

### Pattern 2: Builder Pattern

#### How to recognize

- Complex constructors with many parameters
- Optional parameters in object creation
- Need for step-by-step object construction

#### Key indicators in our code

- `EmailService` constructor has four parameters
- Hard to remember parameter order
- What if some parameters are optional?

#### Questions to ask

- Is the constructor becoming too complex?
- Are there optional parameters that cloud have defaults?
- Would step-by-step construction be clearer?

### Pattern 3: Factory pattern

#### How to recognize

- Long if-else chains for object creation
- Creating different objects based on input type
- Complex object creation logic scattered in code

#### Key indicators in our code

- `NotificationManager.sendNotification()` has if-else chain
- Object creation logic mixed with business logic
- Adding new types requires modifying existing code

#### Questions to ask

- Am I creating different objects based on a type parameter?
- Is object creation logic scattered throughout my code?
- Do I need to modify existing code when adding new types?

### Pattern 4: Observer pattern

#### How to recognize

- One event needs to trigger multiple actions
- String-based or primitive-based communication between objects
- Manual notification loops

#### Key indicators in our code

- `OrderProcessor` maintains a list of string listeners
- Manual loop to notify each listener
- String-based identification of listener types

#### Questions to ask

- Does one event need to notify multiple components?
- Am I using strings or primitives to represent different behaviors?
- Would objects be better than strings for representing different actions?

### Step 5: Pattern matching exercise

#### Match the problem to the pattern

For each code snippet, identify the design pattern that would solve the problem.

#### 1. Database connection issue -> Which pattern ensures only one database connection?

**Answer**: Singleton pattern - Ensures only one instance exists and provides global access to it.

#### 2. Email service creation -> Which pattern makes complex object creation easier?

**Answer**: Builder pattern - Allows step-by-step construction with optional parameters and readable code.

#### 3. Notification type handling -> Which pattern handles creating different objects based on type?

**Answer**: Factory Pattern - Creates objects based on input parameters without exposing creation logic.

#### 4. Order event handling -> Which pattern manages one-to-many event notifications?

**Answer**: Observer pattern - Allows multiple objects to be notified when an event occurs.

### Challenge

#### Scenario-based thinking

##### Challenge 1: The configuration dilemma

Your application needs a configuration manager that:

- Loads settings from a file once
- Is accessed by multiple classes throughout the application
- Should never have duplicate instances

Which pattern fits and why?

**Singleton Pattern** - The configuration should be loaded once and shared across the entire application. Multiple instances would waste memory and potentially cause inconsistency if the file is read multiple times.

##### Challenge 2: The payment processor

Your system needs to process payments via:

- Credit cards (Visa, MasterCard, and Amex)
- Digital wallets (PayPal, Apple Pay, and Google Pay)
- Bank transfers (ACH and Wire)
- Cryptocurrency (Bitcoin and Ethereum)

Each type has different initialization requirements and processing logic.

Which pattern helps organize this? What are the benefits?

**Factory Pattern** - Creates appropriate payment processor objects based on payment type. Benefits:

- Centralize object creation logic
- Easy to add new payment types
- Client code doesn't need to know specific implementation details
- Reduces coupling between client and concrete classes
- Single place to modify creation logic

### Conclusion

Throughout this lab:

1. Focused on problem first - Recongnized recurring issues in existing code
2. Developed recognition skills - Learned how to spot areas where patterns can improve structure
3. Understood pattern purpose - Matched common problems with the right design patterns
4. Applied real-world thinking - Explored how patterns often work together in practical scenarios

Design patterns are not just theoretical concepts. They offer practical solutions that improve maintainability, readability, and flexibility in real systems.
