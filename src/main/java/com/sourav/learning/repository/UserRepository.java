package com.sourav.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sourav.learning.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	   User findByUsername(final String username);

}
