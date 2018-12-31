package com.glaucus.task.controller.error;

public final class ErrorConstants {

	
	public static final String ERR_ACCESS_DENIED = "error.accessDenied";
	public static final String ERR_VALIDATION = "error.validation";
	public static final String ERR_METHOD_NOT_SUPPORTED = "error.methodNotSupported";
	public static final String ERR_INTERNAL_SERVER_ERROR = "error.internalServerError";
	public static final String ERR_SQL_ERROR = "error.sqlerError";
	public static final String TYPE_MISMATCH = "error.typeMismatch";
	public static final String ERR_FIELD = "error.fieldNotFound";
	public static final String ERR_DB_NO_RECORD_FOUND="GLAU100";
	public static final String ERR_INVALID_DATEFORMAT="GLAU101";
	public static final String ERR_MISSING_PARAMETER="GLAU102";
	public static final String ERR_SERVICE_DOWN="GLAU103";
	public static final String ERR_CONTENT_TYPE_NOT_SUPPORTED="GLAU104";


	
	public static final String ERR_ITEM_COUNT_REQUIRED ="error.itemCount.notnull";

	public static final String ERR_ITEM_COUNT_INVALID ="error.itemCount.format";
	
	public static final String ERR_MAX_LENGTH ="{javax.validation.constraints.Max.message}";
	public static final String ERR_MIN_LENGTH ="{javax.validation.constraints.Min.message}";

	
	
	public static final String ERR_SIZE_LENGTH ="{javax.validation.constraints.Size.message}";
	

	private ErrorConstants() {
	}

}
