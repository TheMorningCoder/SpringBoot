package com.example.seat_booking_service.service;

import com.example.seat_booking_service.model.Booking;
import com.example.seat_booking_service.model.Customer;
import com.example.seat_booking_service.model.Seat;
import com.example.seat_booking_service.repository.BookingRepository;
import com.example.seat_booking_service.repository.CustomerRepository;
import com.example.seat_booking_service.repository.SeatRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SeatBookingService {

    private final SeatRepository seatRepository;
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;

    // Fetch available seats
    public List<Seat> getAvailableSeats() {
        return seatRepository.findByIsBookedFalse();
    }

    // Book seats for a customer
    @Transactional
    public Booking bookSeats(Long customerId, List<String> seatIds) throws Exception {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new Exception("Customer not found");
        }

        // Fetch the requested seats
        List<Seat> seats = seatRepository.findAllById(seatIds);

        // Ensure no seat is already booked
        for (Seat seat : seats) {
            if (seat.isBooked()) {
                throw new Exception("Seat " + seat.getSeatId() + " is already booked");
            }
        }

        // Mark seats as booked
        seats.forEach(seat -> seat.setBooked(true));
        seatRepository.saveAll(seats);

        // Create booking
        Booking booking = new Booking();
        booking.setCustomer(customer.get());
        booking.setSeats(seats);
        booking.setBookingTime(LocalDateTime.now());
        booking.setPaymentConfirmed(false); // Payment confirmation is pending
        return bookingRepository.save(booking);
    }
}

