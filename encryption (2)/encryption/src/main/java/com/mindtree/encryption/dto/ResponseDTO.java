package com.mindtree.encryption.dto;

import java.time.LocalTime;
import java.util.Date;

import com.mindtree.encryption.util.DateFormatter;

public class ResponseDTO {

	private String message;

	private boolean success;

	private boolean error;

	private Object body;

	private String date = DateFormatter.convertUtilDateToString(new Date());;

	public ResponseDTO() {
		super();
	}

	public ResponseDTO(String message, boolean success, boolean error, Object body) {
		super();
		this.message = message;
		this.success = success;
		this.error = error;
		this.body = body;
	}

	public ResponseDTO(String message, Throwable cause) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
