# Inventory Management Solution Final Project

## Overview

In this final project, will build a command-line inventory management system using core Java concepts. Will apply design patterns, write unit tests, and deploy the application using Docker.

This project helps demonstrate an understanding of object-oriented programming, design patterns, software testing, and modern deployment practices. Will complete the implementation of key classes and test them through a structured workflow.

## Learning Objectives

- Apply the Factory and Strategy design patterns
- Write unit tests using JUnit
- Build and structure the project using Maven
- Package the application as a Java Archive (JAR)
- Deploy the application in a Docker container

## Step 1: Download and create the application

1. Open a new terminal window by using the menu in the editor: `Terminal > New Terminal`.

2. If not already in the project folder, run the following command:
   `cd /home/project`

3. Clone the Git repository if it does not already exist using the following command:
   `git clone https://github.com/ibm-developer-skills-network/sdtzj-final-project-design-patterns-skeleton invlab`

4. Change into the project directory:
   `cd invlab`

5. View the files inside of the folder:
   `ls`

## Step 2: Understand the project

### Maven Project Structure

After downloading the project from GitHub, the directory structure should look something like this:

```
invlab/
├── pom.xml
├── src/
│   ├── main/
│   │   └── java/
│   │       └── inventory/
│   │           ├── Product.java
│   │           ├── ProductFactory.java
│   │           ├── DiscountCalculator.java
│   │           ├── InventoryManager.java
│   │           └── Main.java
│   └── test/
│       └── java/
│           └── inventory/
│               ├── ProductFactoryTest.java
│               ├── DiscountCalculatorTest.java
│               └── InventoryManagerTest.java
├── Dockerfile
```

### pom.xml

The `pom.xml` file is already configured. Open it using the file explorer

It contains:

- JUnit 5.10.0 dependency for unit testing
- Maven plugins for compilation, testing, and packaging
- Java version settings
- Executable JAR configuration with `Main` as the entry point

## Step 3: Task - Build the Product and Product Factory

### Product

Open up the `Product.java` skeleton file

Here are some guidelines to get started

#### Requirements

Need to first implement the `Product` class with the following

#### Fields(private)

- `id` (String): Unique identifier
- `name` (String): Product name
- `type` (String): Product category
- `price` (double): Product price
- `quantity` (int): Stock quantity

#### Constructor

- Takes all five parameters
- Validates that price and quantity are non-negative
- Throws `IllegalArgumentException` for invalid inputs

#### Methods to implement

- Getter and setter methods for all fields
- `sell(int amount)`: Reduces quantity if available, returns true if successful
- `addStock(int amount)`: Increases quantity
- `isInStock()`: Returns true if quantity > 0
- `toString()`: Returns formatted string representation

#### Implementation Hints

```
// Constructor validation example
if (price < 0) {
    throw new IllegalArgumentException("Price cannot be negative");
}

// Sell method logic
public boolean sell(int amount) {
    if (amount <= 0 || amount > quantity) {
        return false;
    }
    // Update quantity and return true
}

// ToString format suggestion
return String.format("Product{id='%s', name='%s', type='%s', price=%.2f, quantity=%d}",
                     id, name, type, price, quantity);
```

#### Test the Implementation

`mvn compile`

### Factory Pattern: ProductFactory

Let's create the `ProductFactory` class: Open up the Skeleton file

#### Requirements

Implement a factory that creates products based on business rules

#### Constants to define

- BOOK_TYPE="BOOK"
- ELECTRONICS_TYPE="ELECTRONICS"

#### Business rules

- Books must have a minimum price of $5.00
- Electronics must have a minimum price of $10.00
- Invalid product types should throw exceptions

#### Method

- `createProduct(String id, String name, String type, double price, int quantity)`

#### Implementation hints

```
public class ProductFactory {
    public static final String BOOK_TYPE = "BOOK";
    public static final String ELECTRONICS_TYPE = "ELECTRONICS";

    public static Product createProduct(String id, String name, String type, double price, int quantity) {
        // Validate product type first
        if (!BOOK_TYPE.equals(type) && !ELECTRONICS_TYPE.equals(type)) {
            throw new IllegalArgumentException("Invalid product type: " + type);
        }

        // Apply business rules based on type
        if (BOOK_TYPE.equals(type) && price < 5.0) {
            throw new IllegalArgumentException("Books must have minimum price of $5.00");
        }

        // Continue with electronics validation...
        // Return new Product instance
    }
}
```

#### Test implementation

`mvn compile`

## Step 4: Task - Create the DiscountCalculator

### Strategy Pattern - DiscountCalculator

Let's create the `DiscountCalculator` class. Open up the skeleton file to get started

