package com.backend.server3.model;

public class Transaction {
    private String transactionId;
    private String status;
    private String amount;
    private String date;

    public Transaction(String transactionId,String status,String amount,String date){
    	this.transactionId=transactionId;
    	this.status=status;
    	this.amount=amount;
    	this.date=date;
    }
    // Constructors, getters, and setters
}
