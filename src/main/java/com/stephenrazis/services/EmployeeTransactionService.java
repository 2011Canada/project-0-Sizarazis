package com.stephenrazis.services;

import com.stephenrazis.models.Customer;

// TODO
public class EmployeeTransactionService {
	public Customer ApproveAccount(Customer c) {
		c.SetRegistration(true);
		return c;
	}
	
	public Customer RejectAccount(Customer c) {
		c.SetRegistration(false);
		return c;
	}
	
	public double TransferMoney(Customer from, Customer to, double ammount) {
		return 0.00;
	}
	
	public Customer ViewAccount(Customer c) {
		return c;
	}
	
	public String ViewAllAccounts() {
		return "";
	}
	
	public Customer RegisterForCustomerAccount() {
		return new Customer("a", "b");
	}
}
