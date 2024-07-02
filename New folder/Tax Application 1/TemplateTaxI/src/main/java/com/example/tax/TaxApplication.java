package com.example.tax;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class TaxApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Tax incomeTax = (Tax) context.getBean("incomeTax");
		Tax propertyTax = (Tax) context.getBean("propertyTax");
		
		Scanner sc = new Scanner(System.in);
		String type;
		int amount;
		
		System.out.println("Which tax do you want to calculate?");
		type = sc.nextLine().trim().toLowerCase();
		
		if (type.equals("income")) {
			System.out.println("Enter the Taxable Amount");
			amount = sc.nextInt();
			incomeTax.setTaxableAmount(amount);
			System.out.println(incomeTax.getTaxAmount());
		} else {
			System.out.println("Enter the Taxable Amount");
			amount = sc.nextInt();
			propertyTax.setTaxableAmount(amount);
			System.out.println(incomeTax.getTaxAmount());
		}
	}

}
