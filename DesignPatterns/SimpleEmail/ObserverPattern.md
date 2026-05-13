# Simple Email Notifications Observer Pattern

## Overview

In this lab, will learn how to write and use the observer design pattern by building a simple email notification system, where when a new email arrives, multiple components (inbox counter and popup) get notified automatically.

## Objectives

- Analyze the observer pattern fundamentals and automatic notification mechanisms
- Implement loose coupling in Java for independent email notification components
- Design Java 21 interfaces for clean observer-subject communication
- Demonstrate automatic event notification triggering multiple simultaneous responses
- Apply the observer pattern to build a real-world email notification system

## Verify the environment and commmand-line tools

1. Open a terminal window by using the menu in the editor: `Terminal > New Terminal`

2. Verify that `java` is installed
   `java --version`

3. Verify that Maven is installed
   `mvn --version`

## Step 1: Create the application

1. Open a new terminal window by using the menu in the editor: `Terminal > New Terminal`

2. If not currently in the project folder, copy and paste the following code to change in the project folder.
   `cd /home/project`

3. Run the following command to clone the Git repository that contains the starter code needed for this project.
   `git clone https://github.com/ibm-developer-skills-network/gxefn-observer-design-pattern observerlab`

4. Change to the directory to start working on the lab.
   `cd observerlab`

5. List the contents of this directory to see the artifacts for this lab.
   `ls`

## Step 2: Check the pom.xml file

Let's understand the content inside of the `pom.xml` file

This file is a `Maven Project Object Model(POM)` file that defines how the project is built and configured using Java 21 and Maven.

Here are the key points

- Uses Java 21 with the latest Maven compiler plugin (3.14.0)
- Simple configuration with no external dependencies
- Uses `release` property for proper Java 21 support

## Step 3: Create Observer interface

Let's understand the content of the `Observer` class

### Interface responsibilities

This interface defines the contract for components that want to receive email notifications.

#### Key responsibilities include

- `update()` method receives email data when a new email arrives
- `getName()` help identify different observers
- Enables loose coupling between the email system and notification components

## Step 4: Create an EmailSystem (subject)

Let's understand the contents of the `EmailSystem` class

### Class responsibilities

This class manages registered observers and notifies them when a new email is received.

#### Key responsibilities include

- Maintains a list of observers
- Registers new components using `addObserver()`
- Notifies all observers via `receiveEmail()`
- Uses an ArrayList for observer management

## Step 5: Create InboxCounter observer

Let's understand the content of the `InboxCounter` class

### Class responsibilities

This observer tracks the number of unread emails and displays a count.

#### Key responsibilities include

- Increments the counter when `update()` is called
- Represents the notification badge seen in email apps
- Maintains its own internal state independently

## Step 6: Create popup notification observer

Let's understand the content of the `PopupNotifier` class

### Class responsibilities

This observer displays a popup preview when a new email is received.

#### Key responsibilities include

- Displays message preview with urgency indicator
- Truncates long messages for concise display
- Reacts differently from other observers, demonstrating indepdendence

## Step 7: Create the main application

Let's understand the content of the `EmailApp` class

### Main class responsibilities

This class brings together the subject and observers and simulates email events.

#### Key responsibilities include

- Creates an `EmailSystem` instance
- Registers multiple observers
- Simulates email sending to trigger notifications
- Demonstrates the complete observer pattern workflow

## Step 8: Run the application

- Run the following command in the terminal

### Compile

`mvn compile`

### Run tests

`mvn exec:java`

## Challenge

### Task

- Add email priority to the observer system. Modify the existing application so that

- If an email is marked as high priority, the popup displays "🛑 URGENT"
- If it's a normal email, the popup displays "🟡 NORMAL"
- The inbox should separately track the number of urgent emails

### Hints

- Add `boolean isUrgent` parameter to `update()` method
- Modify observers to handle priority differently
- Update `receiveEmail()` to accept priority

## Conclusion

Have successfully implemented an email notification system using the observer pattern.

In this lab

- Built a subject class(`EmailSystem`) and multiple observers
- Triggered automatic notifications to all registered observers
- Demonstrated how each observer handled updates independently
- Applied the observer pattern to simulate real-time email alerts

Also explored key design concepts

- Loose coupling between subjects and observers
- Easy extension without modifying existing logic
- Consistent interface-design communication
