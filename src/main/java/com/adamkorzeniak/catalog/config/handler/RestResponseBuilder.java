package com.adamkorzeniak.catalog.config.handler;
//package com.adamkorzeniak.catalog.config.handler;
//
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.http.server.ServerHttpResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
//
//import com.adamkorzeniak.catalog.config.exception.ExceptionResponse;
//
//import org.springframework.core.MethodParameter;
//
//@ControllerAdvice
//public class RestResponseBuilder implements ResponseBodyAdvice<Object> {
//
//	@Override
//	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
//		return true;
//	}
//
//	@Override
//	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
//			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
//			ServerHttpResponse response) {
//		if (body instanceof ExceptionResponse) {
//			return ResponseEnvelope.errorInstance((ExceptionResponse)body);
//		}
//		return ResponseEnvelope.successInstance(body);
//	}
//
//}