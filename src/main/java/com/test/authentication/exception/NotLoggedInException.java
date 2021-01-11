package com.test.authentication.exception;

public class NotLoggedInException extends Exception{

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 1L;
	
	public NotLoggedInException() {
		super("User must first login before accessing its user details.");
	}

}
