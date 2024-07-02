package com.example.tax;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class TaxApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Scanner sc = new Scanner(System.in);
		int choice=0;
		int amount;
		char ch='n';
		
		while(choice!=3) {
			System.out.println("\nWhat do you want to do?\n1.Calculate Income Tax\n2.Calculate Property Tax\n3.Exit");
			choice=sc.nextInt();
			if(choice==1) {
				System.out.println("Calculating Income Tax");
				Tax incomeTax = (Tax) context.getBean("incomeTax");
				if(incomeTax.isTaxPayed()) {
					System.out.println("Income Tax is already paid");
				}else {
					System.out.println("Enter taxable amount.");
					amount = sc.nextInt();
					incomeTax.setTaxableAmount(amount);
					incomeTax.calculateTaxAmount();
					System.out.println("Your tax amount is:"+ incomeTax.getTaxAmount());
					System.out.println("Do you want to pay Income Tax?(Y/N)");
					ch=sc.next().charAt(0);
					if(ch=='y'||ch=='Y') 
						incomeTax.payTax();	
				}
			}
			else if(choice==2) {
				System.out.println("Calculating Property Tax");
				Tax propertyTax = (Tax) context.getBean("propertyTax");
				if(propertyTax.isTaxPayed()) {
					System.out.println("Property Tax is already paid");
				}else {
					System.out.println("Enter taxable amount.");
					amount = sc.nextInt();
					propertyTax.setTaxableAmount(amount);
					propertyTax.calculateTaxAmount();
					System.out.println("Your tax amount is:"+ propertyTax.getTaxAmount());
					System.out.println("Do you want to pay Income Tax?(Y/N)");
					ch=sc.next().charAt(0);
					if(ch=='y'||ch=='Y') 
						propertyTax.payTax();	
				}
			}else if(choice==3){
				System.out.println();
				System.exit(0);
			}
		}
		
	}

}
