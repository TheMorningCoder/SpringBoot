package com.example.EventRegistration;

import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@SpringBootApplication
public class EventRegistrationApplication {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		// Import scanner and take ClassPathXmlApplicationContext.

		System.out.println("Welcome to the Graduation Ceremony Registration Application");
		// Take the college event bean from the application context.
		CollegeEvent event =(CollegeEvent)context.getBean("event") ;
		event.printEventDetails();
		// Print the event details.

		while (true) {
			System.out.println("Do you want to register for the ceremony\n1. Yes\n2. No");
			int input = sc.nextInt();
			if (input == 1) {
				System.out.println("Please enter your name");
				sc.nextLine();
				String name=sc.nextLine();
				System.out.println("Please enter your department");
				String department=sc.nextLine();
				System.out.println("In which year did you pass out?");
				int year=sc.nextInt();
				StudentAttendee attendee=(StudentAttendee) context.getBean("student");
				attendee.setAttendeeDetails(name, department, year);
				event.registerStudent(attendee);
				attendee.printRegistrationConfirmation();
				/*
				 1. Take attendee details from the console.
				 2. Get studentAttendee bean from context and set the attendee details
				 3. Register the attendee for the event.
				 4. Print the registration confirmation.
				 */

			} else if(input == 2) {
				break;
			} else {
				System.out.println("Invalid Choice");
				return;
			}
		}

		// Get the number of attendees and print along with the statement below
		System.out.println("No. of attendees registered are: " + event.getAttendeeCount());
		System.out.println("The list of attendees are:");
		for(Attendee attendee: event.getAllAttendees()) {
			System.out.println(attendee.getAttendeeName()+" Reference id: "+attendee);
		}
		// Print all the attendee names with their reference ids as given in the sample output..

	}

}
