package com.revature.exceptions;

public class InsufficientFundsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InsufficientFundsException() {
		System.out.println("There is not enough funds in your account to complete this transaction.\n");
	}
}
