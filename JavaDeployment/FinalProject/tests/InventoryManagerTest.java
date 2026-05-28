package inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Unit tests for InventoryManager class.
 * TODO: Write comprehensive tests for inventory management operations.
 */
class InventoryManagerTest {

    // TODO: Declare test instance
    private InventoryManager manager;

    /**
     * TODO: Set up fresh instance before each test
     */
    @BeforeEach
    void setUp() {
        // TODO: Create new InventoryManager instance
        manager = new InventoryManager();
    }

    /**
     * TODO: Test adding products to inventory
     */
    @Test
    void testAddProduct() {
        // TODO: Add a valid product
        // Verify it's added successfully
        // Check inventory contains the product
        manager.addProduct("B001", "Test Book", "BOOK", 20.0, 10);
        Product product = manager.getProductById("B001");
        assertNotNull(product);
        assertEquals("Test Book", product.getName());
    }

    /**
     * TODO: Test adding product with invalid parameters
     */
    @Test
    void testAddInvalidProduct() {
        // TODO: Try adding product with invalid price
        // Verify appropriate error handling
        // Check inventory remains unchanged
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            manager.addProduct("B002", "Invalid Book", "BOOK", -5.0, 10);
        });
        assertEquals("Books must have a minimum price of $5.00", exception.getMessage());
        assertNull(manager.getProductById("B002"));
    }

    /**
     * TODO: Test selling products
     */
    @Test
    void testSellProduct() {
        // TODO: Add product to inventory
        // Sell some quantity
        // Verify quantity is reduced correctly
        // Test with different discount types
        manager.addProduct("B001", "Test Book", "BOOK", 20.0, 10);
        manager.sellProduct("B001", 2, "STUDENT");
        Product product = manager.getProductById("B001");
        assertEquals(8, product.getQuantity());
    }

    /**
     * TODO: Test selling more than available stock
     */
    @Test
    void testSellInsufficientStock() {
        // TODO: Add product with limited stock
        // Try to sell more than available
        // Verify transaction is rejected
        // Check stock remains unchanged
        manager.addProduct("B001", "Test Book", "BOOK", 20.0, 10);
        boolean result = manager.sellProduct("B001", 15, "STUDENT");
        assertFalse(result);
        Product product = manager.getProductById("B001");
        assertEquals(10, product.getQuantity());
    }

    /**
     * TODO: Test adding stock to existing product
     */
    @Test
    void testAddStock() {
        // TODO: Add product to inventory
        // Add stock to the product
        // Verify quantity is increased correctly
        manager.addProduct("B001", "Test Book", "BOOK", 20.0, 10);
        manager.addStock("B001", 5);
        Product product = manager.getProductById("B001");
        assertEquals(15, product.getQuantity());
    }

    /**
     * TODO: Test inventory value calculation
     */
    @Test
    void testInventoryValue() {
        // TODO: Add multiple products with known values
        // Calculate expected total value
        // Verify getInventoryValue() returns correct amount
        manager.addProduct("B001", "Test Book", "BOOK", 20.0, 10); // $200
        manager.addProduct("E001", "Test Electronics", "ELECTRONICS", 100.0, 5); // $500
        double expectedValue = 200.0 + 500.0;
        assertEquals(expectedValue, manager.getInventoryValue(), 0.001);
    }

    /**
     * TODO: Test low stock detection
     */
    @Test
    void testLowStockProducts() {
        // TODO: Add products with various stock levels
        // Call getLowStockProducts() with threshold
        // Verify correct products are returned
        manager.addProduct("B001", "Test Book", "BOOK", 20.0, 10);
        manager.addProduct("E001", "Test Electronics", "ELECTRONICS", 100.0, 3);
        List<Product> lowStock = manager.getLowStockProducts(5);
        assertEquals(1, lowStock.size());
        assertEquals("E001", lowStock.get(0).getId());
    }

    /**
     * TODO: Test operations on non-existent products
     */
    @Test
    void testNonExistentProduct() {
        // TODO: Try selling non-existent product
        // Try adding stock to non-existent product
        // Verify appropriate error handling
        boolean sellResult = manager.sellProduct("NON_EXISTENT", 1, "STUDENT");
        assertFalse(sellResult);
        manager.addStock("NON_EXISTENT", 5);
        Product product = manager.getProductById("NON_EXISTENT");
        assertNull(product);
    }

    /**
     * TODO: Test complete workflow
     */
    @Test
    void testCompleteWorkflow() {
        // TODO: Test adding, selling, restocking sequence
        // Verify state changes correctly throughout
        // Test statistics are updated properly
        manager.addProduct("B001", "Test Book", "BOOK", 20.0, 10);
        manager.sellProduct("B001", 2, "STUDENT");
        manager.addStock("B001", 5);
        Product product = manager.getProductById("B001");
        assertEquals(13, product.getQuantity());
        assertEquals(260.0, manager.getInventoryValue(), 0.001);
    }

    // TODO: Add more tests as needed
    // - Test edge cases (zero quantities, etc.)
    // - Test concurrent modifications
    // - Test with large inventories
}