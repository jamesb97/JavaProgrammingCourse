# Java Deployment with Maven and JLink Lab

## Overview

In this lab, will create a simple command-line calculator using pure Java and Maven, then deploy it using JLink to create a standalone executable. This lab demonstrates core Java deployment techinques in simple steps.

## Objectives

- Describe the Maven project structure and dependency management
- Configure environmental-specific settings using Maven profiles
- Build executable Java Archive (JAR) files with Maven
- Deploy standalone applications using JLink

## Verify the environment

1. Open a terminal window by using the menu in the editor. `Terminal > New Terminal`.

2. Verify that `java` is installed.
   `java --version`

3. Verify that Maven is installed.
   `mvn --version`

## Step 1: Create the application

1. Open a terminal window by using the menu in the editor: `Terminal > New Terminal`.

2. If not in the current project folder, copy and paste the following code to change into the working directory.
   `cd /home/project`

3. Run the following command to clone the Git repository that contains the starter code needed for the project, if the Git repository doesn't already exist.

`git clone https://github.com/ibm-developer-skills-network/stuss-package-execute-java-application deploylab`

4. Change to the directory to start working on the lab.
   `cd deploylab`

5. List the contents of the directory to see the artifacts for this lab.
   `ls`

## Step 2: Check on the pom.xml file

Review the contents of the `pom.xml` file.

This is a `Maven POM (Project Object Model)` file - essentially a configuration file that tells Maven how to build and manage the Java project.

### Key components include

- **Project coordinates**: `groupId`, `artifactId`, and `version` uniquely identify your project in Maven repositories
- **Profiles**: Development and production profiles with different configurations - debug mode versus normal mode, different decimal precision
- **Resource filtering**: Maven replaces `${variable}` placeholders in resource files with profile-specific values during build
- **Maven JAR Plugin**: Creates executable JAR with manifest pointing to main class - simple and reliable approach
- **Main class specification**: Tells Maven which class contains the main method for the executable JAR
- **Java 11 compatibility**: Sets compiler source and target to Java 11 for modern language features

## Step 3: Create the configuration file

Let's review the content of the `app.properties` file.

This is a configuration file used by the application to define behavior and environment-specific values. Maven replaces variables in this file based on the active profile during the build process.

### Key functions include

- **Maven filtering**: The `${variable}` syntax gets replaced with actual values from Maven profiles during build
- **Environment-specific values**: Same configuration file works for both development and production with different values
- **Application settings**: Controls how the calculator behaves - precision for decimal results, debug information display
- **Centralized configuration**: All application settings in one place makes it easier to manage different environments
- **Runtime behavior control**: Properties control what information is displayed to users
- **Build-time substitution**: Values are determined at build time, not runtime, for better performance

## Step 4: Create the calculator application

Let's understand the content of the `Calculator` Application

This is the main class for the calculator application. It handles user interaction, input validation, and coordination between configuration and calculation logic.

### Key functions include

- **Main method**: Entry point that initializes configuration and handles top-level errors gracefully
- **Configuration-driven display**: Uses properties to control what information is shown to users
- **Input validation**: Checks if user input is valid numbers and handles invalid input without crashing
- **User interaction loop**: Continues until user types "quit", demonstrating basic command-line interface patterns
- **Error handling**: Catches and displays errors from calculator operations in user-friendly format
- **Resource cleanup**: Properly closes Scanner to prevent resource leaks when application exits

## Step 5: Create supporting classes

Let's understand the contents of the `ConfigManager` and `Calculator` class

These classes support the main calculator logic. ConfigManager handles configuration loading, while Calculator performs arithmetic operations and formatting.

### Key functions include

- **ConfigManager**: Loads properties from classpath and provides type-safe access methods with default values
- **Resource management**: Uses try-with-resources to automatically close InputStream and prevent resource leaks
- **Type conversion**: Converts string properties to boolean and integer types with fallback to default values
- **Calculator class**: Performs basic arithmetic operations with proper error handling for edge cases such as division by zero
- **Configurable formatting**: Uses DecimalFormat with precision controlled by configuration properties
- **Separation of concerns**: Configuration loading, calculation logic, and formatting are handled by separate, focused classes

## Step 6: Build and test the application

- Build the application with Maven:

