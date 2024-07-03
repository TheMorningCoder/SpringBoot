package com.backend.server1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.backend.server1.model.Transaction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BackendServer1Controller {

    @GetMapping("/success/{accountNumber}")
    public Map<String, Object> getSuccessTransactions(@PathVariable String accountNumber) {
        Map<String, Object> response = new HashMap<>();
        response.put("AccountNumber", accountNumber);
        response.put("success", Arrays.asList(
                new Transaction("123456789", "success", "500", "30-05-2023"),
                new Transaction("789566233", "success", "100", "31-05-2023"),
                new Transaction("556666678", "success", "700", "20-05-2023")
        ));
        return response;
    }
}

