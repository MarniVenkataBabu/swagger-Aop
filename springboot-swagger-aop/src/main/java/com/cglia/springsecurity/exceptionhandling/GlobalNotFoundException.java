package com.cglia.springsecurity.exceptionhandling;

public class GlobalNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GlobalNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public GlobalNotFoundException(String message) {
		super(message);
	}

	public GlobalNotFoundException(Throwable cause) {
		super(cause);
	}
	

}
