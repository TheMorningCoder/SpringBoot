package com.example.seat_booking_service.service;

import com.example.seat_booking_service.model.Booking;
import com.example.seat_booking_service.model.Seat;

import java.util.List;

public interface SeatBookingService {

    List<Seat> getAvailableSeats();

    Booking bookSeats(Booking booking);
}
