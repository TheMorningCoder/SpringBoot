package com.example.tax;

public class IncomeTax implements Tax {
	double taxableAmount;
	double taxAmount=0.0;
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
       double taxRate;
       switch((int)(taxableAmount/300000)) {
       case 0:
    	   taxRate=0;
    	   break;
       case 1:
    	   taxRate=0.05;
    	   taxAmount=taxableAmount*taxRate;
    	   break;
       case 2:
    	   taxRate=0.10;
    	   taxAmount=taxableAmount*taxRate;
    	   break;
       case 3:
    	   taxRate=0.15;
    	   taxAmount=taxableAmount*taxRate;
    	   break;
       case 4:
    	   taxRate=0.20;
    	   taxAmount=taxableAmount*taxRate;
    	   break;
       default:
    	   taxRate=0.30;
    	   taxAmount=taxableAmount*taxRate;
    	   break;
       }
	}

	@Override
	public double getTaxAmount() {
		return taxAmount;
	}

	@Override
	public String getTaxType() {
		return "income";
	}

	@Override
	public boolean isTaxPayed() {
		return this.isTaxPayed;
	}

	@Override
	public void payTax() {
		this.isTaxPayed=true;
		System.out.println("Hi, your income tax is now paid");
	}

}
