package com.revature.services;

import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.MalformedPasswordException;
import com.revature.exceptions.TooManyFailedLoginsException;
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
	
	int loginAttempts;
	public static final int MAX_LOGIN_ATTEMPTS = 3;
	
	public SignInService() {
		this.customerDAO = new CustomerDAO();
		this.employeeDAO = new EmployeeDAO();
		
		loginAttempts = 0;
		
	}
	
	// TODO: WHAT ACCOUNT WILL AN EMPLOYEE HAVE IF THEY ARE BOTH A CUSTOMER AND AN EMPLOYEE
	// ALSO: I may want to hide the password in the console
	// ALSO: Right now, if an employee signs in they will only sign-in as an employee
	public User Login(String id, String password) throws IncorrectPasswordException, UserNotFoundException, TooManyFailedLoginsException {
		Customer customer = customerDAO.FindCustomerById(id);
		Employee employee = employeeDAO.FindEmployeeById(id);
		
		// bad username
		if (customer == null && employee == null) {
			throw new UserNotFoundException();
		}
		// good employee login
		else if (employee != null && employee.GetPassword().equals(password)) {
			return employee;
		}
		// good customer login
		else if (customer != null && customer.GetPassword().equals(password)) {
			return customer;
		}
		// bad password
		else {
			loginAttempts++;
			if (loginAttempts < MAX_LOGIN_ATTEMPTS) {
				throw new IncorrectPasswordException();
			}
			else {
				throw new TooManyFailedLoginsException();
			}
		}
	}

	// TODO
	public Customer Register(String id, String password) {
		return null;
	}

	
	public String ValidatePassword(String password) throws MalformedPasswordException {
		String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
		
		if (!password.matches(pattern)) {
			throw new MalformedPasswordException();
		}
		else {
			return password;
		}
	}

	
	// TODO
	public String GenerateId() {
		return "1234";
	}
}
