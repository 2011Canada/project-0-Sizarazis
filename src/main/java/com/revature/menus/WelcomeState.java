package com.revature.menus;

import java.util.Scanner;

public class WelcomeState implements BankState {

	public String Display() {
		String welcome = "\n" +
				"===========================\n" +
				"WELCOME TO THE BANK OF BYTE\n" +
				"===========================\n\n" + 
				"Please type 'login' to login to your existing account, \n" +
				"Or, type 'register' to begin registering for an account.\n";
		return welcome;
	}

	public BankState HandleUserInput(String cmd) {
		if (cmd.equals("login")) {
			return new LoginState();
		}
		else if (cmd.equals("register")) {
			// change state
			return new RegistrationState();
		}
		else {
			System.out.println("\nCommand not accepted.\n");
			return this;
		}
	}
}
