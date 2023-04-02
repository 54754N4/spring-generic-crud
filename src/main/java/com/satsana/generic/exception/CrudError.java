package com.satsana.generic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface CrudError {
	@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
	public static final class NotFoundException extends RuntimeException {
		private static final long serialVersionUID = -1255398966972664938L;
	}
	
	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Duplicate entity field found when supposed to be unique")
	public static final class NotUniqueException extends RuntimeException {
		private static final long serialVersionUID = 8081400061512155247L;
	}
}
