package com.glaucus.task.controller.error;

import java.io.Serializable;

/**
 * View Model for transferring error message with a list of field errors.
 */
public class ErrorFeildVM implements Serializable {

	private static final long serialVersionUID = 1L;
	private String field;
	private String errorCode;
	private String message;
	private String description;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
