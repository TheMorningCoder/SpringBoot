package com.example.service1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/service1")
public class Service1Controller {

    @Autowired
    private RestTemplate restTemplate;

    // Single async call to service 2
    @GetMapping("/asyncMessage")
    @CircuitBreaker(name = "service2", fallbackMethod = "fallbackResponse")
    public CompletableFuture<String> getMessageAsync() {
        return CompletableFuture.supplyAsync(() -> 
            restTemplate.getForObject("http://localhost:8082/service2/message", String.class)
        );
    }

    // Making multiple async requests using Stream API and CompletableFuture
    @GetMapping("/multipleAsyncMessages")
    public CompletableFuture<List<String>> getMultipleMessagesAsync() {
        // Make 5 async requests to service 2 using Stream API
        List<CompletableFuture<String>> futures = IntStream.range(0, 5)
            .mapToObj(i -> CompletableFuture.supplyAsync(() ->
                restTemplate.getForObject("http://localhost:8082/service2/message", String.class))
            )
            .collect(Collectors.toList());
        
        // Combine all futures and return when all are complete
        return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v -> 
                    futures.stream()
                           .map(CompletableFuture::join)
                           .collect(Collectors.toList())
                );
    }

    // Fallback method for Circuit Breaker
    public String fallbackResponse(Throwable t) {
        return "Service 2 is down. Please try again later.";
    }
}

/*
Explanation of Code:
 
1)getMessageAsync():
This method calls Service 2 asynchronously using CompletableFuture.supplyAsync().
The response is wrapped in a CompletableFuture, making the call non-blocking.

2)getMultipleMessagesAsync():
We create multiple asynchronous calls to Service 2 using a Stream over an IntStream.range(0, 5). This sends five requests in parallel.
The results of these requests are collected into a list of CompletableFuture objects.
CompletableFuture.allOf() is used to wait for all futures to complete. After that, we use Stream to collect the results from each future using .join(), which blocks until the result is available.

3)Fallback Method: This handles failures when Service 2 is down. It's triggered by the circuit breaker.
  
  
*/
