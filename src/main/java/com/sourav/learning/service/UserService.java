package com.sourav.learning.service;

import com.sourav.learning.entity.User;

public interface UserService {

	User addUser(User user);

	boolean login(String username, String password);

}
