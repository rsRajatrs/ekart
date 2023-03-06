package com.ekartv2.exceptions;

public class ErrorMessageException extends RuntimeException{
	
	String message;

	public ErrorMessageException(String message) {
		
		super(message);
		this.message = message;
	}
	
	

}
