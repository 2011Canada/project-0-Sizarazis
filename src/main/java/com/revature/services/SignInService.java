package com.revature.services;

import com.revature.exceptions.CustomerNotFoundException;
import com.revature.exceptions.IncorrectUserLoginException;
import com.revature.models.Customer;
import com.revature.models.User;

public class SignInService implements ISignInService {

	public User Login(String id, String password) throws IncorrectUserLoginException, CustomerNotFoundException {
		// TODO Auto-generated method stub
		
		//TEST CODE NEED TO CHECK FOR EMPLOYEE OR CUSTOMER
		return new Customer(id, password);
	}

	public User Register(String id, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	public void Logout() {
		// TODO Auto-generated method stub
		
	}

}
