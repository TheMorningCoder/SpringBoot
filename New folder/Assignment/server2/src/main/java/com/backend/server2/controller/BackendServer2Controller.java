package com.backend.server2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.backend.server2.model.Transaction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BackendServer2Controller {

    @GetMapping("/failure/{accountNumber}")
    public Map<String, Object> getFailureTransactions(@PathVariable String accountNumber) {
        Map<String, Object> response = new HashMap<>();
        response.put("AccountNumber", accountNumber);
        response.put("failure", Arrays.asList(
                new Transaction("345577721", "fail", "500", "30-04-2023"),
                new Transaction("245900023", "fail", "50", "29-05-2023")
        ));
        return response;
    }
}

