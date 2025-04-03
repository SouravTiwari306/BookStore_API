package com.sourav.learning.controller.POJO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class UserRequest {

	@NotNull
	@Size(min = 5, max = 50)
	private final String username;
	@NotNull
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$", message = "Password must be 8-20 characters long, with at least one uppercase letter, one lowercase letter, one number, and one special character.")
	private String password;

}
