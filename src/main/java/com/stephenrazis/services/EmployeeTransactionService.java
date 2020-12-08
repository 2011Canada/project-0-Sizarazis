package com.stephenrazis.services;

import java.util.List;

import com.stephenrazis.models.Customer;
import com.stephenrazis.models.Employee;
import com.stephenrazis.models.User;
import com.stephenrazis.models.Verifiable;
import com.stephenrazis.repositories.IEmployeeDAO;

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
