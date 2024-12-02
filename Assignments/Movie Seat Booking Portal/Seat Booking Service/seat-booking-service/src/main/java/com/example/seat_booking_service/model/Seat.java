package com.example.seat_booking_service.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Seat {
    @Id
    private String seatId;   // e.g., "A1", "A2", etc.
    private boolean isBooked = false; // Whether the seat is booked or not
}

