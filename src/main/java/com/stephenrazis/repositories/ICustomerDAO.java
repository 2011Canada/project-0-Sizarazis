package com.stephenrazis.repositories;

import java.util.List;

import com.stephenrazis.exceptions.CustomerNotFoundException;
import com.stephenrazis.models.Customer;

public interface ICustomerDAO {

	public Customer SaveCustomer();
	
	public Customer UpdateCustomer(Customer c);
	
	public List<Customer> FindAllCustomers();
	
	public Customer FindCustomerById(String id) throws CustomerNotFoundException;
}
