package com.main.application.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "backendServer1", url = "http://localhost:8086")
public interface BackendServer1Client {
    @GetMapping("/success/{accountNumber}")
    Map<String, Object> getSuccessTransactions(@PathVariable String accountNumber);
}

