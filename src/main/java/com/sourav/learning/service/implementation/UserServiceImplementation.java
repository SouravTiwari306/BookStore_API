package com.sourav.learning.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourav.learning.entity.User;
import com.sourav.learning.repository.UserRepository;
import com.sourav.learning.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

	@Autowired
	private final UserRepository userRepository;

	public User addUser(final User user) {

		return userRepository.save(user);

	}

	public boolean login(final String username, final String password) {
		User user = userRepository.findByUsername(username);
            
		if (user != null && user.getPassword().equals(password)) {
			
			return true;
		}
		return false;

	}

}
