package com.adamkorzeniak.catalog.config.exception;

public class ExceptionResponse {

	private String message;
	private String title;
	
	public ExceptionResponse(String title, String message) {
		this.message = message;
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public String getTitle() {
		return title;
	}
}
