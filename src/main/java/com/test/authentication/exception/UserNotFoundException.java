package com.test.authentication.exception;

public class UserNotFoundException extends Exception {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super("The specified user cannot be found");
	}

}
