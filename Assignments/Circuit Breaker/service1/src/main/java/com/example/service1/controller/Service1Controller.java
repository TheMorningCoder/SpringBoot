package com.example.service1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service1")
public class Service1Controller {

    private static final Logger log = LoggerFactory.getLogger(Service1Controller.class);

    @Autowired
    private RestTemplate restTemplate;

    // Single async call to service 2
    @GetMapping("/asyncMessage")
//    @CircuitBreaker(name = "service2", fallbackMethod = "fallbackResponse")
    public CompletableFuture<String> getMessageAsync() {
        log.info("Inside getMessageAsync method.");
        return CompletableFuture.supplyAsync(this::callService2);
    }

    // Making multiple async requests using Stream API and CompletableFuture
    @GetMapping("/multipleAsyncMessages")
    @CircuitBreaker(name = "service2", fallbackMethod = "fallbackResponseForMultiple")
    public CompletableFuture<List<String>> getMultipleMessagesAsync() {
        List<CompletableFuture<String>> futures = IntStream.range(0, 5)
            .mapToObj(i -> CompletableFuture.supplyAsync(this::callService2))
            .collect(Collectors.toList());

        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> 
                    futures.stream()
                           .map(CompletableFuture::join)
                           .collect(Collectors.toList())
                );
    }

    // Helper method to call service2
    private String callService2() {
        log.info("Inside callService2 method.");
        try {
            return restTemplate.getForObject("http://localhost:8082/service2/message", String.class);
        } catch (Exception e) {
        	log.error("Service 2 returned a 500 Internal Server Error");
            return "Service 2 is down (500 Internal Server Error). Please try again later.";
        }
    }

    // Generic Fallback method for all exceptions
    public String fallbackResponse(Throwable t) {
        log.error("Fallback triggered. Error: {}", t.getMessage());
        return "Service is currently down. Please try again later.";
    }

    // Fallback method for multiple async calls
    public CompletableFuture<List<String>> fallbackResponseForMultiple(Throwable t) {
        log.error("Fallback triggered for multiple requests. Error: {}", t.getMessage());
        return CompletableFuture.supplyAsync(() -> 
            List.of("Service is currently down. Please try again later.")
        );
    }
}
