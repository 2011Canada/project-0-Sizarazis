package com.revature.services;

import java.util.List;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.NegativeNumberException;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.repositories.ICustomerDAO;
import com.revature.repositories.IEmployeeDAO;

public class EmployeeTransactionService implements IEmployeeTransactionService {
	IEmployeeDAO employeeDAO;
	ICustomerDAO customerDAO;
	
	public EmployeeTransactionService(IEmployeeDAO employeeDAO, ICustomerDAO customerDAO) {
		this.employeeDAO = employeeDAO;
		this.customerDAO = customerDAO;
	}

	public Customer ApproveAccount(Customer c) {
		c.SetRegistration(true);
		return c;
	}

	public Customer RejectAccount(Customer c) {
		c.SetRegistration(false);
		return c;
	}

	public void TransferMoney(Customer from, Customer to, double amount) throws NegativeNumberException, InsufficientFundsException {
		double fromBal = from.GetBalance();
		double toBal = to.GetBalance();
		if (amount < 0) {
			throw new NegativeNumberException();
		}
		else if (amount > fromBal) {
			throw new InsufficientFundsException();
		}
		else {
			from.SetBalance(fromBal - amount);
			to.SetBalance(toBal + amount);
		}
	}

	public Customer ViewAccount(String id) {
		return customerDAO.FindCustomerById(id);
	}

	public List<Customer> ViewAllAcounts() {
		return customerDAO.FindAllCustomers();
	}

	//TODO
	public Customer RegisterForCustomerAccount(Employee e) {
		return null;
	}
	
	public void Logout() {
		System.exit(0);
	}
	
}
