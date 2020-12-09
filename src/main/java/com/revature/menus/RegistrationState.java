package com.revature.menus;

import com.revature.exceptions.MalformedPasswordException;
import com.revature.services.ISignInService;
import com.revature.services.SignInService;

public class RegistrationState implements BankState {
	boolean willingToRegister = false;
	String id;
	String password;
	
	ISignInService signInService;
	
	public RegistrationState() {
		willingToRegister = false;
		id = "";
		password = "";
		
		signInService = new SignInService();
	}
	
	public String Display() {
		String s = "";
		
		if (!willingToRegister) {
			s = "\nHELLO SIR OR MADAM!\n" +
					"If you would like to register for an account, please type \"register\".\n" +
					"Please note that this bank comes with no perks, no security, and no guarantees.\n" +
					"Any and all assets you may give us are at your own risk.\n";
		}
		else if (willingToRegister && !id.equals("") && password.equals("")) {
			s = "\nPlease enter a password.\n" +
				"The password must:\n" +
					"    1. Have at least 1 digit\n" +
					"    2. Have at least 1 lowercase letter\n" +
					"    3. Have at least 1 uppercase letter\n" + 
					"    4. Have at least 1 special character\n" +
					"    5. Not have any spaces\n" + 
					"    6. Be at least 8 characters\n";
		}

		return s;
	}

	public BankState HandleUserInput(String cmd) {
		// willing to register
		if (!willingToRegister && cmd.equals("register")) {
			willingToRegister = true;
			
			id = signInService.GenerateId();
			System.out.println("Your Bank of Byte ID will be: " + id);
			
			return this;
		}
		// not willing to register OR didn't write the 'register' command
		else if (!willingToRegister && !cmd.equals("register")) {
			System.out.println("Fine! We didn't want you anyways!\n");
			return new WelcomeState();
		}
		// entering a password
		else if (willingToRegister) {
			if (password.length() == 0) {
				try {
					password = signInService.ValidatePassword(cmd);
					System.out.println("\nPlease reenter your password.");
					return this;
				}
				catch(MalformedPasswordException e) {
					//TODO: Anything to do here?
					return this;
				}
			}
		}
		// validating their password
		if (password.length() > 0) {
			if (cmd.equals(password)) {
				signInService.Register(id, password);
				System.out.println("\nCongratulations on registering for a Bank of Byte account!" + 
									"\nYou will be able to login after one of our many employees has validated your account\n");
				return new WelcomeState();
			}
			else {
				// TODO: give them a way out of this loop
				System.out.println("\nYour passwords don't match. Please reenter your password.");
				return this;
			}
		}
		System.out.println("CODE REACHED A STATE I DIDN'T WANT IT TO.");
		return this;
	}

}
