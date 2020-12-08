package com.revature.services;

import java.util.List;

import com.revature.models.Customer;
import com.revature.models.Employee;

public interface IEmployeeTransactionService {
	
	public Customer ApproveAccount(Customer c);
	
	public Customer RejectAccount(Customer c);
	
	public double TransferMoney(Customer from, Customer to);
	
	public Customer ViewAccount(Customer c);
	
	public List<Customer> ViewAllAcounts();
	
	public Customer RegisterForCustomerAccount(Employee e);
	
}
