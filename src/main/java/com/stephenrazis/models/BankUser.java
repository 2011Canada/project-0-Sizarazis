package com.stephenrazis.models;

public class BankUser {
	private String username;
	private String password;
	private double balance;
	
	
	public BankUser(String username, String password) {
		this.username = username;
		this.password = password;
		this.balance = 0.00;
	}
	
	public BankUser(String username, String password, double balance) {
		this.username = username;
		this.password = password;
		this.balance = balance;
	}
	
	public String GetUsername() {
		return this.username;
	}
	
	// note: I may want to protect access to passwords
	public String GetPassword() {
		return this.password;
	}
	
	public void SetPassword(String nextPassword) {
		this.password = nextPassword;
	}
	
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
