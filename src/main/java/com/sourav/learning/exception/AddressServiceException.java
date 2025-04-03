package com.sourav.learning.exception;

public class AddressServiceException extends RuntimeException{
	
	public AddressServiceException(String e) {
		super(e);
	}
	  public AddressServiceException(String message, Throwable cause) {
	        super(message, cause);
	    }

}
