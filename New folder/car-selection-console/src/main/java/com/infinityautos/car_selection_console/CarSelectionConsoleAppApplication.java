package com.infinityautos.car_selection_console;
import java.util.Scanner;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class CarSelectionConsoleAppApplication {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		Scanner sc=new Scanner(System.in);
		System.out.println("Hi, please enter your name");
		String name=sc.nextLine();
		System.out.println("Please select car of your choice:");
		System.out.println("1)Family Car \n2)Sports Car \n3)Truck");
		int userChoice=sc.nextInt();
		String carChoice="";
		switch(userChoice) {
			case 1 -> carChoice="normalCar";
			case 2 -> carChoice="sportsCar";
			case 3 -> carChoice="truck";
			default->{
				System.out.println("Invalid choice");
			}
		}
		Car c=(Car) context.getBean(carChoice);
		c.setOwnerName(name);
		System.out.println(c.getInfo());
	}
}
