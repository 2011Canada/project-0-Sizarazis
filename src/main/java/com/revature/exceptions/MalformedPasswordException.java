package com.revature.exceptions;

public class MalformedPasswordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MalformedPasswordException( ) {
		super("Malformed password construction.");
	}
	
}
