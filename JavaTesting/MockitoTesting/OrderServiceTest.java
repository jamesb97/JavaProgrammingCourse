package com.example.service;

import com.example.email.Email;
import com.example.email.EmailService;
import com.example.payment.PaymentGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OrderServiceTest {

    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSuccessfulOrderCancellation() {
        // ARRANGE
        String orderId = "ORDER-123";
        String customerEmail = "customer@example.com";
        double amount = 75.0;

        when(paymentGateway.refundPayment(orderId, amount)).thenReturn(true);

        // ACT
        String result = orderService.cancelOrder(orderId, customerEmail, amount);

        // ASSERT
        assertEquals("Order cancelled successfully", result);

        verify(paymentGateway).refundPayment(orderId, amount);
        verify(emailService).sendEmail(customerEmail,
                "Order " + orderId + " has been cancelled. Refund of $" + amount + " processed.");
    }

    @Test
    void testFailedRefund() {
        // ARRANGE
        String orderId = "ORDER-456";
        String customerEmail = "customer@example.com";
        double amount = 50.0;

        when(paymentGateway.refundPayment(orderId, amount)).thenReturn(false);

        // ACT
        String result = orderService.cancelOrder(orderId, customerEmail, amount);

        // ASSERT
        assertEquals("Refund failed", result);

        verify(paymentGateway).refundPayment(orderId, amount);
        verify(emailService, never()).sendEmail(anyString(), anyString());
    }

    @Test
    void testCancelOrderWithInvalidOrderId() {
        // ARRANGE
        String invalidOrderId = "";
        String customerEmail = "customer@example.com";
        double amount = 50.0;

        // ACT
        String result = orderService.cancelOrder(invalidOrderId, customerEmail, amount);

        // ASSERT
        assertEquals("Invalid order ID", result);

        verify(paymentGateway, never()).refundPayment(anyString(), anyDouble());
        verify(emailService, never()).sendEmail(anyString(), anyString());
    }

    @Test
    void placeOrder_ShouldReturnTrue_WhenOrderIsPlaced() {
        // ARRANGE
        String item = "Laptop";
        int quantity = 2;

        // ACT
        boolean result = orderService.placeOrder(item, quantity);

        // ASSERT
        assertTrue(result, "placeOrder() should return true when order is placed");
    }
}