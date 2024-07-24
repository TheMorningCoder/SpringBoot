package com.example.Vaccination;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
@SpringBootApplication
public class VaccinationApplication {

    public static void main(String[] args) {
    	ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext.xml");
    	Scanner sc=new Scanner(System.in);
    	System.out.println("Welcome to the Vaccination Application");
    	while(true) {
    		System.out.println("Please choose your vaccination preference");
        	System.out.println("1.Covid\n2.Polio\n3.Typhoid");
        	int userChoice=sc.nextInt();
        	sc.nextLine();
        	String vaccineChoice="";
        	String userType="";
        	switch(userChoice) {
        		case 1->vaccineChoice="Covid";
        		case 2->vaccineChoice="Polio";
        		case 3->vaccineChoice="Typhoid";
        	}
        	System.out.println("Whom do you want to vaccinate");
        	System.out.println("1.Father\n2.Mother\n3.Self\n4.Spouse\n5.Exit");
        	int choice=sc.nextInt();
        	sc.nextLine();
        	switch(choice) {
        		case 1->userType="father";
        		case 2->userType="mother";
        		case 3->userType="self";
        		case 4->userType="spouse";
        		case 5->{
        			System.out.println("Exiting...");
        			return;
        		}
        		default->{
        			System.out.println("Invalid Choice");
        			return;
        		}
        	}
        	User user=(User) context.getBean(userType+vaccineChoice);
        	if(!user.IsVaccinated()) {
        		System.out.println("Please enter "+userType+" details:");
        		System.out.print("Name");
        		String name=sc.nextLine();
        		System.out.print("Age:");
        		int age=sc.nextInt();
        		sc.nextLine();
        		System.out.print("Appointment date");
        		String date=sc.nextLine();
        		System.out.print("Appointment time");
        		String time=sc.nextLine();
        		System.out.print("Appointment location");
        		String location=sc.nextLine();
        		TimeAndLocation timeAndLocation=new TimeAndLocation();
        		timeAndLocation.setDetails(time, location, date);
        		user.setUserDetails(name, age, timeAndLocation);
        		user.setAppointment();
        		System.out.print("Do you want to register for someone else\n1.Yes\n2.No");
        		int input = sc.nextInt();
        		sc.nextLine();
    			if (input == 2) 
    				break;
        		
        	}else {
        		System.out.println("User is already vaccinated");
        	}	
    	}
    	
    	
    	
        /*
		You need to complete this application as mentioned in the problem 
		statement build your own logic and perform the following tasks.

		 Tasks:
		1. Fetch context from ApplicationContext.xml and initiate Scanner.
		2. Fetch vaccine and User type choice.
		3. Get the required bean from context.
		4. Get the appointment details form user
		5. Display the appointment details
		6. Run the loop again to book for another user or else exit.
		 */

    }
}