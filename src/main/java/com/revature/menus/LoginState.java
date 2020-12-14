package com.revature.menus;

import com.revature.exceptions.UserNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.TooManyFailedLoginsException;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.services.ISignInService;
import com.revature.services.SignInService;

public class LoginState implements BankState {
	int id = -1;
	String pw = "";
	boolean hasAskedForSignIn = false;
	boolean isEmployeeLogin = false;
	
	ISignInService signInService;
	
	public static Logger BoBLogger = LogManager.getLogger("com.revature.BoB");
	
	public LoginState() {
		this.signInService = new SignInService();
	}
	
	public String Display() {
		String s = "";
		if (hasAskedForSignIn) {
			if (id < 0) {
				s = "\nPlease enter your ID: ";
			} 
			else {
				s = "\nPlease enter your password: ";
			}
		}
		else {
			s = "\nType \"employee\" for the employee login portal, otherwise type \"customer\".\n";
		}
		
		return s;
	}

	// TODO: enable users to go back to the welcome page
	public BankState HandleUserInput(String cmd) {
		if (!hasAskedForSignIn) {
			if (cmd.equals("employee")) {
				hasAskedForSignIn = true;
				isEmployeeLogin = true;
			}
			else if (cmd.equals("customer")) {
				hasAskedForSignIn = true;
			}
		}
		else {
			try {
				// assign id
				if (id < 0) {
					id = Integer.parseInt(cmd);
					return this;
				}
				// assign password and attempt login
				else {
					pw = cmd;

					// attempt employee login
					if (isEmployeeLogin) {
						Employee employee = signInService.EmployeeLogin(id, pw);
						return new EmployeeTransactionState(employee);
					}
					// attempt customer login
					else {
						Customer customer = signInService.CustomerLogin(id, pw);
						return new CustomerTransactionState(customer);
					}
				}
			}
			catch (UserNotFoundException e) {
				id = -1;
				pw = "";
				System.out.println("User not found. Please enter a valid ID.");
			}
			catch (IncorrectPasswordException e) {
				pw = "";
				System.out.println("Incorrect password. Try again.");
			}
			catch (TooManyFailedLoginsException e) {
				System.out.println("Too many failed login attempts.\n");
				
				//BoBLogger.info("User: " + id + " has failed to login 3 times in a row.");
				
				return new WelcomeState();
			}
			catch (NumberFormatException e) {
				System.out.println("\nInvalid ID. Your ID will be a number.\n");
			}
		}
		
		return this;
	}

}
