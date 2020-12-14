package com.revature.repositories;

import java.util.List;

import com.revature.models.Account;


public interface IAccountDAO {
	public void SaveAccount(Account account, int customer_id);
	
	public void UpdateAccount(Account account);
	
	public List<Account> FindAllAccounts();
	
	public List<Account> FindAllCustomersAccounts(int customer_id);
	
	public Account FindAccountById(int account_id);
	
	public double GetAccountBalance(int account_id);
}
