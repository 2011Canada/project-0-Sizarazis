package com.stephenrazis.models;

public interface Verifiable {
	
	public User Login(String id, String password);
	
	public User Register(String id, String password);
	
	public void Logout();
	
}
