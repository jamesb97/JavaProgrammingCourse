package inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for DiscountCalculator class.
 * TODO: Write comprehensive tests for Strategy pattern implementation.
 */
class DiscountCalculatorTest {

    // TODO: Declare test products
    private Product book;
    private Product electronics;

    /**
     * TODO: Set up test data before each test
     */
    @BeforeEach
    void setUp() {
        // TODO: Create test products using ProductFactory
        book = ProductFactory.createProduct("B001", "Test Book", "BOOK", 20.0, 10);
        electronics = ProductFactory.createProduct("E001", "Test Electronics", "ELECTRONICS", 100.0, 10);
    }

    /**
     * TODO: Test student discount on books
     */
    @Test
    void testStudentDiscountOnBooks() {
        // TODO: Calculate student discount for book
        // Verify 10% discount is applied
        // Check description message
        DiscountCalculator.DiscountResult result = DiscountCalculator.calculateDiscount(book, 1, "STUDENT");
        assertEquals(2.0, result.getDiscountAmount(), 0.001);
        assertEquals("Student discount applied: 10% off", result.getDescription());
    }

    /**
     * TODO: Test student discount on electronics (should not apply)
     */
    @Test
    void testStudentDiscountOnElectronics() {
        // TODO: Calculate student discount for electronics
        // Verify no discount is applied
        // Check description explains why
        DiscountCalculator.DiscountResult result = DiscountCalculator.calculateDiscount(electronics, 1, "STUDENT");
        assertEquals(0.0, result.getDiscountAmount(), 0.001);
        assertEquals("Student discount does not apply to electronics", result.getDescription());
    }

    /**
     * TODO: Test bulk discount when quantity >= 5
     */
    @Test
    void testBulkDiscountValid() {
        // TODO: Calculate bulk discount with quantity = 5 or more
        // Verify 15% discount is applied
        // Test with both books and electronics
        DiscountCalculator.DiscountResult bookResult = DiscountCalculator.calculateDiscount(book, 5, "BULK");
        assertEquals(15.0, bookResult.getDiscountAmount(), 0.001);
        assertEquals("Bulk discount applied: 15% off", bookResult.getDescription());
    }

    /**
     * TODO: Test bulk discount when quantity < 5
     */
    @Test
    void testBulkDiscountInvalid() {
        // TODO: Calculate bulk discount with quantity < 5
        // Verify no discount is applied
        // Check description explains requirement
        DiscountCalculator.DiscountResult result = DiscountCalculator.calculateDiscount(book, 4, "BULK");
        assertEquals(0.0, result.getDiscountAmount(), 0.001);
        assertEquals("Bulk discount not applicable for quantity less than 5", result.getDescription());
    }

    /**
     * TODO: Test no discount option
     */
    @Test
    void testNoDiscount() {
        // TODO: Calculate with "NONE" discount type
        // Verify discount amount is 0
        // Check appropriate description
        DiscountCalculator.DiscountResult result = DiscountCalculator.calculateDiscount(book, 1, "NONE");
        assertEquals(0.0, result.getDiscountAmount(), 0.001);
        assertEquals("No discount applied", result.getDescription());
    }

    /**
     * TODO: Test boundary conditions
     */
    @Test
    void testBoundaryConditions() {
        // TODO: Test bulk discount with exactly 5 items
        // TODO: Test with quantity = 1
        // TODO: Test with large quantities
        DiscountCalculator.DiscountResult result = DiscountCalculator.calculateDiscount(book, 5, "BULK");
        assertEquals(15.0, result.getDiscountAmount(), 0.001);
        assertEquals("Bulk discount applied: 15% off", result.getDescription());
    }

    /**
     * TODO: Test discount calculation accuracy
     */
    @Test
    void testDiscountCalculationAccuracy() {
        // TODO: Verify exact discount amounts
        // Example: $20 book, qty 2, student discount = $4.00
        // Example: $100 electronics, qty 5, bulk discount = $75.00
        DiscountCalculator.DiscountResult studentResult = DiscountCalculator.calculateDiscount(book, 2, "STUDENT");
        assertEquals(4.0, studentResult.getDiscountAmount(), 0.001);
        assertEquals("Student discount applied: 10% off", studentResult.getDescription());
    }

    // TODO: Add more tests as needed
    // - Test case sensitivity of discount types
    // - Test invalid discount types
    // - Test with zero quantities
}