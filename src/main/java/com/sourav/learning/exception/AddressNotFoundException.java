package com.sourav.learning.exception;

public class AddressNotFoundException extends RuntimeException {

	public AddressNotFoundException(final Long addressId) {
		super("Address not found with ID: " + addressId);
	}
}
