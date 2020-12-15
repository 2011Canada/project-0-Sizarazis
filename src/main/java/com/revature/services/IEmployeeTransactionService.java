package com.revature.services;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.NegativeNumberException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Account;
import com.revature.models.Employee;

public interface IEmployeeTransactionService {
	
	public void ApproveAccount(int account_id);
	
	public void RejectAccount(int account_id);
	
	public void TransferMoney(int from, int to, double amount) throws NegativeNumberException, InsufficientFundsException, UserNotFoundException;
	
	public Account ViewAccount(int account_id);
	
	public String ViewAllAcounts();
	
	public int RegisterForCustomerAccount(Employee e);
	
	public String GetTransactionLogs();
	
	public double CheckBalance(int account_id);
	
	public void Logout();
	
}
