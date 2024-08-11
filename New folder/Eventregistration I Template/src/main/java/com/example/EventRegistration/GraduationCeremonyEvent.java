package com.example.EventRegistration;

import java.util.ArrayList;
import java.util.List;

public class GraduationCeremonyEvent implements CollegeEvent {

	String name = "Graduation Ceremony";
	String address="Auditorium";
	String time="10 AM";
	String date="12 Nov 2024";
	int count = 0;
	ArrayList<Attendee> eventAttendees=new ArrayList<Attendee>();
	Attendee attendee;
	

	@Override
	public void registerStudent(Attendee user) {
		eventAttendees.add(user);
		count++;
	}

	@Override
	public List<Attendee> getAllAttendees() {
		return eventAttendees;
	}

	@Override
	public void printEventDetails() {
		System.out.println("\"The Graduation Ceremony details are as follows:\r\n"
				+ "                 Venue: "+this.address+"\r\n"
				+ "                 Time:  "+this.time +"\r\n"
				+ "                 Date:  "+this.date);
	}

	@Override
	public int getAttendeeCount() {
		return count;	
	}

	@Override
	public void setAttendee(Attendee attendee) {
		this.attendee=attendee;
	}

	/*
	 * 1. Create the following attributes: a. name (initialize it as
	 * "Graduation Ceremony". b. address (String) c. time (String) d. date (String)
	 * e. count (int) (initially 0) f. eventAttendees (List of Attendee) NOTE:
	 * Initialize the address, time and date attributes with some values. 2. Inject
	 * Attendee with attribute name "attendee" with setter injection. 3. Make this
	 * class an implementation of CollegeEvent interface and override the interface
	 * methods.
	 */

}
