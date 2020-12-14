package com.revature.repositories;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;

public class CustomerDAO implements ICustomerDAO {
	
	static List<Customer> customers = new ArrayList<Customer>();
	
	static {
		Customer c1 = new Customer(123456789, 1, "password123");
		Customer c2 = new Customer(1, 2, "password123");
		Customer c3 = new Customer(2, 3, "password123");
		Customer c4 = new Customer(3, 4, "password123");
		Customer c5 = new Customer(4, 5, "password123");
			
		Account a1 = new Account(1, 10.00, true);
		c1.addAccount(a1);
		
		Account a2 = new Account(1, 10.00, true);
		Account a3 = new Account(2, 100.00, true);
		c2.addAccount(a2);
		c2.addAccount(a3);
		
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

	public Customer FindCustomerById(int id) {
		for (Customer c : customers) {
			if (c.getCustomerId() == id) {
				return c;
			}
		}
		return null;
	}
}
