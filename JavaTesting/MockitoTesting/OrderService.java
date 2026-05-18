package com.example.sevice;

/**
 * Cancels an order and processes refund
 * 
 * @param orderId       the order to cancel
 * @param customerEmail customer's email address
 * @param amount        amount to refund
 * @return status message
 */
public class OrderService {
    private PaymentGateway paymentGateway;
    private EmailService emailService;

    public String cancelOrder(String orderId, String customerEmail, double amount) {
        // Validate order ID
        if (orderId == null || orderId.trim().isEmpty()) {
            return "Invalid order ID";
        }

        // Validate email
        if (customerEmail == null || customerEmail.trim().isEmpty()) {
            return "Invalid email";
        }

        // Validate amount
        if (amount <= 0) {
            return "Invalid amount";
        }

        // Try to process refund
        boolean refundSuccess = paymentGateway.refundPayment(orderId, amount);

        if (refundSuccess) {
            // Refund successful - send cancellation email
            emailService.sendEmail(customerEmail,
                    "Order " + orderId + " has been cancelled. Refund of $" + amount + " processed.");
            return "Order cancelled successfully";
        } else {
            // Refund failed
            return "Refund failed";
        }
    }
}
// public class OrderService {
// public boolean placeOrder(String item, int quantity) {
// Simulate placing an order
// System.out.println("Placing order: " + quantity + " x " + item);
// return true; // Assume order is always successful for this example
// }
// }
