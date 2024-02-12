package com.inventory.inventorymanagement.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.inventory.inventorymanagement.dto.ResponseDto;
import com.inventory.inventorymanagement.exception.CategoryNotFoundException;
import com.inventory.inventorymanagement.exception.ProductNotFoundException;

@RestControllerAdvice
public class ExceptionHandler {

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@org.springframework.web.bind.annotation.ExceptionHandler(value = {CategoryNotFoundException.class})
	public ResponseDto CategoryNotFoundHandler(CategoryNotFoundException categoryNotFoundException) {
		return new ResponseDto(404, categoryNotFoundException.getMessage(), categoryNotFoundException.fillInStackTrace().getStackTrace());
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@org.springframework.web.bind.annotation.ExceptionHandler(value = {ProductNotFoundException.class})
	public ResponseDto ProductNotFoundHandler(ProductNotFoundException productNotFoundException) {
		return new ResponseDto(404, productNotFoundException.getMessage(), productNotFoundException.fillInStackTrace().getStackTrace());
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@org.springframework.web.bind.annotation.ExceptionHandler(value = {Exception.class})
	public ResponseDto handleGeneralException(Exception exception) {
		return new ResponseDto(500, exception.getMessage(), exception.getStackTrace());
	}
}
