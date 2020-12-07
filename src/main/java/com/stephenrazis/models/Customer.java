package com.stephenrazis.models;

public class Customer extends User {
	private String id;
	private String password;
	private double balance;
	private boolean isRegistered;
	
	
	// constructors
	public Customer(String id, String password) {
		super(id, password);
		
		this.id = id;
		this.password = password;
		this.balance = 0.00;
		this.isRegistered = false;
	}
	
	public Customer(String id, String password, double balance) {
		super(id, password);
		
		this.id = id;
		this.password = password;
		this.balance = balance;
		this.isRegistered = false;
	}
	
	// TODO
	// FOR THE CASE WHERE AN EMPLOYEE IS REGISTERING AS A CUSTOMER
	public Customer(String id, String password, boolean isEmployee) {
		super(id, password);
		
		this.id = id;
		this.password = password;

		this.isRegistered = true;
	}
	
	// getters and setters
	public double GetBalance() {
		return this.balance;
	}
	
	public void SetBalance(double nextBalance) {
		this.balance = nextBalance;
	}
	
	public void AddBalance(double toAdd) {
		this.balance = this.balance + toAdd;
	}
	
	public void SubtractBalance(double toSubtract) {
		this.balance = this.balance + toSubtract;
	}
}