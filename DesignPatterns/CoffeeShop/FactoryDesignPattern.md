# Factory Design Pattern Coffee Shop Lab

## Overview

In this lab, will learn how to write and apply the Factory Design pattern by building a simple coffee shop system.

## Objectives

- Explain the Factory design pattern and its core components
- Implement the Factory Pattern using modern Java syntax
- Apply object-oriented principles such as loose coupling and polymorphism
- Demonstrate how the Factory pattern improves extensibility and maintainability

## Verify the environment and command-line tools

1. Open a new terminal window by using the menu in the editor: `Terminal > New Terminal`.

2. Verify that `java` is installed.
   `java --version`

3. Verify that Maven is installed.
   `mvn --version`

## Step 1: Create the application

1. Open a terminal window by using the menu in the editor. `Terminal > New Terminal`.
2. If not in the current project folder, copy and paste the following code to change into the project folder.
   `cd /home/project`
3. Run the following command to clone the Git repository that contains the starter code needed for the project.
   `git clone https://github.com/ibm-developer-skills-network/egdqh-factory-design-pattern factorylab`
4. Change to the directory to start working on the lab.
   `cd factorylab`
5. List the contents of this directory to see the artifacts for this lab.
   `ls`

## Step 2: Check pom.xml file

In this step, will review the contents of the `pom.xml` file in order to understand how it configures the project.

The `pom.xml` file is a `Maven Project Object Model (POM)` file that defines how Maven builds and manages the Java project.

### Project settings in `pom.xml` file

- Configure a basic Maven project for Java 21
- Set `maven.compiler.source` and `target` to use Java 21 features
- Enable command-line execution using the `exec-maven-plugin`
- Specify the main class using `mainClass`
- Use only core Java libraries with no external dependencies

### Step 3: Create product interface

In this step, will review the `Coffee` interface to understand how it defines common behavior for all coffee types.

#### What this interface does

The `Coffee` interface defines the contract that all coffee types must follow in the Factory pattern.

#### Features of the `Coffee` interface

- Serves as the product interface in the Factory pattern
- Declares two required methods: `brew()` and `getPrice()`
- Ensures consistency across all coffee types
- Allows client code to work with coffee objects without knowing their specific types
- Promotes loose coupling between the factory and its concrete products
- Includes only essential methods needed for the coffee shop scenario

## Step 4: Create coffee types

### Espresso class

In this step, will review the `Espresso` class to see how it implements the Coffee interface as a concrete product.

### What this class does

This class defines a specific type of coffee by implementing the shared Coffee interface.

#### Features of the `Espresso` class

- Implements the `Coffee` interface as a concrete product in the Factory pattern
- Uses `@Override` annotations to ensure correct method implementation
- Provides specific brewing logic in the `brew()` method
- Returns a fixed price of $2.50 using `getPrice()`
- Encapsulates its behavior and properties as a standalone coffee type
- Is created by the factory when "espresso" is requested

## Step 5: Create factory

In this step, will review the `CoffeeFactory` class to see how it centralizes object creation using the Factory pattern.

### What this class does

This class defines the factory responsible for creating coffee objects based on the user input.

### Features of the `CoffeeFactory` class

The factory hides the object creation details. Client doesn't need to know about `new Espresso()` or `new Latte()`.

- Implements the Factory pattern by encapsulating object creation logic
- Defines a static method so the client can call `CoffeeFactory.createCoffee()` without instantiating the factory
- Uses Java21 `switch` expressions with `yield` to return the correct coffee object
- Applies `toLowerCase()` to make input case-insensitive
- Returns the `Coffee` interface type to promote loose coupling
- Returns `null` for unknown input to allow graceful error handling in the client code

## Step 6: Create the main application

In this step, will examine the `CoffeeShop` class to see how the client uses the factory to order different types of coffee.

### What this class does

This class represents the client code that demonstrates how to use the factory to create and work with coffee objects.

### Features of the `CoffeeShop` class

Note: The `CoffeeShop` class never uses `new Espresso()` or `newLatte()`. It relies entirely on the factory to create objects.

- Represents the client that interacts with the factory instead of directly creating objects
- Uses the `main()` method to demonstrate different coffee orders, including an invalid case
- Defines the `orderCoffee()` method to delegate creation to `CoffeeFactory.createCoffee()`
- Follows the key principle: client never uses `new Espresso()` or `newLatte()`
- Checks for `null` to handle unsupported coffee types gracefully
- Works entirely with the `Coffee` interface without referencing specific classes

## Step 7: Run the application

In this step, will compile the code and run the application to observe how the factory creates different coffee types.

### Compile the code

Use the following command to compile the code
`mvn compile`

### Run the application

Use the following command to run the application
`mvn exec:java`

## Challenge

In this optional challenge, will extend the coffee shop system by adding support for a new coffee type: cappuccino. This exercise demonstrates how the Factory pattern makes it easy to scale your application without modifying existing client code.

### Step 1: Create `Cappuccino.java`

- Implement `Coffee` interface
- Use the brewing message: "Making Cappuccino with foam art"
- Set price to `$3.50`

### Step 2: Update `CoffeeFactory.java`

- Add a `case` for "cappuccino" in the switch statement

### Step 3: Test your code

- Call `orderCoffee("cappuccino")` in the `main()` method of `CoffeeShop`

### Hints

- Copy the structure of `Latte.java` and modify it
- Add one line in the factory switch block
- Test with `orderCoffee("Cappuccino")

## Conclusion

Have successfully implemented the Factory pattern in a working coffee shop application.

In this lab

- Defined a common product interface (`Coffee`) and implemented multiple concrete classes
- Created a factory class (`CoffeeFactory`) to centralize object creation
- Used the factory in client code (`CoffeeShop`) without directly instantiating products
- Extended the system by adding a new product (`Cappuccino`) without modifying existing client logic
