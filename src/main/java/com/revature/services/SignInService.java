package com.revature.services;

import com.revature.exceptions.CustomerNotFoundException;
import com.revature.exceptions.IncorrectUserLoginException;
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
	public User Login(String id, String password) throws IncorrectUserLoginException, CustomerNotFoundException {
		Customer customer = customerDAO.FindCustomerById(id);
		Employee employee = employeeDAO.FindEmployeeById(id);
		
		if (customer == null && employee == null) {
			throw new CustomerNotFoundException();
		}
		// Right now, if an employee signs in they will only sign-in as an employee
		else if (employee != null && employee.GetPassword().equals(password)) {
			return new Employee(id, password);
		}
		else if (customer != null && customer.GetPassword().equals(password)) {
			return new Customer(id, password);
		}
		throw new IncorrectUserLoginException();
	}
		
	public User Register(String id, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}
