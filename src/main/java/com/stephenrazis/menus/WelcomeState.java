package com.stephenrazis.menus;

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

	public void HandleUserInput(String cmd) {
		if (cmd.equals("login")) {
			// change state
		}
		else if (cmd.equals("register")) {
			// change state
		}
		else {
			System.out.println("\nCommand not accepted.\n");
		}
	}

	public void NextState(BankState state) {
		// TODO Auto-generated method stub
		
	}

}
