# Creating a Singleton Class Printer Manager Lab

## Overview

In this lab, will learn how to write and run a singleton class.

Imagine your office has one printer that everyone shares. You don't want multiple "printer managers" running at the same time because that would cause confusion; print jobs could get mixed up! The Singleton pattern ensures only one PrinterManager exists, just like having one person in charge of the office printer.

## Objectives

- Explain the Singleton design pattern
- Identify when shared resources need controlled access
- Implement a basic Singleton pattern
- Demonstrate Singleton behavior

## Verify the environment and command-line tools

1. Open a new terminal window by using the menu in the editor: `Terminal > New Terminal`.
2. Verify that `java` is installed.
   `java --version`
3. Verify that Maven is installed.
   `mvn --version`

## Step 1: Create the application

1. Open a new terminal window by using the menu in the editor: `Terminal > New Terminal`.
2. If not in the current project folder, copy and paste the following code to change into the project folder.
   `cd /home/project`
3. Run the following command to clone the Git repository that contains the starter code needed for this project if the Git repository doesn't already exist.
   `git clone https://github.com/ibm-developer-skills-network/jsior-design-patterns-singleton singletonlab`
4. Change to the directory to start working on the lab.
   `cd singletonlab`
5. List the contents of this directory to see the artifacts for this lab.
   `ls`

## Step 2: Check on pom.xml file

In this step, review the contents of the `pom.xml` file and understand how it defines the project settings.

This is a Maven POM (Project Object Model) file, which acts as a configuration file to define how Maven builds and manages the Java project.

### Project settings in pom.xml

The following settings in the `pom.xml` file defines how Maven manages the project

- **Group ID:** Defines the organization (com.example)
- **Artifact ID:** Names the project (printer-singleton-lab)
- **Version:** Sets the version (1.0.0)
- **Java 21:** Uses the latest Java version
- **JUnit 5:** Includes the latest stable test framework
- **Exec plugin:** Enables running the main class using mvn exec:java

## Step 3: The PrinterManager (Singleton)

In this step, will explore how the `PrinterManager` class forms the core of the Singleton pattern. It represents a single manager responsible for controlling access to a shared printer, preventing chaos that would happen if multiple people tried to manage the same printer simultaneously.

### Features of the `PrinterManager` class

The following features of the `PrinterManager` class demonstrate how it implements the Singleton pattern:

- **Package declaration:** Organizes code in the com.example.printer package
- **Private constructor:** Prevents direct instantiation using new PrinterManager()
- **Static method:** Returns the single instance using getManager()
- **Single instance:** Guarantees that only one manager controls the printer
- **Getter methods:** Provides accessors for unit testing

## Step 4: Understand OfficeTest class

In this step, review the `OfficeTest` class to see how it demonstrates the behavior of the Singleton pattern in real-world scenario.

### What this class does

`OfficeTest.java` is our demonstration program that provides the Singelton pattern actually works.

### Action performed in the `OfficeTest` class

The following actions in the OfficeTest class demonstrate the Singleton pattern in action:

- **Simulates an office scenario:** Models two employees, Alice and Bob, sharing a single printer
- **Creates the first instance:** Alice calls `PrinterManager.getManager()` to get the Singleton and print her report
- **Reuses the same instance:** Bob also calls `PrinterManager.getManager()` and receives the same object
- **Verifies identity:** Uses == to confirm both references point to the same object in memory
- **Displays memory addresses:** Prints `hashCode()` values to show they match
- **Validates Singelton behavior:** Confirms all users get the same instance regardless of who calls it
- **Provides feedback:** Prints success messages to demonstrate expected output

## Step 5: Create unit tests

In this step, will review the `PrinterManagerTest` class to understand how unit tests are used to verify the Singleton pattern.

### What this class does

`PrinterManagerTest.java` is our automated quality checker that runs tests to verify our PrinterManager works correctly.

### Unit tests in the `PrinterManagerTest` class

- **Runs automated tests:** Uses JUnit to verify Singleton behavior without manual steps
- **Verifies shared instance:** Checks that multiple calls to `getManager()` return the same object
- **Confirms consistent hash codes:** Validates that identical instances produce the same `hashCode()`
- **Checks initial state:** Ensures the printer starts idle and no jobs are assigned
- **Uses assertions:** Applies methods like `assertSame(), assertEquals(), and assertFalse()` to confirm expected results
- **Detects implementation issues:** Flags failures if the Singleton pattern is broken (e.g., public constructor)
- **Generates test reports:** Provides output such as "Test run: 3, Failures: 0" when `mvn tests` is executed

## Step 6: Run the application

In this step, compile the code, run the tests, and then execute the main application to observe the Singleton behavior.

### Compile the code

- Run the following command in the terminal
  `mvn compile`

### Run tests

- Execute the following command to run all unit tests
  `mvn test`

### Expected output

- Should see a build success message where all tests passed

### Run the main application

- Use the following command to run the OfficeTest demonstration
  `mvn exec:java`

### Expected output

- Should see a build success message

## Challenge

### Objective

In this challenge, the goal is to enhance the `PrinterManager` class by adding a job counter that tracks how many documents have been printed.

#### Add a new variable to count jobs

```
// Add this with your other variables
private int totalJobsPrinted = 0;
```

#### Increment the counter when printing

```
// Add this line inside printDocument() method when printing starts
totalJobsPrinted++;
```

#### Example implementation

Step 1: Add this variable to PrinterManager.java (with the other variables)

```
private int totalJobsPrinted = 0;
```

Step 2: Add this line inside `printDocument()` method (right after printing starts)

```
// Add this line after: System.out.println("🖨️ Printing: " + document + " for " + person);
totalJobsPrinted++;
System.out.println("📊 Job #" + totalJobsPrinted + " completed");
```

Step 3: Add this new method to PrinterManager.java

```
public int getTotalJobsPrinted() {
    return totalJobsPrinted;
}
```

Step 4: Test it by adding this to the end of OfficeTest.java `main()` method

```
// Add before the final success message
System.out.println("\n📊 Printing Statistics:");
System.out.println("Total jobs printed today: " + aliceManager.getTotalJobsPrinted());
```

#### Expected Output

This enhancement shows how the Singleton pattern can maintain shared state across the entire application.

## Conclusion

Have successfully bulit a working Singleton class.

In this lab

- Explored the need for managing shared resources using the Singleton pattern
- Implemented a Singelton with a private constructor and static method
- Verified Singleton behavior using both manual testing and unit tests
- Extended the design by maintaining shared state across users
