package com.revature.services;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.NegativeNumberException;
import com.revature.models.Customer;
import com.revature.repositories.ICustomerDAO;

public class CustomerTransactionService implements ICustomerTransactionService {
	ICustomerDAO customerDAO;
	
	public CustomerTransactionService(ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	
	
	public boolean IsCustomer(String id) {
		return customerDAO.FindCustomerById(id) != null ? true : false;
	}
	
	// TODO: Should the parameter by an id?
	// NOTE: customer was propagated from the login state where it was received from the DAO, but it feels weird not interacting with the DAO at this stage
	public double CheckBalance(String id) {
		return customerDAO.FindCustomerById(id).GetBalance();
	}

	public double Withdraw(String id, double amount) throws InsufficientFundsException, NegativeNumberException {
		Customer c = customerDAO.FindCustomerById(id);
		double balance = c.GetBalance();
		
		if (amount < 0) {
			throw new NegativeNumberException();
		}
		else if (amount > balance) {
			throw new InsufficientFundsException();
		}
		else {
			c.SetBalance(balance - amount);
			balance = c.GetBalance();
			customerDAO.UpdateCustomer(c);
			
			System.out.println("Transaction complete. Your new account balance is: " + balance);
			return balance;
		}
	}

	public void Deposit(String id, double amount) throws NegativeNumberException {
		Customer c = customerDAO.FindCustomerById(id);
		if (amount < 0) {
			throw new NegativeNumberException();
		}
		else {
			c.SetBalance(c.GetBalance() + amount);
			customerDAO.UpdateCustomer(c);
			
			System.out.println("Your new balance is: " + c.GetBalance());
		}
	}
	
	public void Logout() {
		System.exit(0);
	}
}
