package com.stephenrazis.repositories;

import java.util.ArrayList;
import java.util.List;

import com.stephenrazis.exceptions.CustomerNotFoundException;
import com.stephenrazis.exceptions.IncorrectUserLoginException;
import com.stephenrazis.models.Customer;

public class CustomerDAO implements ICustomerDAO {
	
	static List<Customer> customers = new ArrayList<Customer>();
	
	static {
		Customer c1 = new Customer("123456789", "password123", 20.00);
		Customer c2 = new Customer("1", "password123");
		Customer c3 = new Customer("2", "password123", 30.00);
		Customer c4 = new Customer("3", "password123", 40.00);
		Customer c5 = new Customer("4", "password123", 50.00);
		c1.SetRegistration(true);
		c2.SetRegistration(true);
		c3.SetRegistration(true);
		c4.SetRegistration(true);
		c5.SetRegistration(false);
		customers.add(c1);
		customers.add(c2);
		customers.add(c3);
		customers.add(c4);
		customers.add(c5);
	}

	public Customer SaveCustomer() {
		return null;
	}

	public Customer UpdateCustomer(Customer c) {
		return null;
	}

	public List<Customer> FindAllCustomers() {
		return customers;
	}

	public Customer FindCustomerById(String id) throws CustomerNotFoundException {
		for (Customer c : customers) {
			if (c.GetId().equals(id)) {
				return c;
			}
		}
		
		throw new CustomerNotFoundException();
	}
	
	public Customer FindIdPasswordCombo(String id, String password) throws IncorrectUserLoginException {
		for (Customer c : customers) {
			if (c.GetId().equals(id) && c.GetPassword().equals(password)) {
				return c;
			}
		}
		
		throw new IncorrectUserLoginException();
	}

}
