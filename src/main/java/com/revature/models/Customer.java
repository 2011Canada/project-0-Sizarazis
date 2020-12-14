package com.revature.models;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
	private final int customer_id;
	private List<Account> accounts;
	
	
	// constructors
	public Customer(int user_id, int customer_id, String password) {
		super(user_id, password);
		
		this.customer_id = customer_id;
		this.accounts = new ArrayList<Account>();
	}
	
	
	// getters and setters
	public int getCustomerId() {
		return this.customer_id;
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}


	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	
	public void addAccount(Account account) {
		accounts.add(account);
	}
	
	public void removeAccount(Account account) {
		accounts.remove(accounts.indexOf(account));
	}
	
	
	// overrides
	@Override
	public String Display() {
		String output = "user_iD: " + this.user_id + ", customer_id: " + this.customer_id + "\naccounts: \n";
		
		for (Account a : accounts) {
			output = output + a.Display();
		}

		return output;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + customer_id;
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (customer_id != other.customer_id)
			return false;
		return true;
	}
	
	
	
}
