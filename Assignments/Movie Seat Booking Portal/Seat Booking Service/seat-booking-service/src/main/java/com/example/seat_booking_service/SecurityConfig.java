package com.example.seat_booking_service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .anyRequest().permitAll()  // Allow all requests without authentication
            )
            .csrf().disable();  // Disable CSRF if needed for non-browser clients

        return http.build();
    }

//    @SuppressWarnings("removal")
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests(authz -> authz
//                .requestMatchers("/seats/available").permitAll()  // No authentication required for this endpoint
//                .requestMatchers("/seats/book").permitAll()       // Allow public access to the POST endpoint
//                .requestMatchers("/seats/").permitAll()
//                .anyRequest().authenticated()  // All other endpoints require authentication
//            )
//            .csrf().disable();  // Disable CSRF if needed for non-browser clients
//
//        return http.build();
//    }
}
