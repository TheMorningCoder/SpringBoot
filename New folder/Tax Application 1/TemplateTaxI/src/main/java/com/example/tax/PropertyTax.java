package com.example.tax;

public class PropertyTax implements Tax{
	double taxableAmount;
	double taxAmount;
	boolean isTaxPayed;
	
	PropertyTax(){
		isTaxPayed=false;
	}

	@Override
	public void setTaxableAmount(int amount) {
		this.taxableAmount=amount;
	}

	@Override
	public void calculateTaxAmount() {
		taxAmount=taxableAmount*0.05;
	}

	@Override
	public double getTaxAmount() {
		return taxAmount;
	}

	@Override
	public String getTaxType() {
		return "property";
	}

	@Override
	public boolean isTaxPayed() {
		return isTaxPayed;
	}

	@Override
	public void payTax() {
		System.out.println("Hi, your property tax is paid");
		isTaxPayed=true;
	}
}
