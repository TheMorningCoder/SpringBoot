package com.example.payment_service.controller;

import com.example.payment_service.model.Payment;
import com.example.payment_service.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PaymentControllerTest {

    @InjectMocks
    private PaymentController paymentController;

    @Mock
    private PaymentService paymentService;

    private Payment mockPayment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Create a mock Payment object
        mockPayment = new Payment();
        mockPayment.setId(1L);
        mockPayment.setBookingId(12345L);
        mockPayment.setPaymentMode("CASH");
        mockPayment.setPaymentAmount(1000.0);
        mockPayment.setPaymentStatus("SUCCESS");
        mockPayment.setPaymentTime(LocalDateTime.now());
    }

    @Test
    void testProcessPayment() {
        // Mock the PaymentService's processPayment method
        when(paymentService.processPayment(any(Payment.class))).thenReturn(mockPayment);

        // Call the processPayment API in the controller
        ResponseEntity<Payment> responseEntity = paymentController.processPayment(mockPayment);

        // Verify the status code and response body
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockPayment, responseEntity.getBody());
    }
}
