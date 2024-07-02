package com.example.tax;

public class Rough {

	
	public void calculateTaxAmount(double taxableAmount) {
	    double threeLacs = 300000;
	    double sixLacs = 600000;
	    double nineLacs = 900000;
	    double twelveLacs = 1200000;
	    double fifteenLacs = 1500000;

	    double taxAmount = 0;

	    if (taxableAmount <= threeLacs) {
	        taxAmount = 0;
	    } 
	    else if (taxableAmount <= sixLacs) {
	        taxAmount = (taxableAmount - threeLacs) * 0.05;
	    } 
	    else if (taxableAmount <= nineLacs) {
	        taxAmount = (threeLacs * 0.05) + (taxableAmount - sixLacs) * 0.10;
	    } 
	    else if (taxableAmount <= twelveLacs) {
	        taxAmount = (threeLacs * 0.05) + (threeLacs * 0.10) + (taxableAmount - nineLacs) * 0.15;
	    } 
	    else if (taxableAmount <= fifteenLacs) {
	        taxAmount = (threeLacs * 0.05) + (threeLacs * 0.10) + (threeLacs * 0.15) + (taxableAmount - twelveLacs) * 0.20;
	    } 
	    else {
	        taxAmount = (threeLacs * 0.05) + (threeLacs * 0.10) + (threeLacs * 0.15) + (threeLacs * 0.20) + (taxableAmount - fifteenLacs) * 0.30;
	    }

	    System.out.printf("The calculated tax amount is: ₹%.2f%n", taxAmount);
	}

}
