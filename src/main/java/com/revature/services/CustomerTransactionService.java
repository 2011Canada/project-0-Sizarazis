package com.revature.services;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.NegativeNumberException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.repositories.IAccountDAO;
import com.revature.repositories.ICustomerDAO;
import com.revature.repositories.ITransactionLogDAO;

public class CustomerTransactionService implements ICustomerTransactionService {
	ICustomerDAO customerDAO;
	IAccountDAO accountDAO;
	ITransactionLogDAO transactionDAO;
	
	public CustomerTransactionService(ICustomerDAO customerDAO, IAccountDAO accountDAO, ITransactionLogDAO transactionDAO) {
		this.customerDAO = customerDAO;
		this.accountDAO = accountDAO;
		this.transactionDAO = transactionDAO;
	}
	
	
	public boolean IsCustomer(int customer_id) {
		return customerDAO.FindCustomerById(customer_id) != null ? true : false;
	}
	
	
	public double CheckBalance(int account_id) {
		return accountDAO.GetAccountBalance(account_id);
	}

	
	public double Withdraw(int account_id, double amount) throws InsufficientFundsException, NegativeNumberException {
		Account a = accountDAO.FindAccountById(account_id);
		
		if (amount < 0) {
			throw new NegativeNumberException();
		}
		else if (amount > a.GetBalance()) {
			throw new InsufficientFundsException();
		}
		else {
			a.SetBalance(a.GetBalance() - amount);
			accountDAO.UpdateAccount(a);
			transactionDAO.InsertLog("WITHDRAW", amount, account_id, -1);
			
			double newBal = accountDAO.GetAccountBalance(account_id);
			System.out.println("Transaction complete. Your new account balance is: " + newBal);
			return newBal;
		}
	}

	
	public void Deposit(int account_id, double amount) throws NegativeNumberException {
		Account a = accountDAO.FindAccountById(account_id);
		if (amount < 0) {
			throw new NegativeNumberException();
		}
		else {
			a.SetBalance(a.GetBalance() + amount);
			accountDAO.UpdateAccount(a);
			transactionDAO.InsertLog("DEPOSIT", amount, -1, account_id);
			
			System.out.println("Your new balance is: " + a.GetBalance());
		}
	}
	
	
	public void Transfer(int from_account, int to_account, double amount) throws InsufficientFundsException, NegativeNumberException, UserNotFoundException {
		Account from = accountDAO.FindAccountById(from_account);
		Account to = accountDAO.FindAccountById(to_account);
		double fromBal = 0;
		
		if (from.equals(to)) {
			System.out.println("You can't transfer money to yourself.\n");
			return;
		}
		
		if (from != null) {
		fromBal = from.GetBalance();
		}
		
		
		if (from == null || to == null) {
			throw new UserNotFoundException();
		}
		else if (amount < 0) {
			throw new NegativeNumberException();
		}
		else if (amount > fromBal) {
			throw new InsufficientFundsException();
		}
		else {
			double toBal = to.GetBalance();
			
			from.SetBalance(fromBal - amount);
			to.SetBalance(toBal + amount);
			
			accountDAO.UpdateAccount(from);
			accountDAO.UpdateAccount(to);
			transactionDAO.InsertLog("TRANSFER", amount, from_account, to_account);
		}
	}
	
	
	public void Logout() {
		System.exit(0);
	}

}
