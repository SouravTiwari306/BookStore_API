package com.sourav.learning.controller.POJO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Value;

@Value
public class PublisherRequest {

	@NotNull
	@Pattern(regexp = "^[a-zA-z]{5,50}$")
	private String firstName;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z]{5,50}$")
	private String lastName;

	@NotNull
	@Positive
	private Long addressId;

}
