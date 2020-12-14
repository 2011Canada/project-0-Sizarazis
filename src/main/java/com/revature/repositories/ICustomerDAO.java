package com.revature.repositories;

import com.revature.models.Customer;

public interface ICustomerDAO {

	public void SaveCustomer(int customer_id, String password);
	
	public Customer FindCustomerById(int customer_id);
	
	public String GetPassword(int customer_id);
	
	public int GetNextCustomerId();
}
