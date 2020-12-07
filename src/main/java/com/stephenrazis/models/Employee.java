package com.stephenrazis.models;


// TODO
public class Employee extends User {

	public Employee(String id, String password) {
		super(id, password);
	}
	
	
	public boolean ApproveAccount(String id) {
		return false;
	}
	
	public boolean RejectAccount(String id) {
		return false;
	}
	
	public double TransferMoney(String to, double ammount) {
		return 0.00;
	}
	
	public String ViewAccount(String id) {
		return "";
	}
	
	public Customer RegisterCustomerAccount() {
		return new Customer("a", "b");
	}

}
