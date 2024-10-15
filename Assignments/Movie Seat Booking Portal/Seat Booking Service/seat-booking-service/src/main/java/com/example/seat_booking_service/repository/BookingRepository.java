package com.example.seat_booking_service.repository;

import com.example.seat_booking_service.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	
	

    List<Booking> findByCustomer_CustomerId(Long customerId);
}
