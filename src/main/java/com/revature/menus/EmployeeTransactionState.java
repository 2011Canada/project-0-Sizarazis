package com.revature.menus;

import com.revature.launcher.BankOfByteLauncher;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.repositories.AccountPostgresDAO;
import com.revature.repositories.CustomerPostgresDAO;
import com.revature.repositories.EmployeePostgresDAO;
import com.revature.repositories.TransactionsLogPostgresDAO;
import com.revature.services.EmployeeTransactionService;
import com.revature.services.IEmployeeTransactionService;

// TODO: I should allow an employee to make an account from here, so they register for an account with their user information already filled out

public class EmployeeTransactionState implements BankState {
	boolean hasShownTop = false;
	
	Employee employee;
	IEmployeeTransactionService ets;
	
	
	// constructor
	public EmployeeTransactionState(Employee employee) {
		this.employee = employee;
	
		this.ets = new EmployeeTransactionService(new EmployeePostgresDAO(), new AccountPostgresDAO(), new TransactionsLogPostgresDAO());
	}


	public String Display() {
		String s = "";
		
		if (!hasShownTop) {
			s = "\nHello employee " + this.employee.GetEmployeeId() + "!\n" +
					"Please enter one of the following commands:\n" +
					"    1. \"approve [account ID]\"              --> approve an account.\n" +
					"    2. \"reject [account ID]\"               --> reject an account.\n" +
					"    3. \"view_account [account ID]\"         --> view an account.\n" +
					"    4. \"view_all_accounts\"                 --> view all accounts.\n" +
					//"    5. \"transfer [from, to, amt]\"          --> transfer money.\n" +
					"    5. \"transactions\"                      --> see all of the logged transactions.\n" +
					"    6. \"register_for_account\"              --> register for a customer account\n" +
					"    7. \"logout\"                            --> logout.\n";
			hasShownTop = true;
		}
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
				int accountId = Integer.parseInt(split[1]);
				
				if (ets.ViewAccount(accountId) == null) {
					System.out.println("Please enter a valid account id\n");
				}
				else if (instruction.equals("approve")) {
					ets.ApproveAccount(accountId);

					System.out.println("Account approved.\n");
				}
				else if (instruction.equals("reject")) {
					ets.RejectAccount(accountId);
					
					System.out.println("Account rejected.\n");
				}
				else {
					System.out.println(ets.ViewAccount(accountId).Display());
				}
			}
		}
		// view all accounts
		else if (instruction.equals("view_all_accounts")) {
			System.out.println(ets.ViewAllAcounts());
		}
		// transfer between accounts
//		else if (instruction.equals("transfer")) {
//			if (split.length != 4) {
//				System.out.println("Unacceptable number of arguments.\n");
//			}
//			else {
//				try {
//					double amount = Double.parseDouble(split[3]);
//					System.out.println("Transferring...");
//					ets.TransferMoney(Integer.parseInt(split[1]), Integer.parseInt(split[2]), amount);
//					System.out.println("Transfered " + amount + " from account:" + split[1] + " to account:" + split[2]);
//					
//					//TODO: There might be a bug in checkBalance
//					//System.out.println("Account:" + split[1] + ", updated balance:" + ets.CheckBalance(Integer.parseInt(split[1])));
//					//System.out.println("Account:" + split[2] + ", updated balance:" + ets.CheckBalance(Integer.parseInt(split[2])) + "\n");
//					
//				}
//				// TODO: I should have a specific exception for AccountNotFoundException. Using UserNotFoundException is not good enough.
//				catch (UserNotFoundException e) {
//					System.out.println("At least one of the accounts entered were not found.\n");
//				}
//				catch (NumberFormatException e) {
//					System.out.println("Please ensure you are entering a valid monetary unit and a valid account id.\n");
//				}
//				catch (InsufficientFundsException e) {
//					System.out.println("There is not enough funds in your account to complete this transaction.\n");
//				}
//				catch (NegativeNumberException e) {
//					System.out.println("You cannot withdraw or deposit a negative number.\n");
//				}
//			}
//		}
		else if (instruction.equals("transactions")) {
			System.out.println(ets.GetTransactionLogs());
		}
		else if (instruction.equals("register_for_account")) {
			System.out.println("\nSetting up your customer account...");
			
			int customer_id = ets.RegisterForCustomerAccount(employee);
			
			System.out.println("Your account is set up. Please login as a customer with the ID:" + customer_id + ", and your current password.");
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
		BankOfByteLauncher.BoBLogger.info("Employee logged off, with the ID:" + employee.GetEmployeeId());
		System.out.println("\nConnection closed.");
		System.exit(0);
	}
}
