package com.revature.services;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.NegativeNumberException;

public interface ICustomerTransactionService {
	
	public boolean IsCustomer(String id);
	
	public double CheckBalance(String id);
	
	public double Withdraw(String id, double amount) throws InsufficientFundsException, NegativeNumberException;
	
	public void Deposit(String id, double amount) throws NegativeNumberException;
	
	public void Logout();
}
