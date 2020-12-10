package com.mindtree.encryption.exception;

public class FailedToReadExcelFileException extends CustomerServiceDBException {

	private static final long serialVersionUID = 1L;

	public FailedToReadExcelFileException() {
		super();
		
	}

	public FailedToReadExcelFileException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

	public FailedToReadExcelFileException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public FailedToReadExcelFileException(String message) {
		super(message);
		}

	public FailedToReadExcelFileException(Throwable cause) {
		super(cause);
	
	}

	
}
