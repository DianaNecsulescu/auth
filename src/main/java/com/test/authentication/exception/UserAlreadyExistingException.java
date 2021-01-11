package com.test.authentication.exception;

public class UserAlreadyExistingException extends RuntimeException {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistingException() {
		super("The specified username already exists.");
	}

}