Open `DiscountCalculator.java` file

#### Requirements

- Implement different discount strategies

#### Discount types to support

- STUDENT: 10% off on books only
- BULK: 15% off if buying 5 or more items
- NONE: No discount applied

#### Method to implement

- `calculateDiscount(Product product, int quantity, String discountType)`
- This method should return a `DiscountResult` object with two fields: `discountAmount`(double) and `description` (String)

#### Inner class to create

```
public static class DiscountResult {
    private double discountAmount;
    private String description;

    // Constructor and getters needed
}
```

#### Implementation hints

```
public static DiscountResult calculateDiscount(Product product, int quantity, String discountType) {
    double discountAmount = 0.0;
    String description = "No discount applied";

    switch (discountType.toUpperCase()) {
        case "STUDENT":
            if ("BOOK".equals(product.getType())) {
                discountAmount = product.getPrice() * quantity * 0.10;
                description = "Student discount: 10% off books";
            } else {
                description = "Student discount only applies to books";
            }
            break;
        case "BULK":
            if (quantity >= 5) {
                discountAmount = product.getPrice() * quantity * 0.15;
                description = "Bulk discount: 15% off for 5+ items";
            } else {
                description = "Bulk discount requires 5+ items";
            }
            break;
        case "NONE":
        default:
            // No discount
            break;
    }

    return new DiscountResult(discountAmount, description);
}
```

## Step 5: Task - Build the InventoryManager

### Business logic: InventoryManager

Let's review the `InventoryManager` class. Open up the skeleton `InventoryManager.java` file to get started.

#### Requirements

- Create a class that manages the inventory using both patterns

#### Fields

- products - `Map<String, Product>` to store products by ID

#### Methods to implement

- `addProduct(String id, String name, String type, double price, int quantity)`: Uses Factory
- `sellProduct(String id, int quantity, String discountType)`: Uses Strategy
- `addStock(String id, int quantity)`
- `viewInventory()`: Displays all products
- `getInventoryValue()`: Calculates total value
- `getLowStockProducts(int threshold)`: Returns products with low stock

#### Implementation hints

```
public class InventoryManager {
    private Map<String, Product> products;

    public InventoryManager() {
        this.products = new HashMap<>();
    }

    public void addProduct(String id, String name, String type, double price, int quantity) {
        try {
            Product product = ProductFactory.createProduct(id, name, type, price, quantity);
            products.put(id, product);
            System.out.println("Product added successfully: " + product);
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding product: " + e.getMessage());
        }
    }

    public void sellProduct(String id, int quantity, String discountType) {
        Product product = products.get(id);
        if (product == null) {
            System.out.println("Product not found: " + id);
            return;
        }

        if (!product.isInStock() || product.getQuantity() < quantity) {
            System.out.println("Insufficient stock for product: " + id);
            return;
        }

        // Calculate discount using Strategy pattern
        DiscountCalculator.DiscountResult discount =
            DiscountCalculator.calculateDiscount(product, quantity, discountType);

        // Calculate total and apply discount
        double totalPrice = product.getPrice() * quantity;
        double finalPrice = totalPrice - discount.getDiscountAmount();

        // Update inventory
        product.sell(quantity);

        // Display sale information
        System.out.printf("Sale completed:%n");
        System.out.printf("Product: %s%n", product.getName());
        System.out.printf("Quantity: %d%n", quantity);
        System.out.printf("Original Price: $%.2f%n", totalPrice);
        System.out.printf("Discount: $%.2f (%s)%n", discount.getDiscountAmount(), discount.getDescription());
        System.out.printf("Final Price: $%.2f%n", finalPrice);
    }

    // Implement other methods following similar patterns...
}
```

## Step 6: Task - Build the InventoryManager

### Main class: Main

Let's review the `Main.java` class. Open the file to get started.

#### Task

Complete the `Main` class to simulate an interactive command-line interface for the inventory system.

#### Requirements

- Display the main menu with options

- Add Product
- View Inventory
- Sell Product
- Add Stock
- View Statistics
- Exit

- Use `Scanner` to capture user input
- Use a loop to keep the menu running until the user selects "Exit"

#### Hints

- Use a `switch` statement to handle each option
- Call appropriate methods from `InventoryManager`.

#### Implementation hints

