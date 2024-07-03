package com.backend.server1.model;

public class Transaction {
    private String transactionId;
    private String status;
    private String amount;
    private String date;

    // Default constructor
    public Transaction() {}

    // Parameterized constructor
    public Transaction(String transactionId, String status, String amount, String date) {
        this.transactionId = transactionId;
        this.status = status;
        this.amount = amount;
        this.date = date;
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
