package com.revature.services;

import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.IncorrectPasswordException;
import com.revature.exceptions.MalformedPasswordException;
import com.revature.models.Customer;
import com.revature.models.User;

public interface ISignInService {
	public User Login(String id, String password) throws IncorrectPasswordException, UserNotFoundException;
	
	public Customer Register(String id, String password);
	
	public String ValidatePassword(String password) throws MalformedPasswordException;

	public String GenerateId();
}
