package com.main.application.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.main.application.client.BackendServer1Client;
import com.main.application.client.BackendServer2Client;
import com.main.application.client.BackendServer3Client;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private BackendServer1Client backendServer1Client;

    @Mock
    private BackendServer2Client backendServer2Client;

    @Mock
    private BackendServer3Client backendServer3Client;

    @InjectMocks
    private TransactionService transactionService;

    @Test
    public void testGetTransactionsAllStatus() {
        String accountNumber = "12233300011001";

        // Mocking the responses from backend servers
        Map<String, Object> successTransactions = new HashMap<>();
        successTransactions.put("success", Arrays.asList(
                createTransaction("123456789", "success", "500", "30-05-2023"),
                createTransaction("789566233", "success", "100", "31-05-2023"),
                createTransaction("556666678", "success", "700", "20-05-2023")
        ));

        Map<String, Object> failureTransactions = new HashMap<>();
        failureTransactions.put("failure", Arrays.asList(
                createTransaction("345577721", "fail", "500", "30-04-2023"),
                createTransaction("245900023", "fail", "50", "29-05-2023")
        ));

        Map<String, Object> pendingTransactions = new HashMap<>();
        pendingTransactions.put("pending", Arrays.asList(
                createTransaction("88797721", "pending", "500", "30-04-2023")
        ));

        when(backendServer1Client.getSuccessTransactions(accountNumber)).thenReturn(successTransactions);
        when(backendServer2Client.getFailureTransactions(accountNumber)).thenReturn(failureTransactions);
        when(backendServer3Client.getPendingTransactions(accountNumber)).thenReturn(pendingTransactions);

        // Calling the service method
        Map<String, List<Map<String, Object>>> transactions = transactionService.getTransactions(accountNumber, "ALL");

        // Asserting the response
        assertEquals(3, transactions.get("success").size());
        assertEquals(2, transactions.get("failure").size());
        assertEquals(1, transactions.get("pending").size());
    }

    @Test
    public void testGetTransactionsSuccessStatus() {
        String accountNumber = "12233300011001";

        // Mocking the response from backend server 1
        Map<String, Object> successTransactions = new HashMap<>();
        successTransactions.put("success", Arrays.asList(
                createTransaction("123456789", "success", "500", "30-05-2023"),
                createTransaction("789566233", "success", "100", "31-05-2023"),
                createTransaction("556666678", "success", "700", "20-05-2023")
        ));

        when(backendServer1Client.getSuccessTransactions(accountNumber)).thenReturn(successTransactions);

        // Calling the service method
        Map<String, List<Map<String, Object>>> transactions = transactionService.getTransactions(accountNumber, "SUCCESS");

        // Asserting the response
        assertEquals(3, transactions.get("success").size());
        assertEquals(0, transactions.getOrDefault("failure", Collections.emptyList()).size());
        assertEquals(0, transactions.getOrDefault("pending", Collections.emptyList()).size());
    }

    @Test
    public void testGetTransactionsFailureStatus() {
        String accountNumber = "12233300011001";

        // Mocking the response from backend server 2
        Map<String, Object> failureTransactions = new HashMap<>();
        failureTransactions.put("failure", Arrays.asList(
                createTransaction("345577721", "fail", "500", "30-04-2023"),
                createTransaction("245900023", "fail", "50", "29-05-2023")
        ));

        when(backendServer2Client.getFailureTransactions(accountNumber)).thenReturn(failureTransactions);

        // Calling the service method
        Map<String, List<Map<String, Object>>> transactions = transactionService.getTransactions(accountNumber, "FAILURE");

        // Asserting the response
        assertEquals(0, transactions.getOrDefault("success", Collections.emptyList()).size());
        assertEquals(2, transactions.get("failure").size());
        assertEquals(0, transactions.getOrDefault("pending", Collections.emptyList()).size());
    }

    @Test
    public void testGetTransactionsPendingStatus() {
        String accountNumber = "12233300011001";

        // Mocking the response from backend server 3
        Map<String, Object> pendingTransactions = new HashMap<>();
        pendingTransactions.put("pending", Arrays.asList(
                createTransaction("88797721", "pending", "500", "30-04-2023")
        ));

        when(backendServer3Client.getPendingTransactions(accountNumber)).thenReturn(pendingTransactions);

        // Calling the service method
        Map<String, List<Map<String, Object>>> transactions = transactionService.getTransactions(accountNumber, "PENDING");

        // Asserting the response
        assertEquals(0, transactions.getOrDefault("success", Collections.emptyList()).size());
        assertEquals(0, transactions.getOrDefault("failure", Collections.emptyList()).size());
        assertEquals(1, transactions.get("pending").size());
    }

    private Map<String, Object> createTransaction(String transactionId, String status, String amount, String date) {
        Map<String, Object> transaction = new HashMap<>();
        transaction.put("transactionId", transactionId);
        transaction.put("status", status);
        transaction.put("amount", amount);
        transaction.put("date", date);
        return transaction;
    }
}

