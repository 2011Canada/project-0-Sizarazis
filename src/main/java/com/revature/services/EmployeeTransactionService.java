package com.revature.services;

import java.util.List;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.NegativeNumberException;
import com.revature.exceptions.UserNotFoundException;
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

	public void ApproveAccount(String id) {
		Customer c = customerDAO.FindCustomerById(id);
		c.SetRegistration(true);
		customerDAO.UpdateCustomer(c);
	}

	public void RejectAccount(String id) {
		Customer c = customerDAO.FindCustomerById(id);
		c.SetRegistration(false);
		customerDAO.UpdateCustomer(c);
	}

	public void TransferMoney(String from, String to, double amount) throws NegativeNumberException, InsufficientFundsException, UserNotFoundException {
		Customer fromC = customerDAO.FindCustomerById(from);
		Customer toC = customerDAO.FindCustomerById(to);
		
		double fromBal = fromC.GetBalance();
		double toBal = toC.GetBalance();
		
		if (fromC == null || toC == null) {
			throw new UserNotFoundException();
		}
		else if (amount < 0) {
			throw new NegativeNumberException();
		}
		else if (amount > fromBal) {
			throw new InsufficientFundsException();
		}
		else {
			fromC.SetBalance(fromBal - amount);
			toC.SetBalance(toBal + amount);
			
			customerDAO.UpdateCustomer(fromC);
			customerDAO.UpdateCustomer(toC);
		}
	}

	public Customer ViewAccount(String id) {
		return customerDAO.FindCustomerById(id);
	}

	public String ViewAllAcounts() {
		StringBuilder sb = new StringBuilder("");
		List<Customer> customers = customerDAO.FindAllCustomers();
		for (Customer c : customers) {
			sb = sb.append(c.Display() + "\n");
		}
		
		return sb.toString();
	}

	//TODO
	public Customer RegisterForCustomerAccount(Employee e) {
		return null;
	}
	
	public void Logout() {
		System.exit(0);
	}
	
}
