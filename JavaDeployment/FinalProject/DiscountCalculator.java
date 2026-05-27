package inventory;

/**
 * Strategy pattern implementation for calculating different types of discounts.
 * TODO: Implement the Strategy pattern with multiple discount algorithms.
 *
 * Requirements:
 * - Student discount: 10% off books only
 * - Bulk discount: 15% off when buying 5+ items
 * - No discount option
 * - Return discount amount and description
 */
public class DiscountCalculator {

    /**
     * Inner class to hold discount calculation results.
     * TODO: Implement this class with discount amount and description.
     */
    public static class DiscountResult {
        // TODO: Implement DiscountResult class
        private double discountAmount;
        private String description;

        // TODO: Add constructor
        public DiscountResult(double discountAmount, String description) {
            this.discountAmount = discountAmount;
            this.description = description;
        }

        // TODO: Add getter methods
        public double getDiscountAmount() {
            return discountAmount;
        }

        public String getDescription() {
            return description;
        }
    }

    /**
     * TODO: Implement strategy method for calculating discounts
     * 
     * @param product      - the product being purchased
     * @param quantity     - quantity being purchased
     * @param discountType - type of discount to apply (STUDENT, BULK, NONE)
     * @return DiscountResult with amount and description
     */
    public static DiscountResult calculateDiscount(Product product, int quantity, String discountType) {
        // TODO: Implement discount calculation logic

        // Initialize variables for discount amount and description
        double discountAmount = 0.0;
        String description = "No discount applied";

        // TODO: Implement switch statement or if-else logic for discount types
        // Case "STUDENT":
        // - Check if product type is "BOOK"
        // - Apply 10% discount if applicable
        // - Set appropriate description
        if ("STUDENT".equalsIgnoreCase(discountType)) {
            if (ProductFactory.BOOK_TYPE.equals(product.getType())) {
                discountAmount = product.getPrice() * quantity * 0.10; // 10% discount
                description = "Student discount applied: 10% off books";
            } else {
                description = "Student discount not applicable for this product type";
            }
        } else if ("BULK".equalsIgnoreCase(discountType)) {
            if (quantity >= 5) {
                discountAmount = product.getPrice() * quantity * 0.15; // 15% discount
                description = "Bulk discount applied: 15% off for buying 5 or more items";
            } else {
                description = "Bulk discount not applicable for quantity less than 5";
            }
        } else {
            description = "No discount applied";
        }

        // Case "BULK":
        // - Check if quantity >= 5
        // - Apply 15% discount if applicable
        // - Set appropriate description
        if ("BULK".equalsIgnoreCase(discountType)) {
            if (quantity >= 5) {
                discountAmount = product.getPrice() * quantity * 0.15; // 15% discount
                description = "Bulk discount applied: 15% off for buying 5 or more items";
            } else {
                description = "Bulk discount not applicable for quantity less than 5";
            }
        }

        // Case "NONE" or default:
        // - No discount applied
        if ("NONE".equalsIgnoreCase(discountType)) {
            description = "No discount applied";
        }

        // TODO: Return new DiscountResult with calculated values
        return new DiscountResult(discountAmount, description);
    }

    // TODO: Optional - Add helper methods
    private static boolean isEligibleForStudentDiscount(Product product) {
        return ProductFactory.BOOK_TYPE.equals(product.getType());
    }

    private static boolean isEligibleForBulkDiscount(int quantity) {
        return quantity >= 5;
    }
}