package com.glaucus.task.controller.error;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * Controller advice to translate the server side exceptions to client-friendly
 * json structures.
 */
@ControllerAdvice
public class ExceptionTranslator {

	@ExceptionHandler(ServletRequestBindingException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Errors processValidationError(ServletRequestBindingException ex) {
		List<Error> listOfError = new ArrayList<>();
		Error error = new Error();
		error.setField(ex.getMessage().split("'")[1]);
		error.setErrorCode(ErrorConstants.ERR_MISSING_PARAMETER);
		error.setMessage(ex.getMessage());
		error.setDescription(ex.getMessage());
		listOfError.add(error);
		return createErrorsFromException(listOfError);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Errors processParameterizedValidationError(ResourceNotFoundException exception) {
		List<Error> listOfError = new ArrayList<>();
		Error error = new Error();
		// error.setField(field);
		error.setErrorCode(ErrorConstants.ERR_DB_NO_RECORD_FOUND);
		error.setMessage(exception.getMessage());
		error.setDescription(exception.getMessage());
		listOfError.add(error);
		return createErrorsFromException(listOfError);
	}

	@ExceptionHandler(ResourceAccessException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	@ResponseBody
	public Errors processServiceDownException(ResourceAccessException exception) {
		List<Error> listOfError = new ArrayList<>();
		Error error = new Error();
		exception.getLocalizedMessage();
		// error.setField(field);
		error.setErrorCode(ErrorConstants.ERR_SERVICE_DOWN);
		error.setMessage(exception.getMessage());
		error.setDescription(exception.getMessage());
		listOfError.add(error);
		return createErrorsFromException(listOfError);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public Errors processMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
		List<Error> listOfError = new ArrayList<>();
		Error error = new Error();
		error.setErrorCode(ErrorConstants.ERR_METHOD_NOT_SUPPORTED);
		error.setMessage(exception.getMessage());
		error.setDescription(exception.getMessage());
		listOfError.add(error);
		return createErrorsFromException(listOfError);
	}

	@ExceptionHandler(JpaSystemException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Error processMethodJPAException(JpaSystemException exception) {
		return new Error(ErrorConstants.ERR_SQL_ERROR,
				exception.getCause().getMessage() + ", " + exception.getCause().getCause().getMessage());
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Error processMethodSQLException(DataIntegrityViolationException exception) {
		return new Error(ErrorConstants.ERR_SQL_ERROR,
				exception.getCause().getMessage() + ", " + exception.getCause().getCause().getMessage());
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Object processMethodHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
		if (exception.getCause().getMessage().contains("java.lang.Boolean")) {
			return new Error(ErrorConstants.TYPE_MISMATCH,
					(exception.getCause() != null ? "Invalid boolean Format" : ""));
		}
		if (exception.getCause().getMessage().contains("java.lang.Long")) {
			return new Error(ErrorConstants.TYPE_MISMATCH, (exception.getCause() != null ? "Invalid long Format" : ""));
		}
		if (exception.getCause().getMessage().contains("java.lang.Integer")) {
			return new Error(ErrorConstants.TYPE_MISMATCH,
					(exception.getCause() != null ? "Invalid integer Format" : ""));
		}
		if (exception.getCause().getMessage().contains("java.lang.Float")) {
			return new Error(ErrorConstants.TYPE_MISMATCH,
					(exception.getCause() != null ? "Invalid float Format" : ""));
		}
		return new Error(ErrorConstants.ERR_SQL_ERROR,
				exception.getCause().getMessage() + ", " + exception.getCause().getCause().getMessage());
	}

	@ExceptionHandler(EntityNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Error processMethodHttpEntityNotFoundException(EntityNotFoundException exception) {
		return new Error(ErrorConstants.ERR_SQL_ERROR, (exception.getCause() != null ? exception.getCause().getMessage()
				+ (exception.getCause().getCause() != null ? ", " + exception.getCause().getCause().getMessage() : "")
				: exception.getMessage()));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Error processNumberFormatException(MethodArgumentTypeMismatchException exception) {
		return new Error(ErrorConstants.TYPE_MISMATCH, (exception.getCause() != null ? exception.getCause().getMessage()
				+ (exception.getCause().getCause() != null ? ", " + exception.getCause().getCause().getMessage() : "")
				: exception.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> processRuntimeException(Exception ex) {
		BodyBuilder builder;
		Error errorVM;
		ResponseStatus responseStatus = AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class);
		if (responseStatus != null) {
			builder = ResponseEntity.status(responseStatus.value());
			errorVM = new Error("error." + responseStatus.value().value(), responseStatus.reason());
		} else {
			builder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
			errorVM = new Error(ErrorConstants.ERR_INTERNAL_SERVER_ERROR, "Internal server error");
		}
		return builder.body(errorVM);
	}

	private Errors createErrorsFromException(List<Error> listOfError) {
		Errors errors = new Errors();
		errors.setErrorList(listOfError);
		return errors;
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Errors missingContentTypeError(HttpMediaTypeNotSupportedException ex) {
		List<Error> listOfError = new ArrayList<>();
		Error error = new Error();
		error.setField("Content type");
		error.setErrorCode(ErrorConstants.ERR_CONTENT_TYPE_NOT_SUPPORTED);
		error.setMessage(ex.getMessage());
		error.setDescription(ex.getMessage());
		listOfError.add(error);
		return createErrorsFromException(listOfError);
	}

}
