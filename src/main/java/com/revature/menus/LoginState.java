package com.revature.menus;

import com.revature.services.ISignInService;
import com.revature.services.SignInService;

public class LoginState implements BankState {
	String id = "";
	String pw = "";
	ISignInService signInService;
	
	
	public LoginState() {
		this.signInService = new SignInService();
	}
	
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
			
			//NOTE: this is only a stub, may want to try/catch here so it can be displayed to the user
			signInService.Login(id,  pw);
			
			return new TransactionState();
		}
	}

}
