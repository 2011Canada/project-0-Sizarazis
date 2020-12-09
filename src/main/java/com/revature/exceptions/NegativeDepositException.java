package com.revature.exceptions;

public class NegativeDepositException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NegativeDepositException() {
		super("User tried to deposit a negative number.");
	}
	
}
