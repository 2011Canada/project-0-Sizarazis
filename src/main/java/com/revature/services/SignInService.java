package com.revature.services;

import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.IncorrectPasswordException;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.models.User;
import com.revature.repositories.CustomerDAO;
import com.revature.repositories.EmployeeDAO;
import com.revature.repositories.ICustomerDAO;
import com.revature.repositories.IEmployeeDAO;

public class SignInService implements ISignInService {
	ICustomerDAO customerDAO;
	IEmployeeDAO employeeDAO;
	
	public SignInService() {
		this.customerDAO = new CustomerDAO();
		this.employeeDAO = new EmployeeDAO();
	}
	
	// TODO: WHAT ACCOUNT WILL AN EMPLOYEE HAVE IF THEY ARE BOTH A CUSTOMER AND AN EMPLOYEE
	// ALSO: I may want to hide the password in the console
	public User Login(String id, String password) throws IncorrectPasswordException, UserNotFoundException {
		Customer customer = customerDAO.FindCustomerById(id);
		Employee employee = employeeDAO.FindEmployeeById(id);
		
		if (customer == null && employee == null) {
			throw new UserNotFoundException();
		}
		// Right now, if an employee signs in they will only sign-in as an employee
		else if (employee != null && employee.GetPassword().equals(password)) {
			return employee;
		}
		else if (customer != null && customer.GetPassword().equals(password)) {
			return customer;
		}
		else {
			throw new IncorrectPasswordException();
		}
	}
		
	public User Register(String id, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}
