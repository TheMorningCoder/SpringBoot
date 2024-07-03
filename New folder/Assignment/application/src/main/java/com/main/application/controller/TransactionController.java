package com.main.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.application.service.TransactionService;

import java.util.List;
import java.util.Map;

@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/transactions/{accountNumber}")
    public Map<String, List<Map<String, Object>>> getTransactions(@PathVariable String accountNumber, @RequestParam String status) {
        return transactionService.getTransactions(accountNumber, status);
    }
}

