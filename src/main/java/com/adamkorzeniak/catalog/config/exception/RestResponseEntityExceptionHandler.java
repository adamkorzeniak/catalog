package com.adamkorzeniak.catalog.config.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { ParentTaskNotFoundException.class })
	protected ResponseEntity<Object> parentTaskNotFound(RuntimeException ex, WebRequest request) {
		ExceptionResponse bodyOfResponse = new ExceptionResponse("PARENT_NOT_EXISTS", "Parent task not found");
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = { ParentOfItselfException.class })
	protected ResponseEntity<Object> parentOfItself(RuntimeException ex, WebRequest request) {
		ExceptionResponse bodyOfResponse = new ExceptionResponse("X", "Task cannot be parent of itself");
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(value = { IllegalArgumentException.class })
	protected ResponseEntity<Object> badRequest(RuntimeException ex, WebRequest request) {
		ExceptionResponse bodyOfResponse = new ExceptionResponse("X", "Bad Request");
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(value = { DataIntegrityViolationException.class })
	protected ResponseEntity<Object> dataInvalid(RuntimeException ex, WebRequest request) {
		ExceptionResponse bodyOfResponse = new ExceptionResponse("X", "Request data violates database integrity");
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
	}

	@ExceptionHandler(value = { ConstraintViolationException.class })
	protected ResponseEntity<Object> constraintsViolated(RuntimeException ex, WebRequest request) {
		ExceptionResponse bodyOfResponse = new ExceptionResponse("X", "Database constraint have been violated");
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
	}

}