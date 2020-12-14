package com.revature.repositories;

import java.util.List;

import com.revature.models.Account;

public class AccountDAO implements IAccountDAO{
	
	public void SaveAccount(Account account, int customer_id) {
		// TODO Auto-generated method stub
	}

	public void UpdateAccount(Account account) {
		// TODO Auto-generated method stub
	}

	public List<Account> FindAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Account> FindAllCustomersAccounts(int customer_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Account FindAccountById(int account_id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public double GetAccountBalance(int account_id) {
		return 0;
	}

}
