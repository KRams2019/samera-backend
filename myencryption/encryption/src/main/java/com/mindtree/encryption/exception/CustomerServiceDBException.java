package com.mindtree.encryption.exception;

public class CustomerServiceDBException extends CustomerServiceException {

	private static final long serialVersionUID = 1L;

	public CustomerServiceDBException() {
		super();
		
	}

	public CustomerServiceDBException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

	public CustomerServiceDBException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public CustomerServiceDBException(String message) {
		super(message);
	
	}

	public CustomerServiceDBException(Throwable cause) {
		super(cause);
	
	}
	
}
