package com.example.seat_booking_service.exception;

public class SeatLimitExceededException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SeatLimitExceededException(String message) {
        super(message);
    }
}
