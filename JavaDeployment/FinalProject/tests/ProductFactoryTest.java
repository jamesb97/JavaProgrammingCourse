package inventory;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ProductFactory class.
 * TODO: Write comprehensive tests for Factory pattern implementation.
 */
class ProductFactoryTest {

    /**
     * TODO: Test creating a valid book product
     */
    @Test
    void testCreateValidBook() {
        // TODO: Create a book with valid parameters
        // Assert that product is created successfully
        // Verify all properties are set correctly
        Product book = ProductFactory.createProduct("B001", "Effective Java", ProductFactory.BOOK_TYPE, 45.00, 10);
        assertNotNull(book);
        assertEquals("B001", book.getId());
        assertEquals("Effective Java", book.getName());
        assertEquals(ProductFactory.BOOK_TYPE, book.getType());
        assertEquals(45.00, book.getPrice());
        assertEquals(10, book.getQuantity());
    }

    /**
     * TODO: Test creating a valid electronics product
     */
    @Test
    void testCreateValidElectronics() {
        // TODO: Create electronics with valid parameters
        // Assert that product is created successfully
        // Verify all properties are set correctly
        Product electronics = ProductFactory.createProduct("E001", "Smartphone", ProductFactory.ELECTRONICS_TYPE,
                299.99, 5);
        assertNotNull(electronics);
        assertEquals("E001", electronics.getId());
        assertEquals("Smartphone", electronics.getName());
        assertEquals(ProductFactory.ELECTRONICS_TYPE, electronics.getType());
        assertEquals(299.99, electronics.getPrice());
        assertEquals(5, electronics.getQuantity());
    }

    /**
     * TODO: Test book minimum price validation
     */
    @Test
    void testBookMinimumPriceValidation() {
        // TODO: Try creating book with price below $5
        // Assert that IllegalArgumentException is thrown
        // Verify error message is appropriate
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ProductFactory.createProduct("B002", "Cheap Book", ProductFactory.BOOK_TYPE, 4.99, 10);
        });
        assertEquals("Books must have a minimum price of $5.00", exception.getMessage());
    }

    /**
     * TODO: Test electronics minimum price validation
     */
    @Test
    void testElectronicsMinimumPriceValidation() {
        // TODO: Try creating electronics with price below $10
        // Assert that IllegalArgumentException is thrown
        // Verify error message is appropriate
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ProductFactory.createProduct("E002", "Cheap Gadget", ProductFactory.ELECTRONICS_TYPE, 9.99, 5);
        });
        assertEquals("Electronics must have a minimum price of $10.00", exception.getMessage());
    }

    /**
     * TODO: Test invalid product type
     */
    @Test
    void testInvalidProductType() {
        // TODO: Try creating product with invalid type
        // Assert that IllegalArgumentException is thrown
        // Verify error message is appropriate
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ProductFactory.createProduct("X001", "Unknown Product", "FOOD", 15.00, 10);
        });
        assertEquals("Invalid product type: FOOD", exception.getMessage());
    }

    /**
     * TODO: Test boundary conditions
     */
    @Test
    void testBoundaryConditions() {
        // TODO: Test book at exactly $5.00
        // TODO: Test electronics at exactly $10.00
        // Both should succeed
        Product book = ProductFactory.createProduct("B003", "Boundary Book", ProductFactory.BOOK_TYPE, 5.00, 10);
        assertNotNull(book);
        assertEquals(5.00, book.getPrice());

        Product electronics = ProductFactory.createProduct("E003", "Boundary Gadget", ProductFactory.ELECTRONICS_TYPE,
                10.00, 5);
        assertNotNull(electronics);
        assertEquals(10.00, electronics.getPrice());
    }

    // TODO: Add more tests as needed
    // - Test with null parameters
    // - Test with empty strings
    // - Test with negative quantities
    // null parameters and empty strings may be handled by the Product constructor,
    // so additional tests may be needed to ensure proper validation is in place.
}