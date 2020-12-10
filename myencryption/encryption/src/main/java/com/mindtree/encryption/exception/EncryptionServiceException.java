package com.mindtree.encryption.exception;

public class EncryptionServiceException extends CustomerServiceException {
	private static final long serialVersionUID = 1L;

	public EncryptionServiceException() {
		super();
	
	}

	public EncryptionServiceException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

	public EncryptionServiceException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public EncryptionServiceException(String message) {
		super(message);
	
	}

	public EncryptionServiceException(Throwable cause) {
		super(cause);
	
	}
	
}
