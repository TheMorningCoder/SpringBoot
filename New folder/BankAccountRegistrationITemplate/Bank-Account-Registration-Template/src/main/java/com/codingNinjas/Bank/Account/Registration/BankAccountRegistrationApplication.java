package com.codingNinjas.Bank.Account.Registration;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;



@SpringBootApplication
public class BankAccountRegistrationApplication {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context= new ClassPathXmlApplicationContext("ApplicationContext.xml");
		Scanner sc=new Scanner(System.in);
		System.out.println("Welcome to Account Registration Application");
		System.out.println("Please enter your name");
		String name=sc.nextLine();
		User user=(User)context.getBean("myUser");
		user.setUserDetails(name);
		List<Account> accountList=user.getAllAccounts();
		while (true) {
			System.out.println("Do you want to add account\n1. Yes\n2. No");
			int input = sc.nextInt();
			if (input == 1) {
				System.out.println("Please select the account type \n1. Current \n2. Savings");
				sc.nextLine();
				int choice = sc.nextInt();
				System.out.println("Enter the opening balance");
				sc.nextLine();
				double balance=sc.nextDouble();
				
				Account account;
				switch(choice) {
					case 1:
						account= (Account) context.getBean("currentAccount");
						account.addBalance(balance);
						accountList.add(account);
						break;
					case 2:
						 account= (Account) context.getBean("savingsAccount");
						 account.addBalance(balance);
						 accountList.add(account);
						break;
					default:
						System.out.println("Wrong choice for bean");
						break;
				}
			} else if(input == 2) {
				break;
			} else {
				System.out.println("Invalid Choice");
				return;
			}
		}
		System.out.println("Hi "+user.getName()+" here is the list of your accounts");
		for(Account account:user.getAllAccounts()) {
			int userReferenceLength = account.toString().length(); //46
			System.out.println(account.getAccountType()+" : opening balance - "+ account.getBalance()+"\t Reference id: " + account.toString().substring(userReferenceLength - 9, userReferenceLength));
		}
	}

}
