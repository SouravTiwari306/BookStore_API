package com.sourav.learning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sourav.learning.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	List<Book> findByAuthorFirstName(String author);

	List<Book> findByPublisherFirstName(String publisher);

	List<Book> findByAuthorFirstNameAndPublisherFirstName(String authorFirstName, String publisherFirstName);

	Book findByTitle(String title);

}
