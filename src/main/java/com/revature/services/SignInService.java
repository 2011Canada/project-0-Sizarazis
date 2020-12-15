package com.revature.services;

import com.revature.exceptions.UserNotFoundException;
import com.revature.launcher.BankOfByteLauncher;

import java.util.List;

import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.MalformedPasswordException;
import com.revature.exceptions.TooManyFailedLoginsException;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.repositories.AccountPostgresDAO;
import com.revature.repositories.CustomerPostgresDAO;
import com.revature.repositories.EmployeePostgresDAO;
import com.revature.repositories.IAccountDAO;
import com.revature.repositories.ICustomerDAO;
import com.revature.repositories.IEmployeeDAO;

public class SignInService implements ISignInService {
	ICustomerDAO customerDAO;
	IEmployeeDAO employeeDAO;
	IAccountDAO accountDAO;
	
	int loginAttempts;
	public static final int MAX_LOGIN_ATTEMPTS = 3;
	
	public SignInService() {
		this.customerDAO = new CustomerPostgresDAO();
		this.employeeDAO = new EmployeePostgresDAO();
		this.accountDAO = new AccountPostgresDAO();
		
		loginAttempts = 0;
		
	}
	
	
	// NOTE: I may want to hide the password in the console
	public Employee EmployeeLogin(int employee_id, String password)
			throws IncorrectPasswordException, UserNotFoundException, TooManyFailedLoginsException {
		Employee employee = employeeDAO.FindEmployeeById(employee_id);
		
		// bad username
		if (employee == null) {
			throw new UserNotFoundException();
		}
		// good employee login (if they also have a customer id, then they are expected to go here)
		else if (employee != null && employeeDAO.GetPassword(employee_id).equals(password)) {
			return employee;
		}
		// bad password
		else {
			loginAttempts++;
			if (loginAttempts < MAX_LOGIN_ATTEMPTS) {
				throw new IncorrectPasswordException();
			}
			else {
				BankOfByteLauncher.BoBLogger.warn("Employee: " + employee_id + " has failed to login 3 times in a row.");
				throw new TooManyFailedLoginsException();
			}
		}
	}

	
	// NOTE: I may want to hide the password in the console
	public Customer CustomerLogin(int customer_id, String password)
			throws IncorrectPasswordException, UserNotFoundException, TooManyFailedLoginsException {
		Customer customer = customerDAO.FindCustomerById(customer_id);
		
		// bad user id
		if (customer == null) {
			throw new UserNotFoundException();
		}
		// good customer login
		else if (customer != null && customerDAO.GetPassword(customer_id).equals(password)) {
			return customer;
		}
		// bad password
		else {
			loginAttempts++;
			if (loginAttempts < MAX_LOGIN_ATTEMPTS) {
				throw new IncorrectPasswordException();
			}
			else {
				BankOfByteLauncher.BoBLogger.info("Customer: " + customer_id + " has failed to login 3 times in a row.");
				throw new TooManyFailedLoginsException();
			}
		}
	}

	
	public Customer Register(int customer_id, String password) {
		customerDAO.SaveCustomer(customer_id, password);
		
		return customerDAO.FindCustomerById(customer_id);
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

	
	public int GetNextCustomerId() {
		int nextCustomerId = customerDAO.GetNextCustomerId();
		
		return nextCustomerId;
	}


	@Override
	public void DepositInitialAmount(int customer_id, double amount) {
		List<Account> accounts = accountDAO.FindAllCustomersAccounts(customer_id);
		
		Account lastCreatedAccount = accounts.get(accounts.size() - 1);
		lastCreatedAccount.AddBalance(amount);
		
		accountDAO.UpdateAccount(lastCreatedAccount);
	}
}
