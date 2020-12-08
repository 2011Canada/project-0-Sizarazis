package com.revature.services;

import com.revature.models.User;

public interface ISignInService {
	public User Login(String id, String password);
	
	public User Register(String id, String password);
	
	public void Logout();
}
