## Objectives

- Design a proper domain model for retail inventory
- Implement Spring Boot models, repositories, services, and controllers
- Integrate and validate data using JPA and Hibernate
- Ensure the backend compiles without errors

## Create your own GitHub Repository

### Your Task

1. In a browser, visit this GitHub repository: `https://github.com/ibm-developer-skills-network/nxmpl-java-database-final-template`
2. From the GitHub Code tab, click on the green `Use this template` drop-down menu.
3. Select `Create a new repository` from the dropdown menu to create your own repository from this template.

On the next screen, fill out the following prompts:

1. Select your GitHub account from the dropdown list.
2. Name the new repository: `java-database-final`. It is important that you use this name for some of the commands to work in the lab.
3. (Optional) Add a nice description to let people know what this repo is for.
4. Make the repo `Public` so that others can see it.
5. Click on the `Create Repository` to create the repository in the GitHub account you have selected.

### Clone the repository

The next step is to clone the repository using the GitHub token created previously.

1. On the right-hand side of the page, click on the green `Code` drop-down menu, and on the `Local` tab, click the `Copy url to clipboard` button to the right of the `https` URL.
2. Switch back to the `Skills Network Labs` browser tab.
3. Open a new terminal using the menu `Terminal` -> `New Terminal`.
4. In the terminal, type in the `git clone` command, followed by a `space`, then paste in the URL you just copied.
5. In the terminal, run the command below to export PROJECT_HOME to your GitHub repository.
   `export PROJECT_HOME=/home/project/java-database-final`

## Final Project Scenario

Have been asked by the inventory manager at your company to develop a Retail Inventory Backend micro-service to support a multi-store retail business. The frontend user interface (UI) has already been developed by another team and will be used by internal administrators to manage store locations, inventory levels, products, and order tracking.

Since this is a backend micro-service, it is expected to expose a well-defined REST API that the frontend UI and other micro-services can call. Your service will enable features such as creating and managing physical store locations, registering and updating product information, tracking stock availability across stores, and managing incoming and outgoing orders.
Have also been informed that someone else has started on this project and has already scaffolded the Spring Boot structure and created a sample endpoint. You will receive a partially completed backend in your GitHub repository and will be expected to build out the rest of the functionality.

### Final Lab Phases

1. Part 1: Configure the project to work with databases. Will need to configure the backend to use MySQL for structured data and MongoDB for unstructured or flexible data.
2. Part 2: Build the models. Will define the domain models (Store, Product, Customer, Inventory, Review, and other Order related models) using JPA annotations, including field constraints and relationships.
3. Part 3: Build the repositories and services to create JPA repositories and service classes to handle the business logic for CRUD operations and backend processing.
4. Part 4: Build the controllers for the endpoints to expose REST endpoints to support the frontend's needs, such as creating stores, listing products, updating inventory, and retrieving order details.
5. Part 5: Load sample data to write SQL and MongoDB scripts to populate the database with initial sample data.
6. Part 6: Build stored procedures to create stored procedures for common queries checking stock availability across stores.
7. Part 7: Integrate with frontend and run the project to wire up the backend with the pre-built frontend and run the application end-to-end to verify the functionality.

## Part 1: Configure Project

Ensure that you have cloned the fork of the template repository. The first step is to start the database and populate the tables.

### Start MySQL Database

1. The first step is to create the MySQL instance in the IDE environment. Click on the Skills Network Toolbox icon at the bottom of the left-hand panel. This will bring up another panel on the left.
2. Expand `DATABASES`, and select `MySQL` in the databases category. This will open up a new tab.
3. Click on `Create`
4. The MySQL Database will start in a few minutes. Once it has started, click on the `MySQL CLI` in the `Summary` tab. This will open up the MySQL CLI terminal at the bottom of the screen.
5. Can now enter all the `MySQL-based` commands in this lab in the terminal.
6. Can find the username, password, and connection URL information in the `Connection Information` tab if needed to connect outside of the IDE environment.

### Start the MongoDB Database

1. The next step is to create the MongoDB instance in the IDE environment. In the `Skills Network Toolbox` panel, under `DATABASES`, select `MongoDB`. This will open a new tab.
2. Click on `Create`
3. The MongoDB Database will start in a few minutes. Once it has started, click on the `MongoDB CLI` in the `Summary` tab. This will open up the MongoDB CLI terminal at the bottom of the screen.
4. Can now enter any `MongoDB-based` commands in this terminal.
5. Click on the `Connection Information` tab. Here, can find the username, password, and connection URL information if needed to connect to this database from the outside the IDE environment.
6. On this `Connection Information` tab, make note of the URL that start with `mongodb://root` under the `MongoDB CLI Command` section of the page.

### Configure application.properties

Next, you need to fill out the database properties in the `application.properties` file located in the `/home/project/java-database-final/back-end/src/main/resources/` directory.

1. Open the `application.properties` file.
2. Add the following lines of code to the `application.properties` file, replacing the code that is already present in there.

```
spring.application.name=code

spring.datasource.url=jdbc:mysql://{MYSQL_HOST}/inventory?usessl=false
spring.datasource.username=root

spring.datasource.password={MY_SQL_PASSWORD}
spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.open-in-view=false

spring.data.mongodb.uri=mongodb://root:{MONGODB_PASSWORD}@{MONGODB_HOST}/reviews?authSource=admin

management.endpoint.health.show-details=always
management.health.db.enabled=true
```

3. Replace the values in the curly brackets `{}` with the real values from the `Connection Information` sections of the `MongoDB` and `MySQL` tabs as discussed in the labs.
   Next, need to add a dependency to the `pom.xml` file located in the `/home/project/java-database-final/back-end/` directory.

4. Open the `pom.xml` file.
5. Add the following lines of code to the `pom.xml` file. Add them within the `<dependencies>` section after the last `<dependency>` entry.

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

## Part 2: Create Models

The task is to build out the following models based on the specifications provided here:

### Customer Model

1. Open the `Customer.java` file
2. Add the following attributes along with the getters and setters:

- `id`: `private long` Auto increment
- `name`: `private String`; cannot be empty
- `email`: `private String`; cannot be empty
- `phone`: `private String`; cannot be empty

3. A customer can have multiple orders. Use the `@OneToMany` annotation.
   Hint:

- Use `@Id` and `@GeneratedValue(strategy = GenerationType.IDENTITY)` for id to auto increment it and set as primary key.
- Use the `@NotNull` annotation for the `name`, `email`, and `phone` fields. Example: `@NotNull(message = "Email cannot be null")`.
- Use the `@OneToMany` annotation. Example: `@OneToMany(mappedBy="customer", fetch=FetchType.EAGER)` to create a one-to-many relationships with orders.
- Apply the `@JsonManagedReference` annotation to ensure proper JSON serialization.
- Add `@Entity` annotation above the class name.

### Inventory Model

1. Open the `Inventory.java` file
2. Add the following attributes along with getters and setters:

