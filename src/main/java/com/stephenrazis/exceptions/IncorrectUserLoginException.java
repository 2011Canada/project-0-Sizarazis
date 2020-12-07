package com.stephenrazis.exceptions;

public class IncorrectUserLoginException extends Exception {
	private static final long serialVersionUID = 1L;

	public IncorrectUserLoginException() {
		super("Wrong ID or password.");
	}
}
