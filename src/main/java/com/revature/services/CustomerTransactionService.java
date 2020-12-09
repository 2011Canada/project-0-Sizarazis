package com.revature.services;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.NegativeNumberException;
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

	public double Withdraw(double amount) throws InsufficientFundsException, NegativeNumberException {
		double balance = customer.GetBalance();
		
		if (amount < 0) {
			throw new NegativeNumberException();
		}
		else if (amount > balance) {
			throw new InsufficientFundsException();
		}
		else {
			customer.SetBalance(balance - amount);
			balance = customer.GetBalance();
			System.out.println("Transaction complete. Your new account balance is: " + balance);
			return balance;
		}
	}

	public void Deposit(double amount) throws NegativeNumberException {
		if (amount < 0) {
			throw new NegativeNumberException();
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
