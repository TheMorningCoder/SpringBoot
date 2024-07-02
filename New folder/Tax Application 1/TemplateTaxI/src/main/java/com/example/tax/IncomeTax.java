package com.example.tax;

public class IncomeTax implements Tax {
	double taxableAmount;
	double taxAmount;
	boolean isTaxPayed;
	
	IncomeTax(){
		isTaxPayed=false;
	}
	
	@Override
	public void setTaxableAmount(int amount) {
		this.taxableAmount = amount;
	}

	@Override
	public void calculateTaxAmount() {
		double threeLacs = 300000;
		double sixLacs = 600000;
		double nineLacs = 900000;
		double tweleveLacs = 1200000;
		double fifteenLacs = 1200000;

		if (taxableAmount <= threeLacs) {
			taxAmount = 0;
		} 
		else if (taxableAmount <= sixLacs) {
			taxAmount = (taxableAmount - threeLacs) * 0.05;
		} 
		else if (taxableAmount <= nineLacs) {
			taxAmount = (threeLacs * 0.05) + (taxableAmount - sixLacs) * 0.10;
		} 
		else if (taxableAmount <= tweleveLacs) {
			taxAmount = (threeLacs * 0.05) + (threeLacs * 0.10) + (taxableAmount - nineLacs) * 0.15;
		} 
		else if (taxableAmount <= fifteenLacs) {
			taxAmount = (threeLacs * 0.05) + (threeLacs * 0.10) + (threeLacs * 0.15) + (taxableAmount - tweleveLacs) * 0.20;
		} 
		else if (taxableAmount > fifteenLacs) {
			taxAmount=(threeLacs * 0.05) + (threeLacs * 0.10) + (threeLacs * 0.15)+(threeLacs*0.20)+(taxableAmount-fifteenLacs)*0.30;
		}

	}

	@Override
	public double getTaxAmount() {
		calculateTaxAmount();
		return taxAmount;
	}

	@Override
	public String getTaxType() {
		// TODO Auto-generated method stub
		return "income";
	}

	@Override
	public boolean isTaxPayed() {
		return this.isTaxPayed;
	}

	@Override
	public void payTax() {
		System.out.println("Hi, your income tax is paid");
		this.isTaxPayed=true;
	}

}
