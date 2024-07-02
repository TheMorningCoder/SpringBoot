package com.saumyaSingh.testingSpringDemo;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class TestingSpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingSpringDemoApplication.class, args);
		System.out.println("Hii saumya");
		Scanner sc=new Scanner(System.in);
		String type=sc.nextLine();
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
		Table longTable=(Table) context.getBean("longTable");
		Table shortTable=(Table) context.getBean("shortTable");
		if(type.equals("long")) {
			System.out.println(longTable.showDetails());
		}else {
			System.out.println(shortTable.showDetails());
		}
	}
}