- `id`: `private long`; represents the product id in the inventory
- `product`: `private Product`; represents the product in the inventory
- `store`: `private Store`; represents the store where inventory is stored
- `stockLevel`: `private Integer`; represents the current stock level of the product at the store.

Hint:

- Use `@Id` and `@GeneratedValue(strategy=GenerationType.IDENTITY)` for id to auto increment it and set it as primary key\*

3. Set up relationships:

- `Product`: An inventory entry is associated with one product. Use the `@ManyToOne` annotation and link it to the `Product` entity.

Hint:

- Use the `@JSONBackReference("inventory-product")` to handle the bidirectional relationship correctly and prevent circular references during JSON serialization.
- `Store`: An inventory entry is also associated with one store. Use the `@ManyToOne` annotation and link it to the `Store` entity.

Hint:

- Use `@JsonBackReference("inventory-store")` to manage the relationship with the store.

4. Use the `@JoinColumn` annotation to specify the foreign key column names:

- For the `product` field, use `@JoinColumn(name="product_id")`.
- For the `store` field, use `@JoinColumn(name="store_id")`.

Hint:

- Create a constructor that takes in a `Product`, `Store`, and `Integer stockLevel` to initialize the `Inventory` object.
- Add `@Entity` annotation above the class name.

### Product Model

1. Open the `Product.java` file
2. Add the following attributes along with getters and setters:

- `id`: `private long`; auto increment
- `name`: `private String`; cannot be empty
- `category`: `private String`; cannot be empty
- `price`: `private Double`; cannot be empty
- `sku`: `private String`; cannot be empty, must be unique

Hint:

- Use `@Id` and `@GeneratedValue(strategy = GenerationType.IDENTITY)` for id to auto increment it and set it as primary key.
- Use `@NotNull` for the `name`, `category`, `price`, and `sku` fields to ensure they are not null when creating a `Product` object.
- Use the `@Table` annotation with a `uniqueConstraints` attribute to enforce uniqueness on the `sku` column. Example: `@Table(name="product", uniqueConstraints=@UniqueConstraint(columnNames="sku"))` - Add `@Entity` annotation above class name.

3. Set up relationships:

- `Inventory`: A product can have multiple inventory entries. Use the `@OneToMany` annotation to reflect this relationship.

Hint:

- Use the `mappedBy` attribute to indicate the relationship with the `Inventory` class.
- Apply the `@JsonManagedReference("inventory-product")` to handle the bidirectional relationship and prevent circular references during JSON serialization.

### Store Model

1. Open the `Store.java` file
2. Add the following attributes along with getters and setters:

- `id`: `private long`; auto increment
- `name`: `private String`; cannot be empty
- `address`: `private String`; cannot be empty

Hint:

- Use `@Id` and `@GeneratedValue(strategy=GenerationType.IDENTITY)` for id to auto increment it and set it as primary key

3. Set up relationships:

- `Inventory`: A store can have multiple inventory entries. Use the `@OneToMany` annotation to reflect this relationship.

Hint:

- Use the `mappedBy` attribute to indicate the relationship with the `Inventory` class.
- Apply `@JsonManagedReference("inventory-store")` to handle the bidirectional relationship and prevent circular references during JSON serialization.

4. Add validation to ensure that the `name` and `address` are not null or blank:

Hint:

- Create a constructor that initializes the `name` and `address` fields for the `Store` object.
- Add `@Entity` annotation above class name

### OrderDetails Model

1. Open the `OrderDetails.java` file
2. Add the following attributes along with the getters and setters:

- `id`: `private Long`; auto increment
- `customer`: `private Customer`; maps to the customer who placed the order
- `store`: `private Store`; maps to the store from where the order was placed
- `totalPrice`: `private Double`; total price of the order
- `date`: `private LocalDateTime`; date and time when the order was placed
- `orderItems`: `private List<OrderItem>`; list of items in the order

Hint:

- Use the `@Id` and `@GeneratedValue(strategy=GenerationType.IDENTITY)` for `id` to auto increment it and set it as the primary key
- Use `@Entity` annotation above the class name to declare it as a JPA entity
- Use `@ManyToOne` with `@JoinColumn(name="customer_id")` for the `customer` field and apply `@JsonManagedReference`
- Use `@OneToMany(mappedBy="order", fetch=FetchType.EAGER)` for the `orderItems` fields to establish the relationship with the `OrderItem` class, and apply `@JsonManagedReference` to prevent circular references during JSON serialization

3. Add the necessary constructors:

- A no-argument constructor
- A parameterized constructor that takes `Customer`, `Store`, `Double totalPrice`, and `LocalDateTime date` as parameters

### OrderItem Model

1. Open the `OrderItem.java` file
2. Add the following attributes along with getters and setters:

- `id`: `private Long`; auto increment
- `order`: `private OrderDetails`; refers to the order this item belongs to
- `product`: `private Product`; refers to the product in the order
- `quantity`: `private Integer`; quantity of the product in the order
- `price`: `private Double`; price of the product at the time of the order

Hint:

- Use `@Id` and `@GeneratedValue(strategy=GenerationType.IDENTITY)` for `id` to auto increment it and set it as the primary key
- Use `@Entity` annotation above the class name to declare it as a JPA entity
- Use `@ManyToOne` with `@JoinColumn(name="order_id")` for the `order` field and apply `@JsonManagedReference` to prevent circular references

3. Add the necessary constructors:

- A no-argument constructor
- A parameterized constructor that takes `OrderDetails order`, `Product product`, `Integer quantity`, and `Double price` as parameters

### Review Model

1. Open the `Review.java` file
2. Add the following attributes along with getters and setters:

- `customerId`: `private Long`; represents the customer who created the review, cannot be empty
- `productId`: `private Long`; represents the product being reviewed, cannot be empty
- `storeId`: `private Long`; represents the store associated with the product, cannot be empty
- `rating`: `private Integer`; represents the rating given to the product(out of 5), cannot be empty
- `comment`: `private String`; an optional comment on the product

1. Set up validation:

- Apply `@NotNull` annotation to the `customerId`, `productId`, `storeId`, and `rating` fields to ensure they cannot be null.

Hint:

- Example: `@NotNull(message="Customer cannot be null")`

4. Use the `@Document` annotation to indicate that this class represents a MongoDB document.

Hint:

- Annotate the class with `@Document(collection="reviews")` to specify the MongoDB collection for the `Review` entity.

5. Define a constructor to initialize a `Review` object with the `customerId`, `productId`, `storeId`, `rating`) and optionally for `comment`.
6. Ensure the `id` field is the unique identifier used by MongoDB:

- The `id` field is of type `String` and will be automatically generated by MongoDB. Use `@Id` annotation to mark it as the primary key.

## Part 3: Create Repositories and Services

Create repositories and services in this layer. Repositories handle direct communication with the database, providing methods to fetch, save, update, and delete data, often using JPA. Services sit above repositories and contain the business logic, coordinating between repositories and controllers to process data and enforce rules before returning results.
Will also notice the use of Data Transfer Objects or DTOs. These are classes that are used to transfer data between different layers of the application, such as between the service and controller layers. In the repository layer, DTO's are often used in custom queries to fetch specific fields efficiently, improving performance and security. This approach reduces memory usage, keeps APIs clean, and separates internal entity design from external data structures.

