package com.example.payment_service.service;

import com.example.payment_service.model.Payment;

public interface PaymentService {

    Payment processPayment(Payment payment);
}
