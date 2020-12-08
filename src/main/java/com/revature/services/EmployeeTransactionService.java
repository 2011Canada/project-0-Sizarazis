package com.revature.services;

import java.util.List;

import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.models.Verifiable;
import com.revature.repositories.IEmployeeDAO;

// TODO
public class EmployeeTransactionService implements IEmployeeTransactionService, Verifiable {
	IEmployeeDAO employeeDAO;
	
	public EmployeeTransactionService(IEmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public User Login(String id, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public User Register(String id, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public void Logout() {
		// TODO Auto-generated method stub
		
	}

	public Customer ApproveAccount(Customer c) {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer RejectAccount(Customer c) {
		// TODO Auto-generated method stub
		return null;
	}

	public double TransferMoney(Customer from, Customer to) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Customer ViewAccount(Customer c) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Customer> ViewAllAcounts() {
		// TODO Auto-generated method stub
		return null;
	}

	public Customer RegisterForCustomerAccount(Employee e) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
