package com.glaucus.task.controller.error;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * View Model for transferring error message with a list of field errors.
 */
public class Error implements Serializable {

	private static final long serialVersionUID = 11132452352231L;

	@JsonIgnore
	private String field = null;

	@JsonIgnore
	private String errorCode = null;

	@JsonProperty("message")
	private String message = null;

	@JsonProperty("description")
	private String description = null;

	public Error(String message) {
		this(message, null);
	}

	public Error(String message, String description) {
		this.message = message;
		this.description = description;
	}

	public Error(String message, String description, String field) {
		this.message = message;
		this.description = description;
		this.field = field;
	}

	public Error() {

	}

	public Error field(String field) {
		this.field = field;
		return this;
	}

	/**
	 * Get field
	 * 
	 * @return field
	 **/

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Error errorCode(String errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	/**
	 * Get errorCode
	 * 
	 * @return errorCode
	 **/

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Error message(String message) {
		this.message = message;
		return this;
	}

	/**
	 * Get message
	 * 
	 * @return message
	 **/

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Error description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Get description
	 * 
	 * @return description
	 **/

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Error error = (Error) o;
		return Objects.equals(this.field, error.field) && Objects.equals(this.errorCode, error.errorCode)
				&& Objects.equals(this.message, error.message) && Objects.equals(this.description, error.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(field, errorCode, message, description);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Error {\n");

		sb.append("    field: ").append(toIndentedString(field)).append("\n");
		sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
		sb.append("    message: ").append(toIndentedString(message)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
