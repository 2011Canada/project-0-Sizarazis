package com.revature.services;

import com.revature.exceptions.CustomerNotFoundException;
import com.revature.exceptions.IncorrectUserLoginException;
import com.revature.models.User;

public interface ISignInService {
	public User Login(String id, String password) throws IncorrectUserLoginException, CustomerNotFoundException;
	
	public User Register(String id, String password);
	
	public void Logout();
}
