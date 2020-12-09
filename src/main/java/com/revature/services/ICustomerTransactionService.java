package com.revature.services;

import com.revature.exceptions.InsufficientFundsException;

public interface ICustomerTransactionService {
	
	public double CheckBalance();
	
	public double Withdraw(double amount) throws InsufficientFundsException;
	
	public void Deposit(double amount) throws InsufficientFundsException;
	
	public void Logout();
}
