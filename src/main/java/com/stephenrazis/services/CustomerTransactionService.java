package com.stephenrazis.services;

import com.stephenrazis.models.User;
import com.stephenrazis.models.Verifiable;
import com.stephenrazis.repositories.ICustomerDAO;
import com.stephenrazis.repositories.IEmployeeDAO;

public class CustomerTransactionService implements ICustomerTransactionService, Verifiable {
	ICustomerDAO customerDAO;
	
	public CustomerTransactionService(ICustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
		
	public User Login(String id, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public User Register(String id, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public void Logout() {
		// TODO Auto-generated method stub
		
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
}
