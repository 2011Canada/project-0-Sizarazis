package com.revature.models;

public class Account {
	//TODO
	double balance;
	
	//CUSTOMERS CAN HAVE MULTIPLE ACCOUNTS SO I NEED TO ABSTRACT THIS FROM CUSTOMERS
	public double GetBalance() {
		return balance;
	}
	
}
