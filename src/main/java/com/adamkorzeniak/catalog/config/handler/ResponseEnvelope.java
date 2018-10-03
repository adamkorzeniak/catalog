//package com.adamkorzeniak.catalog.config.handler;
//
//import com.adamkorzeniak.catalog.config.exception.ExceptionResponse;
//
//public class ResponseEnvelope {
//	
//	private Object data = null;
//	private ExceptionResponse error = null;
//	
//	private ResponseEnvelope(Object data, ExceptionResponse error) {
//		this.data = data;
//		this.error = error;
//	}
//	
//	public Object getData() {
//		return data;
//	}
//	
//	public ExceptionResponse getError() {
//		return error;
//	}
//
//	public static Object errorInstance(ExceptionResponse body) {
//		return new ResponseEnvelope(null, body);
//	}
//
//	public static Object successInstance(Object body) {
//		return new ResponseEnvelope(body, null);
//	}
//
//}
