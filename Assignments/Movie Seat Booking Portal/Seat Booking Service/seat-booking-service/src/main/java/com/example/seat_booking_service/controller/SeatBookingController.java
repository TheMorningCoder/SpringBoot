package com.example.seat_booking_service.controller;

import com.example.seat_booking_service.exception.CustomerNotFoundException;
import com.example.seat_booking_service.exception.SeatAlreadyBookedException;
import com.example.seat_booking_service.exception.SeatLimitExceededException;
import com.example.seat_booking_service.model.Booking;
import com.example.seat_booking_service.model.Seat;
import com.example.seat_booking_service.service.SeatBookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/seats")
@RequiredArgsConstructor
public class SeatBookingController {

    private final SeatBookingService seatBookingService;

    // GET: Fetch available seats
    @GetMapping("/available")
    public ResponseEntity<List<Seat>> getAvailableSeats() {
        List<Seat> availableSeats = seatBookingService.getAvailableSeats();
        return ResponseEntity.ok(availableSeats);
    }

    // POST: Book seats
    @PostMapping("/book")
    public ResponseEntity<?> bookSeats(@RequestParam Long customerId, @RequestBody List<String> seatIds) {
        try {
            Booking booking = seatBookingService.bookSeats(customerId, seatIds);
            return ResponseEntity.ok(booking);
        } catch (SeatAlreadyBookedException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (SeatLimitExceededException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (CustomerNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while booking seats.");
        }
    }
}
