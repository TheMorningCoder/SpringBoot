package com.example.tax;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class TaxApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Scanner sc = new Scanner(System.in);
		int userChoice=0;
		String taxChoice="";
		int amount;
		char ch;
		System.out.println("\nWelcome to the Tax Payment Application");
		while(true) {
			System.out.println("\nPlease select which tax you want to do?\n1.Calculate Income Tax\n2.Calculate Property Tax\n3.Exit");
			userChoice=sc.nextInt();
			switch(userChoice) {
				case 1:
					taxChoice="incomeTax";				
				break;
				case 2:
					taxChoice="propertyTax";
				break;
				case 3:
					System.out.println("Now exiting");
					System.exit(0);
				break;
				
				default:
					System.out.println("Invalid Choice.. Renter");
			}	
			Tax tax=(Tax)context.getBean(taxChoice);
			System.out.println(tax.getTaxType());
			if(tax.isTaxPayed()) {
				System.out.println("Tax is already paid!!!");
			}else {
				System.out.println("Enter taxable amount.");
				amount = sc.nextInt();
				tax.setTaxableAmount(amount);
				tax.calculateTaxAmount();
				System.out.println("Your tax amount is:"+ tax.getTaxAmount());
				System.out.println("Do you want to pay Tax?(Y/N)");
				ch=sc.next().charAt(0);
				if(ch=='y'||ch=='Y') 
				tax.payTax();
			}
		}
		
	}

}
