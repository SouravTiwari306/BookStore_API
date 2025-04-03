package com.sourav.learning.controller.POJO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class AddressRequest {

	@NotNull
	@Size(min = 5, max = 50)
	private final String line1;

	@Size(min = 5, max = 50)
	private final String line2;

	@NotNull
	@Size(min = 5, max = 20)
	private final String city;

	@NotNull
	@Pattern(regexp = "/^[0-9]{6}$")
	private final String pincode;

	@NotNull
	@Pattern(regexp = "/^[0-9]{6}$")
	private final String mobileNumber;
}