```
Scanner scanner = new Scanner(System.in);
InventoryManager manager = new InventoryManager();

boolean running = true;

while (running) {
    System.out.println("=== MAIN MENU ===");
    System.out.println("1. Add Product");
    System.out.println("2. View Inventory");
    System.out.println("3. Sell Product");
    System.out.println("4. Add Stock");
    System.out.println("5. View Statistics");
    System.out.println("6. Exit");
    System.out.print("Enter choice: ");

    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline

    switch (choice) {
        case 1:
            // Prompt and call addProduct
            break;
        case 2:
            // Call viewInventory
            break;
        case 3:
            // Prompt and call sellProduct
            break;
        case 4:
            // Prompt and call addStock
            break;
        case 5:
            // Call getInventoryValue or getLowStockProducts
            break;
        case 6:
            running = false;
            break;
        default:
            System.out.println("Invalid choice.");
    }
}
```

## Step 7: Implement Test classes

Need to write tests for all three main classes in the `src/test/java/inventory` directory.

### ProductFactoryTest

Open the `ProductFactoryTest.java` file located inside of the `src/test/java/inventory/` directory.

### Tests to implement

- Valid product creation for books and electronics
- Business rule validation (minimum prices)
- Exception handling for invalid inputs

#### Implementation hints

```
@Test
void testCreateValidBook() {
    Product book = ProductFactory.createProduct("B001", "Java Book", "BOOK", 15.99, 5);
    assertNotNull(book);
    assertEquals("B001", book.getId());
    assertEquals("BOOK", book.getType());
    assertEquals(15.99, book.getPrice());
}

@Test
void testBookMinimumPriceValidation() {
    assertThrows(IllegalArgumentException.class, () -> {
        ProductFactory.createProduct("B001", "Cheap Book", "BOOK", 3.99, 5);
    });
}

// Implement more tests...
```

### DiscountCalculatorTest

Open up the `DiscountCalculatorTest.java` file to review how the discount calculations are tested.

#### Tests to implement

- Student discount logic
- Bulk discount logic
- No discount scenarios

### InventoryManagerTest

Open up the `InventoryManagerTest.java` file to review how inventory operations are tested.

#### Tests to implement

- Add products and verify presence
- Perform valid and invalid sales
- Check stock updates and view outputs
- Validate statistics and low-stock results

## Step 8: Run and validate the application

### Run comprehensive tests

Execute all unit tests

`mvn test`

### Run the application

Test the complete application

`mvn compile exec:java -Dexec.mainClass="inventory.Main"`

#### Expected Funtionality

- **Add Product**: Create books and electronics with validation
- **View Inventory**: Display all products with current stock
- **Sell Product**: Apply different discount strategies
- **Add Stock**: Replenish inventory
- **View Statistics**: Show inventory value and low-stock alerts

#### Sample test scenarios

- Add a book priced below $5 (should fail)
- Sell 6 books with bulk discount (should get 15% off)
- Apply student discount to electronics (discount should not be applied)
- Check inventory statistics after transactions

## Step 9: Complete the Dockerfile

Dockerfile overview

Let's create the contents of the `Dockerfile`: Open up the skeleton file to get started.

### Requirements

- Fill in the placeholders to create a working Dockerfile

```
# Use latest OpenJDK (currently JDK 21) with slim OS for smaller image size

# Create a non-root user for security best practices
RUN groupadd -r appuser && useradd -r -g appuser appuser

# Set working directory inside container

# Copy the JAR file from target directory

# Change ownership to non-root user

# Switch to non-root user

# Expose port (not really needed for command-line app, but good practice)

# Command to run the application with optimized JVM settings
```

#### Implementation hints

```
# Use latest OpenJDK (currently JDK 21) with slim OS for smaller image size
FROM openjdk:21-jdk-slim

# Create a non-root user for security best practices
RUN groupadd -r appuser && useradd -r -g appuser appuser

# Set working directory inside container
WORKDIR /app

# Copy the JAR file from target directory
COPY target/app.jar .

# Change ownership to non-root user
RUN chown appuser:appuser app.jar

# Switch to non-root user
USER appuser

# Expose port (not really needed for command-line app, but good practice)
EXPOSE 8080

# Command to run the application with optimized JVM settings
CMD ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]
```

##### Build and test the Docker container

###### Build the JAR file

`mvn clean package`

###### Build the Docker image

`docker build -t simple-inventory .`

###### Run the container

`docker run -it simple-inventory`

## Application verification checklist

Use the following checklist to verify that the application works correctly across code, testing, and deployment stages.

### Code implementation

- Product class with all required methods
- Factory pattern with business rule validation
- Strategy pattern with multiple discount types
- InventoryManager combining both patterns
- Main class with complete CLI interface

### Testing

- All unit tests pass (mvn test)
- Application runs without errors
- All menu options work correctly
- Discount calculations are accurate

### Deployment

- JAR file builds successfully
- Dockerfile creates working container
- Application runs in Docker container
- All functionality works in containerized environment

