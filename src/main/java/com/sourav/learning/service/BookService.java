package com.sourav.learning.service;

import java.util.List;

import com.sourav.learning.entity.Book;

public interface BookService {

	List<Book> getBooks();

	Book findById(Long id);

	void deleteById(Long id);

	void removeById(Long id);

	Book getByTitle(String title);

	List<Book> getByAuthor(String author);

	List<Book> getByPublisher(String publisher);

	List<Book> getByAuthorAndPublisher(String author, String publisher);

	Book addBook(Book book, Long authorId, Long publisherId);

	Book updateById(Long id, Book book, Long authorId, Long publisherId);

}
