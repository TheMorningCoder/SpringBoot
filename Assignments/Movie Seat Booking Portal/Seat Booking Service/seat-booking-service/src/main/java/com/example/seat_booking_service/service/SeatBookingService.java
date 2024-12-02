package com.example.seat_booking_service.service;

import com.example.seat_booking_service.exception.CustomerNotFoundException;
import com.example.seat_booking_service.exception.SeatAlreadyBookedException;
import com.example.seat_booking_service.exception.SeatLimitExceededException;
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
    
    private static final int MAX_SEATS_PER_USER = 8; // Seat limit

    // Fetch available seats
    public List<Seat> getAvailableSeats() {
        return seatRepository.findByIsBookedFalse();
    }

    // Book seats for a customer
    @Transactional
    public Booking bookSeats(Long customerId, List<String> seatIds) throws Exception {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()) {
            throw new CustomerNotFoundException("No Customer not found with customer id: "+customerId);
        }
        
     // Check if the customer has already reached the seat limit
        int totalBookedSeats = getTotalBookedSeatsByCustomer(customerId);
        if (totalBookedSeats + seatIds.size() > MAX_SEATS_PER_USER) {
            throw new SeatLimitExceededException("Booking failed! A user can book a maximum of " + MAX_SEATS_PER_USER + " seats.");
        }

        // Fetch the requested seats
        List<Seat> seats = seatRepository.findAllById(seatIds);

        // Ensure no seat is already booked
        for (Seat seat : seats) {
            if (seat.isBooked()) {
                throw new SeatAlreadyBookedException("Seat " + seat.getSeatId() + " is already booked");
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
 // Helper method to get the total number of seats booked by a customer
    private int getTotalBookedSeatsByCustomer(Long customerId) {
        List<Booking> bookings = bookingRepository.findByCustomer_CustomerId(customerId);
        return bookings.stream()
                .mapToInt(booking -> booking.getSeats().size())
                .sum();
    }
}

