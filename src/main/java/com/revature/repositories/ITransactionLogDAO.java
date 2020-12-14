package com.revature.repositories;

public interface ITransactionLogDAO {

	public void InsertLog(String type, double amount, int from_account, int to_account);
	
	public String GetAllLogs();
	
}
