package com.revature.services;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.NegativeNumberException;

public interface ICustomerTransactionService {
	
	public double CheckBalance();
	
	public double Withdraw(double amount) throws InsufficientFundsException, NegativeNumberException;
	
	public void Deposit(double amount) throws NegativeNumberException;
	
	public void Logout();
}
