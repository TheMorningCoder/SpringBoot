package com.example.seat_booking_service.controller;

import com.example.seat_booking_service.model.Booking;
import com.example.seat_booking_service.model.Seat;
import com.example.seat_booking_service.service.SeatBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seat-booking")
public class SeatBookingController {

    @Autowired
    private SeatBookingService seatBookingService;

    @GetMapping("/seats")
    public ResponseEntity<List<Seat>> getAvailableSeats() {
        List<Seat> seats = seatBookingService.getAvailableSeats();
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<Booking> bookSeats(@RequestBody Booking booking) {
        Booking booked = seatBookingService.bookSeats(booking);
        return new ResponseEntity<>(booked, HttpStatus.OK);
    }
}
