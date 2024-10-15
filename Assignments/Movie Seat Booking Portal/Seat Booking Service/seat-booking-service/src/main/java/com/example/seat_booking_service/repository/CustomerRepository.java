package com.example.seat_booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.seat_booking_service.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}

