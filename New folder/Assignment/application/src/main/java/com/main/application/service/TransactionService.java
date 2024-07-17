package com.main.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.application.client.BackendServer1Client;
import com.main.application.client.BackendServer2Client;
import com.main.application.client.BackendServer3Client;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class TransactionService {

    @Autowired
    private BackendServer1Client backendServer1Client;

    @Autowired
    private BackendServer2Client backendServer2Client;

    @Autowired
    private BackendServer3Client backendServer3Client;

    public Map<String, List<Map<String, Object>>> getTransactions(String accountNumber, String status) {
        if ("ALL".equalsIgnoreCase(status)) {
            CompletableFuture<Map<String, Object>> successTransactions = CompletableFuture.supplyAsync(() -> 
            backendServer1Client.getSuccessTransactions(accountNumber));
            CompletableFuture<Map<String, Object>> failureTransactions = CompletableFuture.supplyAsync(() -> 
            backendServer2Client.getFailureTransactions(accountNumber));
            CompletableFuture<Map<String, Object>> pendingTransactions = CompletableFuture.supplyAsync(() -> 
            backendServer3Client.getPendingTransactions(accountNumber));

            CompletableFuture.allOf(successTransactions, failureTransactions, pendingTransactions).join();

            Map<String, List<Map<String, Object>>> consolidatedTransactions = new HashMap<>();
            consolidatedTransactions.put("success", (List<Map<String, Object>>) successTransactions.join().getOrDefault("success", new ArrayList<>()));
            consolidatedTransactions.put("failure", (List<Map<String, Object>>) failureTransactions.join().getOrDefault("failure", new ArrayList<>()));
            consolidatedTransactions.put("pending", (List<Map<String, Object>>) pendingTransactions.join().getOrDefault("pending", new ArrayList<>()));

            return consolidatedTransactions;
        } else {
            // Handle specific status (success, failure, pending)
            Map<String, List<Map<String, Object>>> specificTransactions = new HashMap<>();
            switch (status.toUpperCase()) {
                case "SUCCESS":
                    specificTransactions.put("success", (List<Map<String, Object>>) backendServer1Client.getSuccessTransactions(accountNumber).getOrDefault("success", new ArrayList<>()));
                    break;
                case "FAILURE":
                    specificTransactions.put("failure", (List<Map<String, Object>>) backendServer2Client.getFailureTransactions(accountNumber).getOrDefault("failure", new ArrayList<>()));
                    break;
                case "PENDING":
                    specificTransactions.put("pending", (List<Map<String, Object>>) backendServer3Client.getPendingTransactions(accountNumber).getOrDefault("pending", new ArrayList<>()));
                    break;
            }
            return specificTransactions;
        }
    }
}

