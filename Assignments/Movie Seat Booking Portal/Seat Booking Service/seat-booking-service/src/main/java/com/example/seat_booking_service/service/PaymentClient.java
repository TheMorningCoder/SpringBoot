package com.example.seat_booking_service.service;

import com.example.seat_booking_service.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;



@Component
public class PaymentClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    public Payment processPayment(Payment payment) {
        return webClientBuilder.build()
                .post()
                .uri("http://localhost:8081/api/v1/payment/process")
                .bodyValue(payment)
                .retrieve()
                .bodyToMono(Payment.class)
                .block();
    }
}
