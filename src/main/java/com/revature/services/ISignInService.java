package com.revature.services;

import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.IncorrectPasswordException;
import com.revature.models.User;

public interface ISignInService {
	public User Login(String id, String password) throws IncorrectPasswordException, UserNotFoundException;
	
	public User Register(String id, String password);
}
