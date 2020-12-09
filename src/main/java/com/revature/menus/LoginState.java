package com.revature.menus;

import com.revature.exceptions.CustomerNotFoundException;
import com.revature.exceptions.IncorrectUserLoginException;
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

	// TODO propagate or check the user inputs
	public BankState HandleUserInput(String cmd) {
		if (id.equals("")) {
			id = cmd;
			return this;
		}
		else {
			pw = cmd;
			
			try {
				User user = signInService.Login(id,  pw);
				
				// TODO: figure out how to handle employees that are also customers
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
			catch (CustomerNotFoundException e) {
				id = "";
				pw = "";
				System.out.println("Customer not found. Please enter a valid ID");
			}
			catch (IncorrectUserLoginException e) {
				pw = "";
				System.out.println("Incorrect password. Try again.");
			}
			return this;
		}
	}

}
