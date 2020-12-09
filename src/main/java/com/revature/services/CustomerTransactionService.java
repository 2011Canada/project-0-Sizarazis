package com.revature.services;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.models.Customer;
import com.revature.repositories.ICustomerDAO;

public class CustomerTransactionService implements ICustomerTransactionService {
	ICustomerDAO customerDAO;
	Customer customer;
	
	public CustomerTransactionService(ICustomerDAO customerDAO, Customer customer) {
		this.customerDAO = customerDAO;
		this.customer = customer;
	}

	// NOTE: customer was propagated from the login state where it was received from the DAO, but it feels weird not interacting with the DAO at this stage
	public double CheckBalance() {
		return customer.GetBalance();
	}

	public double Withdraw(double amount) throws InsufficientFundsException {
		double balance = customer.GetBalance();
		
		if (amount < 0 || amount > balance) {
			throw new InsufficientFundsException();
		}
		else {
			balance = balance - amount;
			customer.SetBalance(balance);
			System.out.println("Transaction complete. Your new account balance is: " + customer.GetBalance());
			return balance;
		}
	}

	public void Deposit(double amount) throws InsufficientFundsException {
		if (amount < 0) {
			throw new InsufficientFundsException();
		}
		else {
			customer.SetBalance(customer.GetBalance() + amount);
			System.out.println("Your new balance is: " + customer.GetBalance());
		}
	}
	
	public void Logout() {
		System.exit(0);
	}
}
