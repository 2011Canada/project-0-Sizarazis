package com.revature.menus;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.NegativeNumberException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Employee;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.EmployeeDAO;
import com.revature.services.CustomerTransactionService;
import com.revature.services.EmployeeTransactionService;
import com.revature.services.ICustomerTransactionService;
import com.revature.services.IEmployeeTransactionService;

public class EmployeeTransactionState implements BankState {
	Employee employee;
	ICustomerTransactionService cts;
	IEmployeeTransactionService ets;
	
	public EmployeeTransactionState(Employee employee) {
		this.employee = employee;
		
		
		this.cts = new CustomerTransactionService(new CustomerDAO());
		this.ets = new EmployeeTransactionService(new EmployeeDAO(), new CustomerDAO());
	}

	// TODO: Only show this once! (state for entrance, another state for "enter command:" or something)
	public String Display() {
		String s = "";
		s = "\nHello employee " + this.employee.GetId() + "!\n" +
				"Please enter one of the following commands:\n" +
				"    1. \"approve [customerID]\"              --> approve an account.\n" +
				"    2. \"reject [customerID]\"               --> reject an account.\n" +
				"    3. \"view_account [customerID]\"         --> view an account (given by the customer id).\n" +
				"    4. \"view_all_accounts\"                 --> view all accounts.\n" +
				"    5. \"transfer [from, to, amt]\"          --> transfer money.\n" +
				"    6. \"register_as_customer\"              --> register for an account.\n" +
				"    7. \"logout\"                            --> logout.\n";
		
		return s;
	}

	public BankState HandleUserInput(String cmd) {
		String[] split = cmd.split(" ");
		String instruction = split[0];
		
		// approve, reject, and view account
		if (instruction.equals("approve") || instruction.equals("reject") || instruction.equals("view_account")) {
			if (split.length != 2) {
				System.out.println("Please provide the right number of parameters.");
			}
			else {
				String customerId = split[1];
				
				if (!cts.IsCustomer(customerId)) {
					System.out.println("Please enter a valid account id\n");
				}
				else if (instruction.equals("approve")) {
					ets.ApproveAccount(customerId);

					System.out.println("Account approved.\n");
				}
				else if (instruction.equals("reject")) {
					ets.RejectAccount(customerId);
					
					System.out.println("Account rejected.\n");
				}
				else {
					System.out.println(ets.ViewAccount(customerId).Display());
				}
			}
		}
		// view all accounts
		else if (instruction.equals("view_all_accounts")) {
			System.out.println(ets.ViewAllAcounts());
		}
		// transfer between accounts
		else if (instruction.equals("transfer")) {
			if (split.length != 4) {
				System.out.println("Unacceptable number of arguments.\n");
			}
			else {
				try {
					double amount = Double.parseDouble(split[3]);
					System.out.println("Transferring...");
					ets.TransferMoney(split[1], split[2], amount);
					System.out.println("Transfered " + amount + " from ID:" + split[1] + " to ID:" + split[2]);
					System.out.println("ID:" + split[1] + ", updated balance:" + cts.CheckBalance(split[1]));
					System.out.println("ID:" + split[2] + ", updated balance:" + cts.CheckBalance(split[2]) + "\n");
					
				}
				catch (UserNotFoundException e) {
					System.out.println("At least of the user IDs entered were not found.\n");
				}
				catch (NumberFormatException e) {
					System.out.println("Please ensure you are entering a valid monetary unit after withdraw or deposit.\n");
				}
				catch (InsufficientFundsException e) {
					System.out.println("There is not enough funds in your account to complete this transaction.\n");
				}
				catch (NegativeNumberException e) {
					System.out.println("You cannot withdraw or deposit a negative number.\n");
				}
			}
		}
		// TODO
		// register for their own account
		else if (instruction.equals("register_as_customer")) {
			
		}
		// logout
		else if (instruction.equals("logout")) {
			Logout();
		}
		// invalid instruction
		else {
			System.out.println("\nPlease enter a valid instruction.\n");
		}
		
		return this;
	}
	
	public void Logout() {
		System.out.println("\nConnection closed.");
		System.exit(0);
	}
}
