package com.example.payment_service.service;

import com.example.payment_service.model.Payment;
import com.example.payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    @Transactional
    public Payment processPayment(Payment payment) {
        payment.setPaymentTime(LocalDateTime.now());
        payment.setPaymentStatus("SUCCESS"); // For simplicity, assuming payment is always successful
        return paymentRepository.save(payment);
    }
}

