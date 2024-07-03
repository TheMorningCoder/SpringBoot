package com.backend.server3.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.backend.server3.model.Transaction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BackendServer3Controller {

    @GetMapping("/pending/{accountNumber}")
    public Map<String, Object> getPendingTransactions(@PathVariable String accountNumber) {
        Map<String, Object> response = new HashMap<>();
        response.put("AccountNumber", accountNumber);
        response.put("pending", Arrays.asList(
                new Transaction("88797721", "pending", "500", "30-04-2023")
        ));
        return response;
    }
}

