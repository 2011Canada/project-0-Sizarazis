package com.revature.exceptions;

public class TooManyFailedLoginsException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TooManyFailedLoginsException() {
		super("Too many failed login attempts.");
	}

}
