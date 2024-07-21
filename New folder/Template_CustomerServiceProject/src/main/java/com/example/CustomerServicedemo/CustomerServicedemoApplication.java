package com.example.CustomerServicedemo;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.Customers.CustomerCare;

@SpringBootApplication
public class CustomerServicedemoApplication {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		System.out.println("Welcome to Customer Care");
		System.out.println("Enter your name");
		String name=sc.nextLine();
		while (true) {
			int userChoice;
			String departmentChoice = "";
			System.out.println(
					"Choose one department: \n 1. Payment Department \n 2. Query Department \n 3. Sales Department \n 4. Exit");
			userChoice = sc.nextInt();
			sc.nextLine();
			switch(userChoice) {
			case 1:
				departmentChoice="paymentDepartment";
				break;
			case 2:
				departmentChoice="queryDepartment";
				break;
			case 3:
				departmentChoice="salesDepartment";
				break;
			case 4:
				System.out.println("Exiting...");
				return;
			default:
				System.out.println("Invalid Choice...");
				return;
			}
			CustomerCare customer=(CustomerCare) context.getBean(departmentChoice);
			customer.setCustomerName(name);
			customer.getService();
			System.out.println("Enter your problem");
			String problem=sc.nextLine();
			customer.setProblem(problem);
			customer.getProblem();
		}

		/*
		 * You need to complete this application as mentioned in the problem statement
		 * build your own logic and perform the following tasks.
		 * 
		 * Tasks: 1. Load the beans from ApplicationContext.xml 2. Display all the
		 * departments available and get the input from user. 3. Get the message from
		 * user and store it into the respective department.
		 * 
		 */
	}
}