```
# Build with development profile (default)
mvn clean compile package

# Test the application (simple JAR without dependencies)
java -cp target/simple-calculator-1.0.0.jar com.calculator.CalculatorApp
```

- Press Ctrl + C to exit out of the application.

### Key steps include

- **Maven clean**: Removes previous build artifacts to ensure fresh build
- **Maven compile**: Compiles source code and processes resources with filtering
- **Maven package**: Creates JAR file with proper manifest for execution
- **Simple JAR**: Creates standard JAR - since we have no external dependencies, this works perfectly
- **Classpath execution**: Use the `-cp` and specify the main class instead of `-jar` for simple JARs

## Step 7: Create JLink deployment

- Create a standalone runtime using JLink: Run all the steps sequentially.

```
# Analyze required Java modules
jdeps --class-path target/simple-calculator-1.0.0.jar --print-module-deps target/simple-calculator-1.0.0.jar

# Create custom runtime with JLink
jlink --add-modules java.base,java.desktop \
      --output calculator-runtime \
      --strip-debug \
      --compress=2 \
      --no-header-files \
      --no-man-pages

# Copy application to runtime
cp target/simple-calculator-1.0.0.jar calculator-runtime/

# Create launch script
cat > calculator-runtime/run-calculator.sh << 'EOF'
#!/bin/bash
cd "$(dirname "$0")"
./bin/java -cp simple-calculator-1.0.0.jar com.calculator.CalculatorApp "$@"
EOF

chmod +x calculator-runtime/run-calculator.sh

# Test standalone runtime
./calculator-runtime/run-calculator.sh
```

### JLink Steps Summary

- **Check what the app needs**: Run `jdeps` to find required Java modules
- **Build custom runtime**: Use `jlink` to create a minimal Java environment with only needed modules
- **Copy app**: Move the JAR file into the new runtime folder
- **Create launch script**: Make a simple script that runs the app using the custom Java
- **Make script runnable**: Use `chmod+x` to make the script executable
- **Test it**: Run the script to verify that everything works
- **End result**: A self-contained folder with the app + minimal Java runtime that works without installing Java separately!

### Key steps include

- **Module analysis**: `jdeps` determines exactly which Java platform modules the application needs
- **Custom runtime creation**: JLink builds minimal Java runtime with only required modules (typically 30-50MB versus 200MB+JRE)
- **Optimization flags**: `--strip-debug` removes debugging info, `--compress=2` applies maximum compression for smaller size
- **Self-contained package**: Runtime directory contains everything needed - no Java installation required on target machine
- **Launch script**: Simple wrapper script makes it easy to run the application from any directory
- **Portable deployment**: Entire runtime directory can be copied to any compatible system and run immediately

## Challenge

### Task

In this challenge, extend the calculator to support a simple command history feature.

### Challenge Requirements

Add a new menu option that lets users view their last 5 calculations. When users type "history" instead of a number, show them their recent calculations.

### Hints to get started

- Store previous calculations using a `List<String>`
- Check for the "history" command in the main input loop (`CalculatorApp.java`)
- Add a method in the `Calculator` class to save formatted calculation strings
- Limit the list to the last 5 entries by removing older ones
- Display the history in a numbered, user-friendly format

### Expected behavior

```
Enter first number: 10
Enter operation (+, -, *, /): +
Enter second number: 5
Result: 10 + 5 = 15.00

Enter first number: history
=== Calculation History ===
1. 10.0 + 5.0 = 15.00
```

#### Key updates include

- **History storage**: Uses `ArrayList<String>` to store formatted calculation strings in memory
- **Size management**: Automatically removes oldest calculations when history exceeds 5 items
- **String formatting**: Creates readable calculation strings using `String.format()` with consistent number formatting
- **Input handling**: Modified main loop to check for "history" command alongside existing "quit" command
- **User experience**: Shows numbered lists of calculations with clear formatting
- **Integration**: History feature works seamlessly with existing configuration and formatting systems

## Conclusion

Have successfully

- Created a Maven project
- Built a simple Java application with configuration management
- Used Maven resource filtering for environment-specific settings
- Created an executable fat JAR with all dependencies
- Deployed with JLink to create a standalone runtime
- Tested both development and production configurations