## Common issues and resolutions

### Code implementation issues

#### 1. NullPointerException

- Issue: Forgetting to initialize collections or checking null values

```
// Problem
private Map<String, Product> products; // Not initialized
products.put("id", product); // NPE!
```

- **Resolution**: Always initialize collections and add null checks

```
private Map<String, Product> products = new HashMap<>();
if (product != null) { /* process */ }
```

#### 2. Infinite loops in menu system

- **Issue**: Scanner not consuming newline characters properly

```
// Problem
int choice = scanner.nextInt(); // Leaves newline in buffer
String input = scanner.nextLine(); // Gets empty string
```

- **Resolution**: Use `scanner.nextLine()` after `nextInt()` or use `nextLine()` + `Integer.parseInt()`

#### 3. Case sensitivity issues

- **Issues**: String comparisons failing due to case differences

```
// Problem
if (type == "book") // Wrong operator and case
```

- **Resolution**: Use `.equals()` with proper case handling

`if ("BOOK".equals(type.toUpperCase()))`

#### 4. Validation logic errors

- **Issue**: Incorrect boundary conditions

```
// Problem
if (price < 5.0 && type.equals("BOOK")) // Wrong logic
```

- **Resolution**: Use correct logical operators

```
if (type.equals("BOOK") && price < 5.0)
```

#### 5. Exception handling mistakes

- **Issue**: Catching exceptions without proper handling

```
// Problem
try { /* code */ } catch (Exception e) { } // Silent failure
```

- **Resolution**: Provide meaningful error messages

```
catch (IllegalArgumentException e) {
    System.out.println("Error: " + e.getMessage());
    return false;
}
```

### Testing issues

#### 1. Missing test annotations

- **Issue**: Forgetting `@Test` annotation

```
// Problem
public void testCreateProduct() { } // Not recognized as test
```

- **Resolution**: Always add `@Test` annotation

```
@Test
public void testCreateProduct() { }
```

#### 2. Incorrect assertions

- **Issue**: Using wrong assertion methods

```
// Problem
assertTrue(product.getPrice() == 10.0); // Floating point comparison
```

- **Resolution**: Use appropriate assertions

```
assertEquals(10.0, product.getPrice(), 0.01); // Delta for doubles
```

#### 3. Not testing edge cases

- **Issue**: Only testing happy path scenarios
- **Resolution**: Test boundary conditions and error cases

```
@Test
void testNegativePrice() {
    assertThrows(IllegalArgumentException.class, () ->
        new Product("id", "name", "BOOK", -1.0, 5));
}
```

### Deployment issues

#### 1. Dockerfile path problems

- **Issue**: Incorrect file paths in COPY command

```
# Problem
COPY simple-inventory-1.0.0.jar app.jar # Wrong path
```

- **Resolution**: Use correct Maven target path

```
COPY target/simple-inventory-1.0.0.jar app.jar
```

#### 2. Base Images issues

- **Issue**: Using wrong or outdated base images

```
# Problem
FROM java:8 # Deprecated image
```

- **Resolution**: Use official OpenJDK images

```
FROM openjdk:17.0.1-jdk-slim
```

#### 3. Missing JAR file

- **Issue**: Docker build fails because JAR doesn't exist
- **Resolution**: Always run Maven build first

```
mvn clean package  # Creates JAR
docker build -t app .
```

#### 4. Container startup issues

- **Issue**: Container exits immediately

```
# Problem
CMD java -jar app.jar # Missing quotes or brackets
```

- **Resolution**: Use proper CMD syntax

```
CMD ["java", "-jar", "app.jar"]
```

### Build and environment issues

#### 1. Maven compilation errors

- **Issue**: Java version mismatch or missing dependencies
- **Resolution**: Check `pom.xml` configuration and Java version

```
java -version  # Check Java version
mvn clean compile  # Clean build
```

#### 2. Classpath issues

- **Issue**: Classes not found when running
- **Resolution**: Use Maven exec plugin

`mvn exec:java -Dexec.mainClass="inventory.Main"`

### General troubleshooting tips

1. **Read error messages carefully**: They usually point to the problem
2. **Use `System.out.println()`** for debugging variable values
3. **Test small pieces**: Don't write everything at once
4. **Check file names and paths**: Case sensitivity matters
5. **Verify Maven structure**: Files must be in correct directories

## Conclusion

Have completed the final project.

In this final project, have successfully completed the following tasks:

- Applied the Factory pattern to manage object creation
- Used the Strategy pattern to implement flexible algorithms
- Wrote and executed unit tests using JUnit
- Structured and built Java projects with Maven
- Packaged and deployed applications using Docker
