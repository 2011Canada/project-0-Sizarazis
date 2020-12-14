package com.revature.services;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.NegativeNumberException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.repositories.IAccountDAO;
import com.revature.repositories.ICustomerDAO;

public class CustomerTransactionService implements ICustomerTransactionService {
	ICustomerDAO customerDAO;
	IAccountDAO accountDAO;
	
	public CustomerTransactionService(ICustomerDAO customerDAO, IAccountDAO accountDAO) {
		this.customerDAO = customerDAO;
		this.accountDAO = accountDAO;
	}
	
	
	public boolean IsCustomer(int customer_id) {
		return customerDAO.FindCustomerById(customer_id) != null ? true : false;
	}
	
	
	public double CheckBalance(int account_id) {
		return accountDAO.GetAccountBalance(account_id);
	}

	
	public double Withdraw(int account_id, double amount) throws InsufficientFundsException, NegativeNumberException {
		Account a = accountDAO.FindAccountById(account_id);
		double balance = a.GetBalance();
		
		if (amount < 0) {
			throw new NegativeNumberException();
		}
		else if (amount > balance) {
			throw new InsufficientFundsException();
		}
		else {
			a.SetBalance(balance - amount);
			balance = a.GetBalance();
			accountDAO.UpdateAccount(a);
			
			System.out.println("Transaction complete. Your new account balance is: " + balance);
			return balance;
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
			
			System.out.println("Your new balance is: " + a.GetBalance());
		}
	}
	
	
	public void Transfer(int from_account, int to_account, double amount) throws InsufficientFundsException, NegativeNumberException, UserNotFoundException {
		Account from = accountDAO.FindAccountById(from_account);
		Account to = accountDAO.FindAccountById(to_account);
		
		double fromBal = from.GetBalance();
		double toBal = to.GetBalance();
		
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
			from.SetBalance(fromBal - amount);
			to.SetBalance(toBal + amount);
			
			accountDAO.UpdateAccount(from);
			accountDAO.UpdateAccount(to);
		}
	}
	
	
	public void Logout() {
		System.exit(0);
	}

}
