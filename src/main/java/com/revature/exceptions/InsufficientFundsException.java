package com.revature.exceptions;

public class InsufficientFundsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InsufficientFundsException() {
		super("not enough funds in the account to withdraw that amount");
	}
}
