# Environment Setup for AI Development in Java Lab

## Introduction

To get started with Java AI programming, need to set up the laptop with the necessary software. In this course, are provided with Cloud IDE. But eventually, on your own, need to set up the Java development environment in order to get started with creating and building AI powered apps using Java and Spring.

In this lab, the primary objective is to help set up the environment for Java AI development. Will install the latest stable version and verify the installation.

## Learning Objectives

After learning this lab, should be able to perform the following tasks:

- Install the latest version of Java on the system.
- Install the IDE can work with
- Test the Java installation on the IDE with a preconfigured project

## Install Java Development Kit (JDK)

1. Go to Oracle JDK Download page. Choose the appropriate version for the operating system (Windows, macOS, Linux). For AI development, the JDK 15 or any latest LTS version is fine.

2. Accept the license agreement and select the appropriate installer (for example, .exe for Windows, .dmg for macOS, .tar.gz for Linux).

3. Install JDK

- **Windows**: Run the downloaded `.exe` file and follow the installation prompts. Choose the installation directory carefully; the default `(C:\Program Files\Java\jdk-23.0.1)` is usually suitable.

- **macOS**: Open the downloaded `.dmg` file, drag the JDK package to the Applications folder.

- **Linux**: For `.tar.gz` or `.rpm`, use terminal commands:

```tar
tar -xzf jdk-23.0.1_linux-x64_bin.tar.gz -C /usr/local
mv /usr/local/jdk-23.0.1 /usr/local/jdk
export PATH=$PATH:/usr/local/jdk/bin
```

Replace the version numbers with the latest gunzipped jar files have downloaded.

Add this to the `~/.bashrc` or `~/.zshrc` file to include JDK in PATH.

4. To verify the installation, open a terminal (on Mac or Linux) or command prompt (on Windows) and type:

`java -version`

This should display the installed Java version.

## Install Integrated Development Environment (IDE)

Eclipse, IntelliJ IDEA, Visual Studio Code, are popular choices for Java development.

1. Visit the IntelliJ IDEA download page. Download the Community Edition.

2. Install IntelliJ IDEA:

- **Windows/macOS**: Run the downloaded `.exe`/`.dmg` file and follw the installation instructions.

- **Linux**: Download the Linux `tar.gz` archive, extract it, and run

`bin/idea.sh`

3. Upon the first run, IntelliJ will prompt with the JDK selection. Choose the one installed earlier. Can skip the project creation screen as we will create a new project manually.

4. Verify the Setup by following the steps mentioned below:

i. Launch IntelliJ IDEA and select "Create New Project".
ii. Choose Java as the project type. Ensure `IntelliJ` is chosen as build type. Click Next.
iii. Configure the project settings (for example, project name, location) and proceed.
iv. Once the project is created, can create a simple "Hello, AI World!" program to ensure everything is set up correctly:

- Right-click on `src`, select `New > Java Class`.
- Name it `HelloWorld.java` and write the following code:

```java
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, AI World!");
    }
}
```

v. Click on the green play button or choose `Run > Run 'HelloWorld'`.

If everything is correctly configured, should see "Hello, AI World!" printed in the bottom Run panel.

## Other IDE options to consider

### VS Code

VS Code is a lightweight, highly customizable editor that, with the right extensions, can be a fully-fledged IDE.

**Installation**: Download VS Code from the official website. Install extensions such as "Extension Pack for Java" (includes language support, debugger, and code navigation).

### Eclipse

Eclipse is one of the most popular IDEs for Java development, known for its extensive plugins and vast community support.
The learning curve is relatively gentle, especially with its intuitive interface and abundant tutorials available online.

**Installation**: Can download Eclipse from the official website. Choose the "Eclipse IDE for Java Developers" edition.

### NetBeans

NetBeans supports not just Java but also other languages such as PHP, HTML5, and C++.
It has a straightforward, user-friendly interface and provides drag-and-drop functionality for UI development.

**Installation**: Download from the official NetBeans website.

### Comparison and Tips

**Eclipse** and **IntelliJ IDEA** are feature-rich and widely adopted in professional settings, making them excellent choices for learning.
**NetBeans** is known for its simplicity and ease of use, ideal for educational purposes.
**VS Code** is lighter and offers flexibility, excellent for those who prefer a more streamlined editor experience.

Each of these IDEs comes with its strengths, and the best choice depends on personal preference and specific workflow needs. For absolute beginners, IntelliJ IDEA Community Edition or Eclipse are often recommended due to their comprehensive support for Java development and intuitive interfaces.

## VS Code Installation

Can download Visual Studio Code from its official website.

Here, will find the download options for:

- **Windows**: Both 32-bit and 64-bit versions are available.
- **macOS**: DMG installer for various macOS versions.
- **Linux**: DEB and RPM packages for Debian/Ubuntu and Red Hat/Fedora based distributions respectively.

Will need to install extensions for the language in which are going to work with. To install extensions for Java, follow the steps below:

### Extension Pack for Java

This pack includes all the core extensions necessary for Java development in VS Code.

### How to install

1. Open VS Code.
2. Go to the Extensions view by clicking on the Extensions icon in the Activity Bar on the side of the window (looks like four squares) or by pressing `Cmd+Shift+X`(macOS) or `Ctrl+Shift+X` (Windows/Linux).
3. In the search bar at the top of the Extensions view, type "Extension Pack for Java".
4. Click the "Install" button next to the "Extension Pack for Java" result.

### Language Support for Java (TM) by Red Hat

Provides advanced language features for Java, including code navigation, error checking, code actions, and more.

### Debug for Java

Enables Java debugging features in VS Code, allowing to set breakpoints, inspect variables, and step through code.

### Maven for Java (if planning to use Maven for project management)

Enhances VS Code with Maven-specific features, including tasks for common Maven commands such as

`mvn clean install`

### Prettier - Code Formatter (Optional)

Helpers to format code, ensuring a consistent style across project. Prettier is a language-agnostic but can be configured for Java.

## Test the IDE for loading and running a project

Once finished installing Visual Studio Code, need to test if the IDE works fine. To make things simpler, have been provided with a preconfigured Maven project which has AI dependencies.

1. Open a terminal and clone the Maven project repository. Can use the following commands in the terminal:

`git clone https://github.com/ibm-developer-skills-network/ogdye-maven-ai-helloworld.git`

2. Open the folder containing the Maven project and view the class with the main method.

3. Use the terminal commands to navigate to the root directory of the Maven project.

`cd ogdye-maven-ai-helloworld/ai`

4. Run the Maven built command using the integrated terminal to compile the Java sources, test cases, and package the project.

`mvn clean install`

5. Run the Project. This will run the main method in the class.

`java -jar target/ai-0.0.1-SNAPSHOT.jar`

## Summary

Have completed setting up Java environment on the system and installed the IDE to work with Java coding.

Now, get ready to code in Java using the new IDE.
