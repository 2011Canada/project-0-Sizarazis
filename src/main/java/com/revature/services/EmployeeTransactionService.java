package com.revature.services;

import java.util.List;

import com.revature.exceptions.InsufficientFundsException;
import com.revature.exceptions.NegativeNumberException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.launcher.BankOfByteLauncher;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Employee;
import com.revature.repositories.IAccountDAO;
import com.revature.repositories.IEmployeeDAO;
import com.revature.repositories.ITransactionLogDAO;

public class EmployeeTransactionService implements IEmployeeTransactionService {
	IEmployeeDAO employeeDAO;
	IAccountDAO accountDAO;
	ITransactionLogDAO transactionDAO;
	
	public EmployeeTransactionService(IEmployeeDAO employeeDAO, IAccountDAO accountDAO, ITransactionLogDAO transactionDAO) {
		this.employeeDAO = employeeDAO;
		this.accountDAO = accountDAO;
		this.transactionDAO = transactionDAO;
	}

	public void ApproveAccount(int account_id) {
		Account a = accountDAO.FindAccountById(account_id);
		a.SetVerification(true);
		accountDAO.UpdateAccount(a);
	}

	public void RejectAccount(int account_id) {
		Account a = accountDAO.FindAccountById(account_id);
		a.SetVerification(false);
		accountDAO.UpdateAccount(a);
	}

	public void TransferMoney(int from, int to, double amount) throws NegativeNumberException, InsufficientFundsException, UserNotFoundException {
		Account fromAccount = accountDAO.FindAccountById(from);
		Account toAccount = accountDAO.FindAccountById(to);
		
		double fromBal = fromAccount.GetBalance();
		double toBal = toAccount.GetBalance();
		
		if (fromAccount == null || toAccount == null) {
			throw new UserNotFoundException();
		}
		else if (amount < 0) {
			throw new NegativeNumberException();
		}
		else if (amount > fromBal) {
			throw new InsufficientFundsException();
		}
		else {
			fromAccount.SetBalance(fromBal - amount);
			toAccount.SetBalance(toBal + amount);
			
			accountDAO.UpdateAccount(fromAccount);
			accountDAO.UpdateAccount(toAccount);
			transactionDAO.InsertLog("TRANSFER", amount, from, to);
		}
	}

	public Account ViewAccount(int account_id) {
		return accountDAO.FindAccountById(account_id);
	}

	public String ViewAllAcounts() {
		StringBuilder sb = new StringBuilder("");
		List<Account> accounts = accountDAO.FindAllAccounts();
		
		if (accounts == null) {
			return "no accounts found.\n";
		}
		
		for (Account a : accounts) {
			sb = sb.append(a.Display() + "\n");
		}
		
		return sb.toString();
	}

	public String GetTransactionLogs() {
		return transactionDAO.GetAllLogs();
	}

	
	//TODO: should I put a check here or in the DAO?
	public double CheckBalance(int account_id) {
		return accountDAO.GetAccountBalance(account_id);
	}
	
	
	//The return value is an int representing the customer ID of the employee's associated account.
	public int RegisterForCustomerAccount(Employee e) {
		Customer c = employeeDAO.FindCustomerByUserId(e.GetUserId());
		int out;
		
		if (c == null) {
			out = employeeDAO.RegisterForAccount(e);
		}
		else {
			out = c.getCustomerId();
		}
		
		BankOfByteLauncher.BoBLogger.info("Employee:" + e.GetEmployeeId() + " registered for the customer account:" + out);
		return out;
	}
	
	public void Logout() {
		System.exit(0);
	}
	
}
