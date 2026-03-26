package com.PrivayChat.scruber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScruberApplication {

	public static boolean STATE = true;

	public static void menu() {
		System.out.println("Welcome to the Scrubber Application!");
		System.out.println("Please select a scrubbing mode:");
		System.out.println("1. Only Digits");
		System.out.println("2. Only Emails");
		System.out.println("3. Full Scrubbing");
	}

	public static void main(String[] args) {
		SpringApplication.run(ScruberApplication.class, args);
		while (STATE) {

			menu();
			String input = System.console().readLine();

			switch (input) {
				case "1":
					System.out.println("Digits scrubbing mode.");
					break;
				case "2":
					System.out.println("Emails scrubbing mode.");
					break;
				case "3":
					System.out.println("Full Scrubbing mode.");
					break;
				default:
					System.out.println("Invalid selection. Please try again.");
			}
			
		}

	}

}
