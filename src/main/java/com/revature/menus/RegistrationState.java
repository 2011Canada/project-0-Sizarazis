package com.revature.menus;

import com.revature.exceptions.MalformedPasswordException;
import com.revature.exceptions.NegativeNumberException;
import com.revature.launcher.BankOfByteLauncher;
import com.revature.services.ISignInService;
import com.revature.services.SignInService;

public class RegistrationState implements BankState {
	boolean willingToRegister = false;
	boolean hasFailedPassword = false;
	boolean validPassword = false;
	int customer_id;
	String password;
	double startingBalance;
	
	ISignInService signInService;
	
	public RegistrationState() {
		willingToRegister = false;
		customer_id = -1;
		password = "";
		startingBalance = -1.0;
		
		signInService = new SignInService();
	}
	
	public String Display() {
		String s = "";
		
		if (!willingToRegister) {
			s = "\nHELLO SIR OR MADAM!\n" +
					"If you would like to register as a customer, please type \"register\".\n" +
					"Please note that this bank comes with no perks, no security, and no guarantees.\n" +
					"Any and all assets you may give us are at your own risk.\n";
		}
		else if (willingToRegister && customer_id > 0 && password.equals("")) {
			if (!hasFailedPassword) {
				s = "\nPlease enter a password.\n" +
					"The password must:\n" +
						"    1. Have at least 1 digit\n" +
						"    2. Have at least 1 lowercase letter\n" +
						"    3. Have at least 1 uppercase letter\n" + 
						"    4. Have at least 1 special character\n" +
						"    5. Not have any spaces\n" + 
						"    6. Be at least 8 characters\n";
			}
			else {
				s = "\nInvalid password. Please try again.\n";
			}
		}

		return s;
	}

	public BankState HandleUserInput(String cmd) {
		// willing to register
		if (!willingToRegister && cmd.equals("register")) {
			willingToRegister = true;
			
			//TODO: refactor this so that the customer_id is assigned after the customer is created. 
			//		It won't matter for this project, but this customer_id might be taken by the time the SQL query exectures
			customer_id = signInService.GetNextCustomerId();
			System.out.println("Your Bank of Byte customer ID will be: " + customer_id);
			
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
					System.out.println("\nMalformed password. Please format your password as advised.");
					return this;
				}
			}
		}
		// validating their password
		if (password.length() > 0 && !validPassword) {
			if (cmd.equals(password)) {
				signInService.Register(customer_id, password);
				System.out.println("\nCongratulations on registering for a Bank of Byte account!"  +
									"\nPlease enter a starting balance for your account (or 0, if you don't want to deposit yet).\n");
				
				validPassword = true;
				BankOfByteLauncher.BoBLogger.info("New customer registered, with the ID:" + customer_id);
				
				return this;
			}
			else {
				System.out.println("\nYour passwords don't match. Please reenter your password.");
				return this;
			}
		}
		// setting the starting balance of their account
		if (startingBalance < 0) {
			try {
				startingBalance = Double.parseDouble(cmd);
				
				if (startingBalance < 0) {
					throw new NegativeNumberException();
				}
				
				signInService.DepositInitialAmount(customer_id, startingBalance);
				
				System.out.println(startingBalance + " has been deposited into your new account.\n" +
						"You will be able to login after one of our many employees has validated your account\n");
				
				return new WelcomeState();
			}
			catch (NumberFormatException | NegativeNumberException e) {
				System.out.println("Please enter a valid monetary number greater than 0\n");
			}
		}
		
		return this;
	}

}
