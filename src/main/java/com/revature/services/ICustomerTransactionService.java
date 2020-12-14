package com.revature.services;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.NegativeNumberException;
import com.revature.exceptions.UserNotFoundException;

public interface ICustomerTransactionService {
	
	public boolean IsCustomer(int id);
	
	public double CheckBalance(int id);
	
	public double Withdraw(int id, double amount) throws InsufficientFundsException, NegativeNumberException;
	
	public void Deposit(int id, double amount) throws NegativeNumberException;
	
	public void Transfer(int from_account, int to_account, double amount) throws InsufficientFundsException, NegativeNumberException, UserNotFoundException;
	
	public void Logout();
}
