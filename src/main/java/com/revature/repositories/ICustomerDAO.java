package com.revature.repositories;

import java.util.List;

import com.revature.models.Customer;

public interface ICustomerDAO {

	public Customer SaveCustomer();
	
	public Customer UpdateCustomer(Customer c);
	
	public List<Customer> FindAllCustomers();
	
	public Customer FindCustomerById(String id);
}