### Customer Repository

1. Open the `CustomerRepository.java` file
2. Create a repository for the `Customer` model by extending `JpaRepository`. This will allow for basic CRUD operations without needing to implement the methods manually.
3. Add the following methods:

- `findByEmail`: Find a customer by their email address.
- Return type: `Customer`
- Parameter: `String email`
- `findById`: Find a customer by their ID.

Hint:

- Extend `JpaRepository<Customer, Long>` to inherit basic CRUD functionality.
- Declare custom query methods like `findByEmail` and `findById` for additional queries.

For example: `Customer findByEmail(String email);

### Inventory Repository

1. Open the `InventoryRepository.java` file
2. Add the following methods:

- `findByProductIdandStoreId`: Find an inventory record by its product ID and store ID.
- Return type: `Inventory`
- Parameters: `Long productId`, `Long storeId`
- Query: `"SELECT i FROM Inventory i WHERE i.product.id = :productId AND i.store.id = :storeId"`
- `findByStore_Id`: Find a list of inventory records for a specific store.
- Return type: `List<Inventory>`
- Parameter: `Long storeId`
- `deleteByProductId`: Delete all inventory records related to a specific product ID.
- Return type: `void`
- Parameter: `Long productId`
- Use `@Modifying` and `@Transactional` annotations to modify the database and ensure the transaction is managed correctly.

Hint:

- Extend `JpaRepository<Inventory, Long>` for basic CRUD functionality.
- Use `@Query` for custom queries, `@Modifying` for update/delete queries, and `@Transactional` to handle transactions.

### OrderDetails Repository

1. Open the `OrderDetailsRepository.java` file
2. Create a repository for the `OrderDetails` model by extending `JpaRepository`. This will allow for basic CRUD operations without needing to implement the methods manually.

Hint:

- Extend `JpaRepository<OrderDetails, Long>` to inherit basic CRUD functionality.
- No custom methods are required for this repository as it handles basic operations for the `OrderDetails` model.

### OrderItem Repository

1. Open the `OrderItemRepository.java` file
2. Create a repository for the `OrderItem` model by extending `JpaRepository`. This will allow for basic CRUD operations without needing to implement the methods manually.

Hint:

- Extend `JpaRepository<OrderItem, Long>` to inherit basic CRUD functionality.
- No custom methods are required for this repository as it handles basic operations for the `OrderItem` model.

### Product Repository

1. Open the `ProductRepository.java` file.
2. Create a repository for the `Product` model by extending `JpaRepository`. This will allow for basic CRUD operations and custom queries.
3. Add the following methods:

- `findAll`: Find all products.
- Return type: `List<Product>`
- Hint: Use the built-in `findAll` method from `JpaRepository`.
- `findByCategory`: Find products by their category.
- Return type: `List<Product>`
- Parameter: `String category`
- Hint: Use the `findBy` convention with `category`.
- `findByPriceBetween`: Find products within a price range.
- Return type: `List<Product>`
- Parameters: `Double minPrice`, `Double maxPrice`
- Hint: Use the `findBy` convention with `priceBetween`.
- `findBySKU`: Find a product by its SKU.
- Return type: `List<Product>`
- Parameter: `String sku`
- Hint: Use the `findBy` convention with `sku`.
- `findByName`: Find a product by its name.
- Return type: `Product`
- Parameter: `String name`
- Hint: Use the `findBy` convention with `name`.
- `findById`: Find a product by its ID.
- Return type: `Product`
- Parameter: `Long id`
- Hint: Use the `findBy` convention with `id`.
- `findByNameLike`: Find products by a name for a specific store.
- Return type: `List<Product>`
- Parameters: `Long storeId`, `String pname`
- Use `@Query` annotation and write the following query.
- `@Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND i.product.category = :category")`
- `findByNameAndCategory`: Find products by name and category for a specific store.
- Return type: `List<Product>`
- Parameters: `Long storeId`, `String category`
- `@Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND LOWER(i.product.name) LIKE LOWER(CONCAT('%', :pname, '%')) AND i.product.category = :category")` to combine `name` and `category` filters.
- `findByCategoryAndStoreId`: Find products by category for a specific store.
- Return type: `List<Product>`
- Parameters: `Long storeId`, `String category`
- Use `@Query` annotation and write the following query `@Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId AND i.product.category = :category")` to filter both `category` and `storeId`.
- `findProductBySubName`: Find products by a name pattern (ignoring case).
- Return type: `List<Product>`
- Parameter: `String pname`
- Use `@Query` annotation and write the following query `@Query("SELECT i FROM Product i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :pname, '%'))")` for partial matching in `name`.
- `findProductsByStoreId`: Find all products for a specific store.
- Return type: `List<Product>`
- Parameters: `Long storeId`
- Use `@Query` annotation and write the following query `@Query("SELECT i.product FROM Inventory i WHERE i.store.id = :storeId")` to join `Inventory` and filter by `storeId`.
- `findProductByCategory`: Find products by category for a specific store.
- Return type: `List<Product>`
- Parameters: `String category`, `Long storeId`
- Use `@Query` annotation and write the following query `@Query("SELECT i.product FROM Inventory i WHERE i.product.category = :category and i.store.id = :storeId")` and filter by `storeId` to filter by `category` and `storeId`.
- `findProductBySubNameAndCategory`: Find products by name pattern and category.
- Return type: `List<Product>`
- Parameters: `String pname`, `String category`
- Use `@Query` annotation and write the following query `@Query("SELECT i FROM Product i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :pname, '%')) AND i.category = :category")` to match both `name` and `category` criteria.

Hint:

- Extend `JpaRepository<Product, Long>` to inherit basic CRUD functionality.
- Use `@Query` for more complex queries that involve custom conditions.
- Use `LOWER` in queries to make case-insensitive searches.
- Use `CONCAT` for partial matching on product names.

### Review Repository

1. Open the `ReviewRepository.java` file
2. Create a repository for the `Review` model by extending `MongoRepository`. This will allow for basic CRUD operations and custom queries.
3. Add the following method:

- `findByStoreIdAndProductId`: Retrieve reviews for a specific product and store.
- Return type: `List <Review>`
- Parameters: `Long storeId`, `Long productId`
- Use the `findBy` convention with `StoreId` and `ProductId` to filter reviews.
  Hint:
- Extend `MongoRepository<Review, String>` to work with MongoDB for review-related CRUD operations.
- Use `findBy` naming convention to create queries that retrieve data based on specific field values (in this case, `storeId`, and `productId`).

### Store Repository

1. Open the `StoreRepository.java` file
2. Create a repository for the `Store` model by extending `JpaRepository`. This will allow for basic CRUD operations and custom queries.
3. Add the following methods:

