package com.sourav.learning.service;

import java.util.List;

import com.sourav.learning.entity.Author;

public interface AuthorService {

	Author createAuthor(Author author, Long addressId);

	List<Author> findAllAuthors();

	Author findAuthorById(Long id);

	Author updateAuthor(Long id, Author author);

	void deleteAddress(Long id);

}
