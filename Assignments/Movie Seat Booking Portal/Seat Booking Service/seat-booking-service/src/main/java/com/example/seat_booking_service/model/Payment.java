package com.example.seat_booking_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private Long bookingId;
    private String paymentMode;
    private Double paymentAmount;
    private String paymentStatus;
}
