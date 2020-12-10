package com.mindtree.encryption.exception;

public class UtilInvalidKeyException extends EncryptionUtilException {

	private static final long serialVersionUID = 1L;

	public UtilInvalidKeyException() {
		super();

	}

	public UtilInvalidKeyException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

	public UtilInvalidKeyException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public UtilInvalidKeyException(String message) {
		super(message);
	
	}

	public UtilInvalidKeyException(Throwable cause) {
		super(cause);
	
	}
	
}
