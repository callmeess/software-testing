package com.PrivayChat.scruber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.PrivayChat.scruber.Interfaces.IScrub;
import com.PrivayChat.scruber.Enums.ScrubbingModes;
import com.PrivayChat.scruber.impl.scrubDigitImpl;
import com.PrivayChat.scruber.impl.scrubEmailImpl;

@SpringBootApplication
public class ScruberApplication {

	public static boolean SYS_STATE = true;

	public static void menu() {
		System.out.println("Welcome to the Scrubber Application!");
		System.out.println("Please select a scrubbing mode:");
		System.out.println("1. Only Digits");
		System.out.println("2. Only Emails");
		System.out.println("3. Full Scrubbing");
	}

	public static void main(String[] args) {
		SpringApplication.run(ScruberApplication.class, args);

		IScrub scrub = new Scrub(new scrubDigitImpl(), new scrubEmailImpl());

		while (SYS_STATE) {

			menu();
			String COMMAND = System.console().readLine();
			System.out.println("Please enter the prompt to be scrubbed");
			String prompt = System.console().readLine();
			String result = null;
			String MODE = null;

			switch (COMMAND) {
				case "1":
					MODE = "Digits scrubbing mode.";
					result = scrub.scrubPrompt(prompt, ScrubbingModes.ONLY_DIGITS);
					break;
				case "2":
					MODE = "Emails scrubbing mode.";
					result = scrub.scrubPrompt(prompt, ScrubbingModes.ONLY_EMAILS);
					break;
				case "3":
					MODE = "Full Scrubbing mode.";
					result = scrub.scrubPrompt(prompt, ScrubbingModes.FULL_SCRUBBING);
					break;
				default:
					System.out.println("Invalid selection. Please try again.");
			}

			System.out.println("");
			System.out.println(MODE);
			System.out.println(result);

		}

	}

}
