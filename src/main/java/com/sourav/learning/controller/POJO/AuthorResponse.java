package com.sourav.learning.controller.POJO;

import com.sourav.learning.entity.Address;

import lombok.Value;

@Value
public class AuthorResponse {

	private final long id;
	private final String firstName;
	private final String lastName;
	private Address address;

}
