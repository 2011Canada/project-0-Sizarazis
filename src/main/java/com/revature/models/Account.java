package com.revature.models;

public class Account implements Displayable {
	private final int account_id;
	private double balance;
	private boolean isVerified;
	
	
	//constructors
	public Account (int id) {
		this.account_id = id;
		balance = 0;
		isVerified = false;
	}
	
	public Account (int id, double balance) {
		this.account_id = id;
		this.balance = balance;
	}
	
	public Account (int id, double balance, boolean isVerified) {
		this.account_id = id;
		this.balance = balance;
		this.isVerified = isVerified;
	}
	
	
	//getters and setters
	public int GetAccountId() {
		return this.account_id;
	}
	
	public double GetBalance() {
		return balance;
	}
	
	public void SetBalance(double newBal) {
		this.balance = newBal;
	}
	
	public void AddBalance(double toAdd) {
		this.balance = this.balance + toAdd;
	}
	
	public boolean GetVerification() {
		return this.isVerified;
	}
	
	public void SetVerification(boolean isVerified) {
		this.isVerified = isVerified;
	}

	//overrides
	public String Display() {
		String s = "Account ID: " + this.account_id + ", isVerified: " + this.isVerified + ", balance: " + this.balance + "\n";
		
		return s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + account_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (account_id != other.account_id)
			return false;
		return true;
	}
	
}
