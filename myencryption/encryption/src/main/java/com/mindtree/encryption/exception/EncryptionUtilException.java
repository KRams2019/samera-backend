package com.mindtree.encryption.exception;

public class EncryptionUtilException extends CustomerServiceException
{
	private static final long serialVersionUID = 1L;

	public EncryptionUtilException() {
		super();

	}

	public EncryptionUtilException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public EncryptionUtilException(String message, Throwable cause) {
		super(message, cause);

	}

	public EncryptionUtilException(String message) {
		super(message);
	}

	public EncryptionUtilException(Throwable cause) {
		super(cause);
	}
	
}