- `findById`: Retrieve a store by its ID.
- Return Type: `Store`
- Parameter: `Long id`
- Use `findById` to fetch a store based on its ID.
- `findBySubName`: Retrieve stores whose name contains a given substring.
- Return Type: `List<Store>`
- Parameter: `String pname`
- Use `@Query` with `LOWER` and `CONCAT` to create a case-insensitive search based on a substring of the store name.
  Hint:
- Extend `JpaRepository<Store, Long>` to interact with the `Store` table.
- For custom queries, use `@Query` annotation with JPQL(Java Persistence Query Language).
- Use `LOWER` to make the search case-insensitive, and `CONCAT` for substring matching.

### OrderService

1. Open the `OrderService.java` file
2. Autowire the necessary repositories and keep the access specifier as private for Example:

```
@Autowired
 private ProductRepository productRepository;
```

- `ProductRepository` for accessing the product data.
- `InventoryRepository` for accessing inventory data.
- `CustomerRepository` for validating product IDs and inventory.
- `StoreRepository` for accessing the store data.
- `OrderDetailsRepository` for saving the order details data.
- `OrderItemRepository` for saving the item ordered details data.

3. Create a service class to manage the order process, including retrieving customer data, creating orders, and saving order items.
   Hint: Add `@Service` annotation above the class definition.
4. Add the following logic:

