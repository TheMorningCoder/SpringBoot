package com.main.application.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "backendServer3", url = "http://localhost:8083")
public interface BackendServer3Client {
    @GetMapping("/pending/{accountNumber}")
    Map<String, Object> getPendingTransactions(@PathVariable String accountNumber);
}
