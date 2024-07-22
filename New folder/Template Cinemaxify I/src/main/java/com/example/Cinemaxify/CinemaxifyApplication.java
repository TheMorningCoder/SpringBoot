package com.example.Cinemaxify;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.Scanner;
// 1. Beginner Code Template is already provided to you.
// 2. Just follow the below task to complete the execution of main application.
@SpringBootApplication
public class CinemaxifyApplication {

	public static void main(String[] args) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Cinemaxify Application");
		System.out.println("Please select the member you want the plan for:");
		System.out.println("1. Self\n2. Spouse");
		String userType = "";
		int userChoice = scanner.nextInt();
		scanner.nextLine();
		switch (userChoice) {
		  case 1 -> {
			  userType="self";
		  }
		  case 2 -> {
			  userType="spouse";
		  }
		  case 3 -> {
			  System.out.println("Exiting...");
			  return;
				    }
		  default -> {
			  System.out.println("Invalid choice.");
			  return;
				     }
		}
		// e. Pick the user bean using context.getBean().
			User user = (User) context.getBean(userType);
			
		// f. Take input for User details i.e. name , age, address etc.
			System.out.println("Please enter your name");
			String name=scanner.nextLine();
			System.out.println("Please enter your age");
			int age = scanner.nextInt();
			scanner.nextLine();
			System.out.println("Please enter your contact");
			long contact = scanner.nextLong();
			scanner.nextLine();
			System.out.println("Please enter your address");
			String address=scanner.nextLine();

		// g. Set the above fetched details into the user by using appropriate method.
			user.setUserDetails(name, age, contact, address);
		// h. finally print the details by using appropriate method.
			user.getUserDetails();
			}
	}