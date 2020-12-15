package com.revature.services;

import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.MalformedPasswordException;
import com.revature.exceptions.TooManyFailedLoginsException;
import com.revature.models.Customer;
import com.revature.models.Employee;

public interface ISignInService {
	public Employee EmployeeLogin(int id, String password) throws IncorrectPasswordException, UserNotFoundException, TooManyFailedLoginsException;
	
	public Customer CustomerLogin(int id, String password) throws IncorrectPasswordException, UserNotFoundException, TooManyFailedLoginsException;
	
	public Customer Register(int id, String password);
	
	public String ValidatePassword(String password) throws MalformedPasswordException;

	public int GetNextCustomerId();

	public void DepositInitialAmount(int customer_id, double amount);
}
