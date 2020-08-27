package com.shopping.cart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
/*
 * Exception Service Class
 */
@ControllerAdvice
public class ProductRestExceptionHandler {

	//add an exception handler for CustomerNotFoundException
	@ExceptionHandler
	public ResponseEntity<ProductrErrorResponse> handleException(ProductNotFoundException exc){
		
		//create customerErrorResponse
		
		ProductrErrorResponse error  = new ProductrErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(),System.currentTimeMillis());
			return new ResponseEntity<> (error,HttpStatus.NOT_FOUND);
	}
	
		//add another exception handler to catch any exception (catch all)	
	@ExceptionHandler
	public ResponseEntity<ProductrErrorResponse> handleAnyException(Exception exc){
		
		ProductrErrorResponse error  = new ProductrErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(),System.currentTimeMillis());
			return new ResponseEntity<> (error,HttpStatus.BAD_REQUEST);
	}
}
