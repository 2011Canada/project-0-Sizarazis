package com.revature.exceptions;

public class MalformedPasswordException extends Exception {

	public MalformedPasswordException( ) {
		System.out.println("\nMalformed password. Please format your password as advised.");
	}
	
}
