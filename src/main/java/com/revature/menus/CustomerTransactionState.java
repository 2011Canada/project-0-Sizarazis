package com.revature.menus;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.NegativeNumberException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.repositories.AccountPostgresDAO;
import com.revature.repositories.CustomerPostgresDAO;
import com.revature.repositories.TransactionsLogPostgresDAO;
import com.revature.services.CustomerTransactionService;
import com.revature.services.ICustomerTransactionService;

public class CustomerTransactionState implements BankState {
	Customer customer;
	ICustomerTransactionService cts;
	
	public CustomerTransactionState(Customer customer) {
		this.customer = customer;
		cts = new CustomerTransactionService(new CustomerPostgresDAO(), new AccountPostgresDAO(), new TransactionsLogPostgresDAO());
	}

	public String Display() {
		String s = "";
		s = "\nHello customer " + this.customer.getCustomerId() + "!\n" +
				"Please enter one of the following commands:\n" +
				"    1. \"balance\"                        --> check the balance of your account.\n" +
				"    2. \"withdraw [money]\"               --> withdraw money from your account.\n" +
				"    3. \"deposit [money]\"                --> deposit money to your account.\n" +
				"    4. \"transfer [to, money]\"           --> transfer money to another account.\n" +
				"    5. \"logout\"                         --> logout from your account.\n";
		
		return s;
	}

	
	// TODO: It is only check a customers 1st account, and expecting them to have at least 1 account. This NEEDS to be fixed.
	public BankState HandleUserInput(String cmd) {
		try {
			String[] split = cmd.split(" ");
			
			// balance
			if (cmd.equals("balance")) {
				System.out.println("\nCurrent account balance: " + cts.CheckBalance(customer.getAccounts().get(0).GetAccountId()));
			}
			// withdraw
			else if (split[0].equals("withdraw") && split.length == 2) {
				double amount = Double.parseDouble(cmd.split(" ")[1]);
				
				System.out.println("\nAttempting to withdraw " + amount + " from your account.");
				
				cts.Withdraw(customer.getAccounts().get(0).GetAccountId(), amount);
			} 
			// deposit
			else if (split[0].equals("deposit") && split.length == 2) {
				double amount = Double.parseDouble(cmd.split(" ")[1]);
				
				System.out.println("\nAttempting to deposit " + amount + " to your account.");
				
				cts.Deposit(customer.getAccounts().get(0).GetAccountId(), amount);
			}
			else if (split[0].equals("transfer") && split.length == 3) {
				double amount = Double.parseDouble(cmd.split(" ")[2]);
				int from_account = customer.getAccounts().get(0).GetAccountId();
				int to_account = Integer.parseInt(split[1]);
				
				System.out.println("\nAttempting to transfer " + amount + " from account: " + from_account + ", to account: " + to_account);
				
				cts.Transfer(from_account, to_account, amount);
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
			System.out.println("You cannot withdraw, deposit, or transfer a negative number.\n");
		}
		catch (UserNotFoundException e) {
			System.out.println("One of the accounts in your transfer doesn't exist.\n");
		}
		
		return this;
	}
	
	public void Logout() {
		System.out.println("\nConnection closed.");
		System.exit(0);
	}

}
