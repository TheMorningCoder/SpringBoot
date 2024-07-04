package com.example.tax;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class TaxApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Tax Payment Application");
		while(true) {
			System.out.println("Please select which tax you want to pay: \n1. Income \n2. Property\n3. Exit");
			int userChoice = scanner.nextInt();
			String taxChoice = "";
			switch(userChoice) {
				case 1:
					taxChoice="incomeTax";				
				break;
				case 2:
					taxChoice="propertyTax";
				break;
				case 3:
					System.out.println("Now exiting");
					return;
				
				default:
					System.out.println("Invalid Choice.. Renter");
			}	
			Tax tax=(Tax)context.getBean(taxChoice);
			System.out.println(tax.getTaxType());
		}
		
	}

}
