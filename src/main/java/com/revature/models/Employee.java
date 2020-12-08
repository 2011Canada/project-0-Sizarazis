package com.revature.models;

public class Employee extends User {
	private final String id;
	private String password;
	
	public Employee(String id, String password) {
		super(id, password);
		
		this.id = id;
		this.password = password;
	}
}
