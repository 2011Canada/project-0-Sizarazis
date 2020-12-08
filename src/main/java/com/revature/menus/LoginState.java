package com.revature.menus;

import java.util.Scanner;

public class LoginState implements BankState {
	String id = "";
	String pw = "";
	
	public String Display() {
		String s = "";
		if (id.equals("")) {
			s = "\nPlease enter your ID: ";
		}
		else {
			s = "\nPlease enter your password: ";
		}
		return s;
	}

	// TODO propagate or check the user inputs
	public BankState HandleUserInput(String cmd) {
		if (id.equals("")) {
			id = cmd;
			return this;
		}
		else {
			pw = cmd;
			return new TransactionState();
		}
	}

}
