package com.sourav.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sourav.learning.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
