package com.sourav.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourav.learning.controller.POJO.UserRequest;
import com.sourav.learning.controller.POJO.UserResponse;
import com.sourav.learning.entity.User;
import com.sourav.learning.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bookstore/api/user")
@RequiredArgsConstructor
public class UserController {

	User convertToUser(UserRequest userRequest) {
		User user = new User(null, userRequest.getUsername(), userRequest.getPassword());
		return user;
	}

	UserResponse convertToUserResponse(User user) {
		UserResponse userResponse = new UserResponse(user.getId(), user.getUsername());
		return userResponse;
	}

	@Autowired
	private final UserService userService;

	@PostMapping("/addUser")
	public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest) {
		User user = convertToUser(userRequest);
		User newUser = userService.addUser(user);
		UserResponse userResponse = convertToUserResponse(newUser);
		return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
	}

}
