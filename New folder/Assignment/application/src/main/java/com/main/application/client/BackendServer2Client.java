package com.main.application.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "backendServer2", url = "http://localhost:8082")
public interface BackendServer2Client {
    @GetMapping("/failure/{accountNumber}")
    Map<String, Object> getFailureTransactions(@PathVariable String accountNumber);
}

