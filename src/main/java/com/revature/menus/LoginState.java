package com.revature.menus;

import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.TooManyFailedLoginsException;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;
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

	// TODO: enable users to go back to the welcome page
	// TODO: figure out how to handle employees that are also customers
	public BankState HandleUserInput(String cmd) {
		if (id.equals("")) {
			id = cmd;
			return this;
		}
		else {
			pw = cmd;
			
			try {
				User user = signInService.Login(id,  pw);
				
				// employee sign-in
				if (user instanceof Employee) {
					return new EmployeeTransactionState((Employee)user);
				}
				// customer sign-in
				else if (user instanceof Customer) {
					return new CustomerTransactionState((Customer)user);
				}
				else {
					System.out.println("SOMETHING WEIRD HAPPENED!!!");
					return this;
				}
			}
			catch (UserNotFoundException e) {
				id = "";
				pw = "";
				System.out.println("User not found. Please enter a valid ID.");
			}
			catch (IncorrectPasswordException e) {
				pw = "";
				System.out.println("Incorrect password. Try again.");
			}
			catch (TooManyFailedLoginsException e) {
				System.out.println("Too many failed login attempts.\n");
				return new WelcomeState();
			}
			return this;
		}
	}

}
