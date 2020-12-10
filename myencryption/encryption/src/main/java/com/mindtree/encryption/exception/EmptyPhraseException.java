package com.mindtree.encryption.exception;

public class EmptyPhraseException extends CustomerServiceDBException {

	private static final long serialVersionUID = 1L;

	public EmptyPhraseException() {
		super();
	
	}

	public EmptyPhraseException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

	public EmptyPhraseException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public EmptyPhraseException(String message) {
		super(message);
	
	}

	public EmptyPhraseException(Throwable cause) {
		super(cause);
	
	}
	
}
