package com.sourav.learning.controller.POJO;

import lombok.Value;

@Value
public class AddressResponse {
	private final long id;
	private final String line1;
	private final String line2;
	private final String city;
	private final String Pincode;
	private final String mobileNumber;
}
