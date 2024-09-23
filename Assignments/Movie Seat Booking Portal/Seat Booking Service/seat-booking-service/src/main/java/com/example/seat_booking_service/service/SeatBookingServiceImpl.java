package com.example.seat_booking_service.service;

import com.example.seat_booking_service.model.Booking;
import com.example.seat_booking_service.model.Payment;
import com.example.seat_booking_service.model.Seat;
import com.example.seat_booking_service.repository.BookingRepository;
import com.example.seat_booking_service.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeatBookingServiceImpl implements SeatBookingService {

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public List<Seat> getAvailableSeats() {
        return seatRepository.findByAvailableTrue();
    }

    @Override
    @Transactional
    public Booking bookSeats(Booking booking) {
        // Mark seats as booked

    	List<Long> seatIds = booking.getSeatNumbers()
                .stream()
                .map(Long::valueOf)
                .collect(Collectors.toList());

		List<Seat> seats = seatRepository.findAllById(seatIds);
		seats.forEach(seat -> seat.setAvailable(false));
		seatRepository.saveAll(seats);


        // Save the booking
        booking.setBookingTime(LocalDateTime.now());
        Booking savedBooking = bookingRepository.save(booking);

        // Prepare and send payment request
        Payment payment = new Payment(savedBooking.getId(), "CASH", 100.0 * seats.size(), "PENDING");
        Payment paymentResponse = webClientBuilder.build()
                .post()
                .uri("http://localhost:8081/api/v1/payment/process")
                .bodyValue(payment)
                .retrieve()
                .bodyToMono(Payment.class)
                .block();

        if (paymentResponse != null && "SUCCESS".equals(paymentResponse.getPaymentStatus())) {
            return savedBooking;
        } else {
            // Rollback seat booking if payment fails
            seats.forEach(seat -> seat.setAvailable(true));
            seatRepository.saveAll(seats);
            throw new RuntimeException("Payment failed, booking not completed.");
        }
    }
}