- `saveOrder Method`: This method processes a customer's order, including saving the order details and associated items.
- Parameters: `PlaceOrderRequestDTO placeOrderRequest`(Request data for placing an order)
- Return type: `void` (This method doesn't return anything, it just processes the order)
- 1. `Retrieve or Create the Customer`: Check if the customer already exists by their email using `findByEmail`. If the customer exists, use the existing customer, otherwise, create a new `Customer` and save it to the repository.
- Hint: Use `customerRepository.findByEmail()` to check for an existing customer and `customerRepository.save()` to save a new customer.
- 2. `Retrieve the Store`: Fetch the store by ID from `storeRepository`. If the store doesn't exist, throw an exception.
- Hint: Use `storeRepository.findById()` to retrieve the store.
- 3. Create `OrderDetails`: Create a new `OrderDetails` object and set customer, store, total, price, and the current datetime.
- Hint: Set the order date with `java.time.LocalDateTime.now()` and save the order with `orderDetailsRepository.save()`.
- 4. `Create and Save OrderItems`: For each product purchased, find the corresponding `Inventory`, update its stock level, and save the changes.
- Hint: Use `inventoryRepository.findByProductIdandStoreId()` to get the inventory and `inventoryRepository.save()` to update it.
- 5. Create `OrderItem` for each product and associate it with the `OrderDetails`.
- Hint: Use `orderItemRepository.save()` to save each order item.

Additional Hints:

1. `Managing Customer Creation`: If the customer is not found in the database, a new `Customer` object will be created and saved to the repository. Make sure to handle the case where the customer already exists and reuse the existing `Customer`.
2. `Store Retrieval`: Always check if the store is present in the database by checking the `Optional` value returned by `findById`. If not, throw a runtime exception to handle the error.
3. `Order Details`: Ensure that the `totalPrice` is passed correctly from the request data. It's also important to store the current timestamp for when the order is created. Use `java.time.LocalDateTime.now()` to capture the time.
4. `Order Item Creation`: For each product in the `purchaseProduct` list, create an `OrderItem`, and ensure the inventory is updated accordingly. Decrease the stock level of the product in the store's inventory after each purchase.
5. `Stock Level Management`: Be mindful of inventory changes. If the product's stock level is updated (decreased in this case), remember to save the modified `Inventory` object back to the repository.
6. `Handling Multiple Items`: If the order contains multiple items, iterate through each product, check the inventory, adjust the stock level, and store each corresponding `OrderItem`.

### ServiceClass

1. Open the `ServiceClass.java` file
2. Create a service class to validate inventory and products, and fetch related data.
3. Hint: Add `@Service` annotation above the class definition.
4. Declare necessary repository to be used as private For Example:
   `private final InventoryRepository inventoryRepository;`

- `ProductRepository` for accessing the product data.
- `InventoryRepository` for accessing inventory data.

1. Add the following logic:

- `validateInventory`: This method check whether an inventory record exists for a given product and store combination.
- Parameters: `Inventory inventory`(The inventory object you want to validate)
- Return Type: `boolean` (Returns `false` if an inventory record already exists for the given product, and store, otherwise `true`)
- Hint: Use `inventoryRepository.findByProductIdandStoreId()` to check for existing inventory records. Return `false` if the inventory already exists, and `true` otherwise.
- `validateProduct`: This method check whether a product exists by its name.
- Parameters: `Product product` (The product you want to validate)
- Return Type: `boolean` (Returns `false` if a product with the same name already exists, otherwise `true`)
- Hint: Use `productRepository.findByName()` to check if a product with the same name already exists in the database. If it exists, return `false`; otherwise, return `true`.
- `ValidateProductId`: This method validates whether a product exists by its ID.
- Parameters: `long id` (The product ID you want to validate)
- Return Type: `boolean` (Returns `false` if the product does not exist with the given ID, otherwise `true`)
- Hint: Use `productRepository.findById()` to check if the product exists. If the product is `null`, return `false`.
- `getInventoryId`: This method fetches the inventory record for a given product and store combination.
- Parameters: `Inventory inventory` (The inventory object to search for)
- Return type: `Inventory` (Returns the found inventory record for the given product and store combination).
- Hint: Use `inventoryRepository.findByProductIdandStoreId()` to get the inventory record.

### Additional Hints

1. `validateInventory Method`: Use this method to ensure there's no duplicate inventory entry for a product-store pair. If a product is already associated with the store, it will return false, meaning no new inventory can be added.
2. `validateProduct Method`: This check ensures that no two products with the same name exist in your product database. You can expand this validation to check for other attributes if needed (e.g., SKU).
3. `ValidateProductId Method`: This method helps ensure the product exists by its ID. Before attempting to create an order or any other action, you can use this method to validate the existence of a product with a given ID.
4. `getInventoryId Method`: This method is useful when you need to retrieve an existing inventory record for a product in a specific store. It can be used when updating stock levels or performing any inventory-related checks.

## Part 4: Create Controllers

Controllers handle incoming HTTP requests, map them to appropriate service methods, and return responses to client. They act as the entry point of the application, managing the flow between the frontend and backend while keeping the business logic separate.

### Inventory Controller

`Purpose`: This controller handles the CRUD operations for managing inventory. It provides endpoints for updating, saving, searching, and validating inventory and products.

1. Open the `InventoryController.java` file
2. `Set Up the Controller Class`

- Annotate the class with `@RestController` to designate it as a REST controller for handling HTTP requests.
- Use `@RequestMapping("/inventory")` to set the base URL path for all methods in this controller.
- 3. Autowired Dependencies
- Autowire the necessary services and repositories:
- ProductRepository for accessing the product data.
- InventoryRepository for accessing the inventory data.
- ServiceClass for validating product IDs and inventory.
- 4. Define the `updateInventory` Method
- Annotate this method with `@PutMapping`.
- The method should accept a request body of type `CombinedRequest`, which contains both a `Product` and `Inventory`.
- It should return a `Map<String, String>`.
- Validate the product ID. Hint: Use method created in `ServiceClass` to validate productId. If the product doesn't exist, return an error message.
- Update the `Product` and `Inventory` if the product ID is valid:
- If the `Inventory` exists for the product, update it and save the changes also returns a key `message` with value "Successfully updated product"
- If the `Inventory` doesn't exist for the product, return a key `message` with value "No data available".
- Catch any exceptions (e.g., `DataIntegrityViolationException`) and handle them appropriately.
- 5. Define the `saveInventory` Method.
- Annotate this method with `@PostMapping`.
- The method should return `Map<String, String>`
- Check if the inventory already exists. Hint: Use the method `validateInventory(inventory)` created in `ServiceClass` to validate inventory.
- If it exists, return a `message` saying that the data s already present.
- It it doesn't exist, save the `Inventory` object to the repository and return `message` saying `data saved successfully`
- Catch ant exceptions related to data integrity or other errors and handle them appropriately.
- 6. Define the `getAllProducts` Method.
- Annotate this method with `@GetMapping("/{storeid}")` to retrieve products for a specific store.
- The method should accept a `storeId` as a path variable and fetch the products for that store. Hint: Call the `findProductByStoreId(storeid)` method on the `ProductRepository` to get the products belonging to the store with the given `storeid`.
- The method should return `Map<String, Object>`
- Return the list of products as part of a map with the key `"products"`.
- 7. Define the `getProductName` Method
- Annotate this method with `@GetMapping("filter/{category}/{name}/{storeid}")`.
- This method should filter products by category and name.
- The method should return `Map<String, Object>`
- If either `category` or `name` is `"null"`, adjust the filtering logic accordingly:
- If `category` is "null", filter by product name only.
- Hint: Call the `findByCategoryAndStoreId` method in the `ProductRepository` to get the filtered products by category.
- If both `category` and `name` are provided, filter products by both parameters.
- Hint: Call the `findByNameAndCategory` method on the `ProductRepository` to get the filtered products by name and category.
- Return the filtered products as part of the response map with the key "product".
- 8. Define the `searchProduct` Method
- Annotate this method with `@GetMapping("search/{name}/{storeId}")`.
- The method should return `Map<String,Object>`
- The method should search for products by name within a specific store.
- Hint: Use method `findByNameLike(storeId, name)` on the `ProductRepository` to search for products with names that match the `name` parameter.
- Return the products found in the response map with the key `"product"`.
- 9. Define the `removeProduct` Method
- Annotate this method with `@DeleteMapping("/{id}")`.
- The method should return `Map<String, String>`
- This method should delete a product by its `id`.
- Hint: Use method `ValidateProductId(id)` on the `ServiceClass` to check if the product exists. It it doesn't exist, return a message saying the product not present in the database.
- If the product exists, delete the corresponding `Inventory` entry.
- Hint: Use method `deleteByProductId(id)` on the `InventoryRepository`.
- Return a success message with key `message` indicating that the product was deleted.
- 10. Define the `validateQuantity` Method
- Annotate this method with `@GetMapping("validate/{quantity}/{storeId}/{productId}")`.
- The method should return `boolean`.
- This method should validate id a specified quantity of a product is available in stock at a given store.
- Retrieve the inventory for the product and store.
- Hint: Use method `findByProductIdandStoreId(productId, storeId)` on the `InventoryRepository`.
- If the stock level is greater than or equal to the requested quantity, return `true`. Otherwise, return `false`.

### Product Controller

Purpose: This controller manages the CRUD operations related to the `Product` entity in your application. Here's how to implement it step by step.

1. Open the `ProductController.java` file
2. Set Up the `Controller Class`
   Annotate the class with `@RestController` to denote it as a controller that handles REST API calls.
   Map the class to the `/product` URL by using `@RequestMapping("/product")`.

3. Autowired Dependencies
   You need the following dependencies injected via "@Autowired":
   `ProductRepository`: For interacting with the product data in the database.
   `ServiceClass`: For validating product data and checking business logic like product existence.
   `InventoryRepository`: To manage the inventory associated with the products.

4. Define `addProduct` Method
   `@PostMapping`: This method will handle POST requests to add a new product.
   This method will return `Map<String, String>`
   `Request Body`: Accept a `Product` object from the request body.
   `Validation`: Check if the product already exists.
   Hint: Use method `validateProduct()` on the `ServiceClass`.
   `Save Product`: If the product is valid, save it to the database.
   Hint: Use `save()` method of `ProductRepository` to save.
   After saving the product return a success message with key `message`.
   `Handle Errors`: Catch exceptions such as `DataIntegrityViolationException` for scenarios like unique SKU violations.

5. Define the `getProductById` Method
   `@GetMapping("/product/{id}")`: This method will handle GET requests to retrieve a product by its `id`.
   This method will return `Map<String, Object>`
   Path Variable: Use `@PathVariable` to accept the product `id` in the URL.
   Return Product: Fetch the product and return it in a map with key `products`.
   Hint: Use method `findById(id)` of `ProductRepository` to fetch product.

6. Define the `updateProduct` Method
   `@PutMapping`: This method will handle PUT requests to update an existing product.
   This method will return `Map<String, String>`
   Request Body: Accept a `Product` object to update.
   `Save Product`: Save the updated product.
   Hint: Use `save()` method of `ProductRepository` to save the updated Product.
   After updating the product return a success message with key `message`.
   Error Handling: Catch any errors during the save operation.

7. Define the `filterbyCategoryProduct` Method
   `@GetMapping("/category/{name}/{category}")`: This method filters products based on their `name` and `category`.
   This method will return `Map<String, Object>`
   Handle Null Parameters: If `name` or `category` is "null", apply conditional filtering logic accordingly.
   Return Filtered Products: Fetch products using repository methods like `findByCategory()`, `findByCategory(category)` or `findProductBySubNameAndCategory()` based on the provided filters and return in with key `products`

8. Define the `listProduct` Method
   `@GetMapping`: This method will handle GET requests to retrieve all products.
   This method will return `Map<String, Object>`
   Return All Products: Fetch and return all products with key `products`
   Hint: Use `findAll()` method of `ProductRepository` to fetch all the products.

9. Define the `getProductbyCategoryAndStoreId` Method
   `@GetMapping("filter/{category}/{storeId}")`: This method will filter products by `category` and `storeId`.
   This method will return `Map<String, Object>`
   Fetch Products: Retrieve all the products by `category` & `storeId` and return with key `product`.
   Hint: Use method `findProductByCategory()` of `ProductRepository`. to retrieve all the products by `category` and `storeId`

10. Define the `deleteProduct` Method
    `@DeleteMapping("/{id}")`: This method will handle DELETE requests to remove a product by its `id`.
    This method will return `Map<String, String>`
    Validation: Check if the product exists before deleting.
    Hint: Use `ValidateProductId()` method of `ServiceClass` to validate product.
    Delete Product: As `Inventory` is mapped with `Product` using foreign key constraint, delete from `Inventory` table first and then from `Product` table.
    Hint: Use method `deleteByProductId(id)` of `inventoryRepository` to remove the inventory entry and use method `deleteById(id)` of `productRepository` to delete the product.
    Return a success message with key `message` indicating that the product was deleted.

11. Define the `searchProduct` Method
    `@GetMapping("/searchProduct/{name}")`: This method will search for products by their `name`.
    This method will return `Map<String, Object>`
    Return Search Results: Search for products by name and return with key `products`.
    Hint: Use method `findProductBySubName()` of `ProductRepository` to search products.

### Review Controller

Purpose: The `ReviewController` handles endpoints for retrieving reviews for products in a store. It provides methods for getting all reviews or filtered reviews by store ID and product ID, with customer information associated with each review.

1. Open the `ReviewController.java` file
2. Set Up the Controller Class
   Action: Annotate the class with `@RestController` to designate it as a REST controller for handling HTTP requests.
   URL Mapping: Use `@RequestMapping("/reviews")` to define the base URL for all methods in this controller.

3. Autowired Dependencies
   Action: Autowire the necessary repositories:
   ReviewRepository for accessing the review data.
   CustomerRepository for retrieving customer details linked with reviews.

4. Define the `getReviews` Method
   URL Mapping: Use `@GetMapping("/{storeId}/{productId}")` to create an endpoint that retrieves reviews for a specific product in a store by `storeId` and `productId`.
   Method will return a `Map<String, Object>`
   Path Variables:
   `storeId`: The ID of the store.
   `productId`: The ID of the product.
   Logic:
   First retrieve all the reviews from the database of a specific product of a store. Hint: Use method `findByStoreIdAndProductId` of `ReviewRepository` to fetch all the reviews of a specific product of a store.
   Now from all the received reviews filter remove the unwanted data and keep `comment`, `rating`. Now add `name` of the customer to the table using `customer id` field in the reviews.
   Hint: Use method `findByid(review.getCustomerId())` of `CustomerRepository`

Return Key: The response will include a key named `reviews`. The value for this key will be a list of review objects, each containing the review comment, rating, and the name of the customer who wrote the review.
Customer Name Key: Each review object will include a key named `reviews`. The value for this key will be a list of review objects, each containing the review comment, rating, and the name of the customer who wrote the review.
Customer Name Key: Each review object will include a key named `customerName`, which will either contain the customer's name or "Unknown" if no customer is found.

### Store Controller

Purpose: The `StoreController` handles the operations related to stores, including adding a new store, validating an existing store, and placing an order. It integrates with the `StoreRepository` and the `OrderService` for managing store and order operations.

1. Open the `StoreController.java` file
2. Set Up the Controller Class
   Action: Annotate the class with `@RestController` to designate it as a REST controller for handling HTTP requests.
   URL Mapping: Use `@RequestMapping("/store")` to set the base URL path for all methods in this controller.

3. Autowired Dependencies
   Action: Autowire the necessary repositories and services:
   StoreRepository for accessing store data.
   OrderService for handling order-related functionality.

4. Define the `addStore` Method
   URL Mapping: Use `@PostMapping` to create an endpoint for adding a new store.
   Request Body: This method should accept a `Store` object in the request body.
   Return Key: The response will include a key named `message`. The value for this key will indicate that the store was successfully created and will include the store's ID.

5. Define the `validateStore` Method
   URL Mapping: Use `@GetMapping("validate/{storeId}")` to create an endpoint that checks if a store with a given `storeId` exists.
   Path Variable: `storeId` represents the ID of the store to be validated.
   Return Key: This method will return a `boolean` value indicating whether the store exists. If the store if found, the method will return `true`; otherwise, it will return `false`.

6. Define the `placeOrder` Method

- URL Mapping: Use `@PostMapping("/placeOrder")` to create an endpoint for placing an order.
  Request Body: This method should accept a `PlaceOrderRequestDTO` object in the request body.
  Return Key: The response will include a key named `message` with the value "Order placed successfully" if the order is successfully processed.
  Error Key: If an error occurs while placing the order, the response will include a key named `Error` with the value being the error message.

### Response Structure for Methods

- `addStore`: Returns a `Map<String, String>` with a key named `message` indicating the store ID.
- `validateStore`: Returns a `boolean` value to indicate if the store exists (true or false).
- `placeHolder`: Returns a `Map<String, String>` with either:
- `message`: "Order placed successfully" if the order is placed without issues.
- `Error`: The error message if there is a failure in processing the order.

### Global Exception Handler

`Purpose`: The `GlobalExceptionHandler` is responsible for handling exceptions globally across all controllers. It ensures that the application responds with meaningful error messages when an exception occurs, improving the user experience and maintaining consistent error handling.

1. Open the `GlobalExceptionHandler.java` file
2. Set Up the Global Exception Handler Class
   `Action`: Annotate the class with `@RestControllerAdvice`. This annotation makes the class capable of handling exceptions globally for all REST controllers in your application.
3. Define the `handleJsonParseException` Method
   `Exception Type`: Use the `@ExceptionHandler(HttpMessageNotReadableException.class)` annotation to handle `HttpMessageNotReadableException`. This exception typically occurs when the request body is not formatted correctly (e.g., invalid JSON syntax).
   `HTTP Status`: Use `@ResponseStatus(HttpStatus.BAD_REQUEST)` to specify that the response will have an HTTP status of `400 Bad Request` when this exception is thrown.
   `Return Key`: The method will return a `Map<String, Object>` containing the following key:
   `message`: A descriptive error message indicating that the input provided is invalid. The value of the `message` key should be: "Invalid input: The data provided is not valid.".

### Exception Handling Flow

- If a request fails to parse correctly (e.g., due to invalid JSON), the application will invoke the `handleJsonParseException` method.
- This will return a response with a `400 Bad Request` status and the error message under the key `message`.

## Part 5: Load Data

### Load NoSQL MongoDB Data

1. The review data is stored in MongoDB. It is available in the file `/home/project/java-database-final/reviews.json`. You can open the file and learn more about the data.

The file contains an array of reviews. Each review has the following information:

- productID: unique id of the product
- customerID: unique id of the customer who wrote the review
- storeID: unique id of the store where the product was purchased
- rating: rating given by the customer in the review
- comment: any comments left by the customer as part of the review

#### Example:

```
{
    "productId": 1,
    "customerId": 78,
    "storeId": 1,
    "rating": 3,
    "comment": "The quality exceeded my expectations."
  },
```

2. Will use the `mongoimport` utility to load this data. Execute the following command in the regular terminal in the lab environment.

```
mongoimport --uri="mongodb://root:{MONGODB_PASSWORD}@{MONGODB_HOST}/reviews?authSource=admin" --collection=reviews --file /home/project/java-database-final/reviews.json --jsonArray
```

Replace the `${MONGODB_HOST}` and `${MONGODB_PASSWORD}` from the MongoDB configuration panel.
Should see a message informing all documents were inserted successfully and there was no failure.

```
250 document(s) imported successfully. 0 document(s) failed to import.
```

### Load MySQL Data

Before we load the sample data, we need to create the database.

1. Switch to the `MySQL CLI` terminal and create the `inventory` database.

`create database inventory;`

Also, before we load sample data, we need to run the backend application so all the tables are created.

2. Switch back to the standard terminal, and run the following command in order to run the backend application:
   `cd /home/project/java-database-final/back-end && mvn spring-boot:run`

This should start the Spring Boot application on port 8080.

Next, we will load some sample data to the database. The SQL commands to load data are located in the `insert_data_sql` file in your repository. Can open and explore the insert_data.sql file. It first targets the `inventory` database using the `use inventory;` command. It then inserts sample data into `product`, `store`, `inventory`, `customer`, `order_details`, and `order_item` tables.

3. In the standard terminal, because the application is still running from the previous step, must use the `Ctrl+c` keyboard shortcut to kill the process, before you can run the next command.
4. Now, copy the following command into the terminal window, BUT DON'T RUN IT YET:

`mysql -h {MYSQL_HOST} -uroot -p{MYSQL_PASSWORD} < /home/project/java-database-final/insert_data.sql`

5. Replace the `{MYSQL_HOST}` and `{MYSQL_PASSWORD}` information with your real host and password details from the `Connection Information` section of the `MySQL` tab.
6. Now, can run the command above to load the sample data from the `insert_data.sql` file.
   Won't see any output from this step, but the cursor will come back with no errors.

## Part 6: Stored Procedures

Stored procedures are pre-written SQL scripts stored in the database that perform complex operations. They can be called from repositories to execute tasks efficiently, often used for batch operations, heavy calculations, or when database-side logic is preferred for performance or security reasons.

### Monthly Sales Reports using Stored Procedures

#### Stored Procedure: Monthly Sales for Each Store

Write a stored procedure to generate monthly sales for each store, based on a specific year and month.
Hint:

- Create a stored procedure that accepts `year_param` and `month_param` as input parameters.
- Use `SELECT` with `SUM(DISTINCT od.total_price)` to calculate the total sales
- Use `SELECT` with `SUM(DISTINCT od.total_price)` to calculate the total sales
- Use `GROUP BY` to group by `store_id`, `MONTH(od.date)`, and `YEAR(od.date)`
- Sort by `total_sales DESC` to display stores with the highest sales fist.

Example Call: `CALL GetMonthlySalesForEachStore(2025, 3);`
Example Output:

```
  mysql> CALL GetMonthlySalesForEachStore(2025, 3);
  +----------+-------------+------------+-----------+
  | store_id | total_sales | sale_month | sale_year |
  +----------+-------------+------------+-----------+
  |        8 |      837.03 |          3 |      2025 |
  |        1 |      491.33 |          3 |      2025 |
  |        5 |      390.75 |          3 |      2025 |
  |       10 |      283.09 |          3 |      2025 |
  |        7 |      233.63 |          3 |      2025 |
  |        2 |      211.93 |          3 |      2025 |
  |        3 |      140.68 |          3 |      2025 |
  +----------+-------------+------------+-----------+
  7 rows in set (0.05 sec)

  Query OK, 0 rows affected (0.05 sec)
```

### Stored Procedure: Aggregate Sales for Company

Write a stored procedure to calculate the total sales for the company across all stores for a specific month and year.

Hint:

- Create a stored procedure that accepts `year_param` and `month_param` as input parameters.
- Use `SUM(DISTINCT od.total_price)` to calculate the aggregate sales.
- Use `GROUP BY` to group by `MONTH(od.date)` and `YEAR(od.date)`.
- Example Call: `CALL GetAggregateSalesForCompany(2025 3);`

Example Output:

```
mysql> CALL GetAggregateSalesForCompany(2025, 3);
+-------------+------------+-----------+
| total_sales | sale_month | sale_year |
+-------------+------------+-----------+
|     2588.44 |          3 |      2025 |
+-------------+------------+-----------+
1 row in set (0.00 sec)
```

### Identifying Top-Selling Products

#### By Category

Write a stored procedure to identify the top-selling products by category for a given month and year.
Hint:

- Create a stored procedure that accepts `target_month` and `target_year` as input parameters.
- Use `SUM(oi.quantity)` to calculate the total quantity sold.
- Use a subquery to find the product with the highest total quantity sold within each category.
- Group by `category` and `name` to identity the top-selling product in each category.

Example Call: `CALL GetTopSellingProductsByCategory(3, 2025);`

Example Output:

```
 mysql> CALL GetTopSellingProductsByCategory(3, 2025);
+----------------------+----------------------+---------------------+-------------+
| category             | name                 | total_quantity_sold | total_sales |
+----------------------+----------------------+---------------------+-------------+
| Accessories          | Sony WH-1000XM4      |                   4 |      179.96 |
| Home Appliances      | Dyson Vacuum Cleaner |                   3 |       89.97 |
| Laptops and Monitors | MacBook Pro 16"      |                   3 |      189.97 |
| Mobile               | Google Pixel 5       |                   3 |      149.97 |
| TV and AV            | LG NanoCell TV       |                   2 |      119.98 |
| TV and AV            | Sony Bravia 4K TV    |                   2 |      139.98 |
+----------------------+----------------------+---------------------+-------------+
6 rows in set (0.03 sec)

Query OK, 0 rows affected (0.03 sec)
```

### By Store

Write a stored procedure to identify the top-selling product by store for a given month and year.
Hint:

- Create a stored procedure that accepts `target_month` and `target_year` as input parameters.
- Use `SUM(oi.quantity)` to calculate the total quantity sold for each product in each store.
- Use a subquery to find the product with the highest total quantity sold for each store.
- Group by `store_id` and `name` to identify the top-selling product in each store.

Example Call: `CALL GetTopSellingProductByStore(3, 2025);`

Example Output:

```
mysql> CALL GetTopSellingProductByStore(3, 2025);
+-------------------------+----------+---------------------+--------------------+
| product_name            | store_id | total_quantity_sold | total_sales        |
+-------------------------+----------+---------------------+--------------------+
| Google Pixel 5          |        1 |                   3 |             149.97 |
| Samsung Galaxy Z Fold 3 |        2 |                   2 |              79.98 |
| Sony Bravia 4K TV       |        3 |                   2 |             139.98 |
| Dyson Vacuum Cleaner    |        5 |                   3 |              89.97 |
| Dell XPS 13             |        7 |                   2 |             159.98 |
| MacBook Pro 16"         |        8 |                   3 |             189.97 |
| Sony WH-1000XM4         |        8 |                   3 |             119.97 |
| Bose QuietComfort 35 II |       10 |                   3 | 269.96999999999997 |
+-------------------------+----------+---------------------+--------------------+
8 rows in set (0.05 sec)

Query OK, 0 rows affected (0.05 sec)
```

## Part 7: Run the Applications

### Run the backend

1. In the standard `theia@theiadocker` terminal, run the following command to run the backend application. If the application is still running from a previous process, use the `Ctrl+c` keyboard shortcut to kill the process and then run the command:
   `cd /home/project/java-database-final/back-end && mvn spring-boot:run`
   This should start the Spring Boot application on port 8080.
2. If you get a message saying that the port 8080 is already in use, use the following command to kill the previously running program on that port.
   `fuser -k 8080/tcp`
3. Then run the command to run the back-end application:
   `cd /home/project/java-database-final/back-end && mvn spring-boot:run`

### Launch the backend and copy the URL

Use the Launch Application feature of the lab environment. Enter `8080` as the port and click on the `Open in new browser tab` icon. Will see an error that is absolutely fine. Copy the complete URL. Will need it in the next step to connect the frontend to the backend.

### Configure the frontend

1. Open the `script.js` file and set the `apiURL` to the backend URL you copied above. Do not copy the trailing `/`.

For example, this might look like:

`const apiURL = 'https://captainfedo1-8080.theiadockernext-0-labs-prod-theiak8s-4-tor01.proxy.cognitiveclass.ai';`

The URL might be different

1. Open the `reviews.html` file and set the URL in the `function getReviews(storeId, productId)` method to the backend URL as well. Just change the `URL` as you still need the remaining part of the variable.
   `url = `URL/reviews/${storeId}/${productId}`;`

Here is an example of what that might look like:

```
function getReviews(storeId, productId) {
            url = `https://captainfedo1-8080.theiadockernext-0-labs-prod-theiak8s-4-tor01.proxy.cognitiveclass.ai/reviews/${storeId}/${productId}`;
            fetch(url, {
                method: "GET",
                headers: { "content-type": "application/json" }
            })
```

The URL will be different from what is shown above.

### Run the frontend

1. In the terminal, use the following command to run the front-end application:
   `cd /home/project/java-database-final/front-end && python3 -m http.server`

This should start a Python server and serve the front end files on port 8000.

2. Can now visit the front end by using the `Launch Application` feature of the lab environment. Enter `8000` as the port and click on the `Open in new browser tab` icon.

## Solution Stored Procedures

### Monthly sales by store

```
DELIMITER

CREATEPROCEDUREGetMonthlySalesForEachStore(INyearp​aramINT,INmonthp​aramINT)BEGINSELECTod.storei​d,SUM(DISTINCTod.totalp​rice)AStotals​ales,−−UseDISTINCTtoavoidduplicatesMONTH(od.date)ASsalem​onth,YEAR(od.date)ASsaley​earFROMorderd​etailsodJOINorderi​temoiONod.id=oi.orderi​dWHEREYEAR(od.date)=yearp​aramANDMONTH(od.date)=monthp​aramGROUPBYod.storei​d,MONTH(od.date),YEAR(od.date)ORDERBYtotals​alesDESC;END

DELIMITER ;
```

#### Example Usage

CALL GetMonthlySalesForEachStore(2025, 3);

### Aggregate sales for the company

```
DELIMITER

CREATEPROCEDUREGetAggregateSalesForCompany(INyearp​aramINT,INmonthp​aramINT)BEGINSELECTSUM(DISTINCTod.totalp​rice)AStotals​ales,MONTH(od.date)ASsalem​onth,YEAR(od.date)ASsaley​earFROMorderd​etailsodJOINorderi​temoiONod.id=oi.orderi​dWHEREYEAR(od.date)=yearp​aramANDMONTH(od.date)=monthp​aramGROUPBYMONTH(od.date),YEAR(od.date);END

DELIMITER ;
```

#### Example Usage

CALL GetAggregateSalesForCompany(2025, 3);

### Identify top selling products by category

```
DELIMITER

CREATEPROCEDUREGetTopSellingProductsByCategory(INtargetm​onthINT,INtargety​earINT)BEGINSELECTp.category,p.name,SUM(oi.quantity)AStotalq​uantitys​old,SUM(oi.price∗oi.quantity)AStotals​alesFROMproductpJOINorderi​temoiONp.id=oi.producti​dJOINorderd​etailsodONoi.orderi​d=od.idWHEREMONTH(od.date)=targetm​onth−−UsetheprovidedmonthANDYEAR(od.date)=targety​ear−−UsetheprovidedyearGROUPBYp.category,p.nameHAVINGSUM(oi.quantity)=(SELECTMAX(totalq​uantity)FROM(SELECTSUM(oi2.quantity)AStotalq​uantityFROMorderi​temoi2JOINorderd​etailsod2ONoi2.orderi​d=od2.idJOINproductp2ONoi2.producti​d=p2.idWHEREMONTH(od2.date)=targetm​onth−−SamemonthANDYEAR(od2.date)=targety​ear−−SameyearANDp2.category=p.category−−SamecategoryGROUPBYp2.name−−Groupbyproductnametocalculatethetotalforeachproduct)ASSubquery)ORDERBYp.category;END

DELIMITER ;
```

#### Example Usage

CALL GetTopSellingProductsByCategory(3, 2025);

### Identify top selling products by store

```
DELIMITER

CREATEPROCEDUREGetTopSellingProductByStore(INtargetm​onthINT,INtargety​earINT)BEGINSELECTp.nameASproductn​ame,od.storei​d,SUM(oi.quantity)AStotalq​uantitys​old,SUM(oi.price∗oi.quantity)AStotals​alesFROMproductpJOINorderi​temoiONp.id=oi.producti​dJOINorderd​etailsodONoi.orderi​d=od.idWHEREMONTH(od.date)=targetm​onth−−UsetheprovidedmonthANDYEAR(od.date)=targety​ear−−UsetheprovidedyearGROUPBYod.storei​d,p.name−−GroupbystoreandproductnameHAVINGSUM(oi.quantity)=(SELECTMAX(totalq​uantity)FROM(SELECTSUM(oi2.quantity)AStotalq​uantityFROMorderi​temoi2JOINorderd​etailsod2ONoi2.orderi​d=od2.idJOINproductp2ONoi2.producti​d=p2.idWHEREMONTH(od2.date)=targetm​onth−−SamemonthANDYEAR(od2.date)=targety​ear−−SameyearANDod2.storei​d=od.storei​d−−SamestoreGROUPBYp2.name−−Groupbyproductname)ASSubquery)ORDERBYod.storei​d;END

DELIMITER ;
```

#### Example Usage

CALL GetTopSellingProductByStore(3, 2025);

## Conclusion

Have successfully demonstrated your understanding of how to build a backend system for a retail management system by using your Java, Spring Boot, and MySQL Skills.

Have successfully configured the backend to use the relevant databases for your data, and built the required stored procedures. Also created the required Spring Boot models, repositories, services, and controllers. And finally, successfully integrated the backend built with the pre-built front-end to run the project's application and tested it end-to-end.

The repository linked to the backend system can be checked out [here](https://github.com/jamesb97/java-database-final)
