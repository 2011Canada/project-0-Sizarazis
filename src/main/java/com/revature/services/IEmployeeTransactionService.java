package com.revature.services;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.NegativeNumberException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.Customer;
import com.revature.models.Employee;

public interface IEmployeeTransactionService {
	
	public void ApproveAccount(String id);
	
	public void RejectAccount(String id);
	
	public void TransferMoney(String from, String to, double amount) throws NegativeNumberException, InsufficientFundsException, UserNotFoundException;
	
	public Customer ViewAccount(String id);
	
	public String ViewAllAcounts();
	
	public Customer RegisterForCustomerAccount(Employee e);
	
	public void Logout();
	
}
