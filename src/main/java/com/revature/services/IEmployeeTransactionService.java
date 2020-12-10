package com.revature.services;

import java.util.List;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.NegativeNumberException;
import com.revature.models.Customer;
import com.revature.models.Employee;

public interface IEmployeeTransactionService {
	
	public Customer ApproveAccount(Customer c);
	
	public Customer RejectAccount(Customer c);
	
	public void TransferMoney(Customer from, Customer to, double amount) throws NegativeNumberException, InsufficientFundsException;
	
	public Customer ViewAccount(String id);
	
	public List<Customer> ViewAllAcounts();
	
	public Customer RegisterForCustomerAccount(Employee e);
	
	public void Logout();
	
}
