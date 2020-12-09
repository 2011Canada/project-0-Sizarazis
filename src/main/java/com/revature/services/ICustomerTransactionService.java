package com.revature.services;

public interface ICustomerTransactionService {
	
	public double CheckBalance();
	
	public boolean Withdraw(double amount);
	
	public void Deposit(double amount);
	
	public void Logout();
}
