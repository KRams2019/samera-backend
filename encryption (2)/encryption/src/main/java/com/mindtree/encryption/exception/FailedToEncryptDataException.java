package com.mindtree.encryption.exception;

public class FailedToEncryptDataException extends EncryptionUtilException {
	private static final long serialVersionUID = 1L;

	public FailedToEncryptDataException() {
		super();
	
	}

	public FailedToEncryptDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

	public FailedToEncryptDataException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public FailedToEncryptDataException(String message) {
		super(message);
	
	}

	public FailedToEncryptDataException(Throwable cause) {
		super(cause);
	
	}
	
}
