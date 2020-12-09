package com.revature.services;

import com.revature.repositories.ICustomerDAO;

public class CustomerTransactionService implements ICustomerTransactionService {
	ICustomerDAO customerDAO;
	
	public CustomerTransactionService(ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}

	public double CheckBalance() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean Withdraw(double amount) {
		// TODO Auto-generated method stub
		return false;
	}

	public void Deposit(double amount) {
		// TODO Auto-generated method stub
		
	}
	
	public void Logout() {
		System.exit(0);
	}
}
