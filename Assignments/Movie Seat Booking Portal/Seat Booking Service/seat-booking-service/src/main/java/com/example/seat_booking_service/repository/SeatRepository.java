package com.example.seat_booking_service.repository;

import com.example.seat_booking_service.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, String> {
    List<Seat> findByIsBookedFalse(); // To get only available (unbooked) seats
}

