package com.revature.menus;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.NegativeNumberException;
import com.revature.models.Customer;
import com.revature.repositories.CustomerDAO;
import com.revature.services.CustomerTransactionService;
import com.revature.services.ICustomerTransactionService;

public class CustomerTransactionState implements BankState {
	Customer customer;
	ICustomerTransactionService cts;
	
	public CustomerTransactionState(Customer customer) {
		this.customer = customer;
		cts = new CustomerTransactionService(new CustomerDAO(), customer);
	}

	public String Display() {
		String s = "";
		s = "\nHello customer " + this.customer.GetId() + "!\n" +
				"Please enter one of the following commands:\n" +
				"    1. \"balance\"           --> check the balance of your account.\n" +
				"    2. \"withdraw [money]\"  --> withdraw money from your account.\n" +
				"    3. \"deposit [money]\"   --> deposit money to your account.\n" +
				"    4. \"logout\"            --> logout from your account.\n";
		
		return s;
	}

	public BankState HandleUserInput(String cmd) {
		try {
			String[] split = cmd.split(" ");
			
			// balance
			if (cmd.equals("balance")) {
				System.out.println("\nCurrent account balance: " + cts.CheckBalance());
			}
			// withdraw
			else if (split[0].equals("withdraw") && split.length == 2) {
				double amount = Double.parseDouble(cmd.split(" ")[1]);
				
				System.out.println("\nAttempting to withdraw " + amount + " from your account.");
				
				cts.Withdraw(amount);
			} 
			// deposit
			else if (split[0].equals("deposit") && split.length == 2) {
				double amount = Double.parseDouble(cmd.split(" ")[1]);
				System.out.println("\nAttempting to deposit " + amount + " to your account.");
				
				cts.Deposit(amount);
			}
			// logout
			else if (cmd.equals("logout")) {
				Logout();
			}
			// malformed command
			else {
				System.out.println("\nPlease enter a valid command.");
			}
		}
		catch (NumberFormatException e) {
			System.out.println("Please ensure you are entering a valid monetary unit after withdraw or deposit.\n");
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Please ensure you are entering the correct amount of arguments.\n");
		}
		catch (InsufficientFundsException e) {
			System.out.println("There is not enough funds in your account to complete this transaction.\n");
		}
		catch (NegativeNumberException e) {
			System.out.println("You cannot withdraw or deposit a negative number.\n");
		}
		
		return this;
	}
	
	public void Logout() {
		System.out.println("\nConnection closed.");
		System.exit(0);
	}

}
