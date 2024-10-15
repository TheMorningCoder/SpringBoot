package com.example.seat_booking_service.controller;

import com.example.seat_booking_service.exception.CustomerNotFoundException;
import com.example.seat_booking_service.exception.SeatAlreadyBookedException;
import com.example.seat_booking_service.exception.SeatLimitExceededException;
import com.example.seat_booking_service.model.Booking;
import com.example.seat_booking_service.model.Seat;
import com.example.seat_booking_service.service.SeatBookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SeatBookingControllerTest {

    @Mock
    private SeatBookingService seatBookingService;

    @InjectMocks
    private SeatBookingController seatBookingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings("deprecation")
	@Test
    public void testBookSeats_Success() throws Exception {
        Long customerId = 1L;
        List<String> seatIds = Arrays.asList("A1", "A2");

        // Mock a successful booking
        Booking mockBooking = new Booking();
        mockBooking.setBookingId(1L);
        when(seatBookingService.bookSeats(customerId, seatIds)).thenReturn(mockBooking);

        // Call the controller method
        ResponseEntity<?> response = seatBookingController.bookSeats(customerId, seatIds);

        // Assert response is 200 OK and body contains the booking
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockBooking, response.getBody());

        // Verify the service method was called
        verify(seatBookingService, times(1)).bookSeats(customerId, seatIds);
    }

    @SuppressWarnings("deprecation")
	@Test
    public void testBookSeats_SeatAlreadyBooked() throws Exception {
        Long customerId = 1L;
        List<String> seatIds = Arrays.asList("A1", "A2");

        // Mock a SeatAlreadyBookedException
        when(seatBookingService.bookSeats(customerId, seatIds))
                .thenThrow(new SeatAlreadyBookedException("Seat A1 is already booked"));

        // Call the controller method
        ResponseEntity<?> response = seatBookingController.bookSeats(customerId, seatIds);

        // Assert response is 400 Bad Request and body contains the exception message
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Seat A1 is already booked", response.getBody());

        // Verify the service method was called
        verify(seatBookingService, times(1)).bookSeats(customerId, seatIds);
    }

    @SuppressWarnings("deprecation")
	@Test
    public void testBookSeats_SeatLimitExceeded() throws Exception {
        Long customerId = 1L;
        List<String> seatIds = Arrays.asList("A1", "A2");

        // Mock a SeatLimitExceededException
        when(seatBookingService.bookSeats(customerId, seatIds))
                .thenThrow(new SeatLimitExceededException("Booking failed! A user can book a maximum of 8 seats."));

        // Call the controller method
        ResponseEntity<?> response = seatBookingController.bookSeats(customerId, seatIds);

        // Assert response is 400 Bad Request and body contains the exception message
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Booking failed! A user can book a maximum of 8 seats.", response.getBody());

        // Verify the service method was called
        verify(seatBookingService, times(1)).bookSeats(customerId, seatIds);
    }

    @SuppressWarnings("deprecation")
	@Test
    public void testBookSeats_CustomerNotFound() throws Exception {
        Long customerId = 1L;
        List<String> seatIds = Arrays.asList("A1", "A2");

        // Mock a CustomerNotFoundException
        when(seatBookingService.bookSeats(customerId, seatIds))
                .thenThrow(new CustomerNotFoundException("No customer found with customer id: " + customerId));

        // Call the controller method
        ResponseEntity<?> response = seatBookingController.bookSeats(customerId, seatIds);

        // Assert response is 400 Bad Request and body contains the exception message
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("No customer found with customer id: " + customerId, response.getBody());

        // Verify the service method was called
        verify(seatBookingService, times(1)).bookSeats(customerId, seatIds);
    }

    @SuppressWarnings("deprecation")
	@Test
    public void testBookSeats_InternalServerError() throws Exception {
        Long customerId = 1L;
        List<String> seatIds = Arrays.asList("A1", "A2");

        // Mock a generic exception
        when(seatBookingService.bookSeats(customerId, seatIds))
                .thenThrow(new Exception("Unexpected error occurred"));

        // Call the controller method
        ResponseEntity<?> response = seatBookingController.bookSeats(customerId, seatIds);

        // Assert response is 500 Internal Server Error and body contains the generic error message
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("An error occurred while booking seats.", response.getBody());

        // Verify the service method was called
        verify(seatBookingService, times(1)).bookSeats(customerId, seatIds);
    }
}
