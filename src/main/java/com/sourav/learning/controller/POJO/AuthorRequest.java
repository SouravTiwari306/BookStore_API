package com.sourav.learning.controller.POJO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Value;

@Value
public class AuthorRequest {

	@NotNull
	@Pattern(regexp = "^[a-zA-Z]{5,50}$")
	private final String firstName;

	@NotNull
	@Pattern(regexp = "^[a-zA-Z]{5,50}$")
	private final String lastName;

	@NotNull
	@Positive
	private Long addressId;

}
