package com.example.seat_booking_service.exception;

public class SeatAlreadyBookedException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SeatAlreadyBookedException(String message) {
        super(message);
    }
}
