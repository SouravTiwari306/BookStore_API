package com.sourav.learning.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourav.learning.entity.Author;
import com.sourav.learning.entity.Book;
import com.sourav.learning.entity.Publisher;
import com.sourav.learning.repository.BookRepository;
import com.sourav.learning.service.AuthorService;
import com.sourav.learning.service.BookService;
import com.sourav.learning.service.PublisherService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImplementation implements BookService {

	@Autowired
	private final BookRepository bookRepository;

	@Autowired
	private final AuthorService authorService;

	@Autowired
	private final PublisherService publisherService;

	public Book addBook(final Book book, final Long authorId, final Long publisherId) {
		Author author = authorService.findAuthorById(authorId);
		Publisher publisher = publisherService.findPublisherById(publisherId);
		book.setAuthor(author);
		book.setPublisher(publisher);
		return bookRepository.save(book);
	}

	public List<Book> getBooks() {
		return bookRepository.findAll();

	}

	public Book findById(final Long id) {
		Optional<Book> bookOptional = bookRepository.findById(id);
		return bookOptional.get();
	}

	public Book updateById(final Long id, final Book book, final Long authorId, final Long publisherId) {
		final Optional<Book> optionalBook = bookRepository.findById(id);
		final Book existingBook = optionalBook.get();
		Author author = authorService.findAuthorById(authorId);
		Publisher publisher = publisherService.findPublisherById(publisherId);
		book.setAuthor(author);
		book.setPublisher(publisher);
		final Book updatedBook = new Book(existingBook.getId(), book.getTitle(), book.getAuthor(), book.getPublisher(),
				false, book.getCreatedDate(), book.getCreatedBy(), book.getUpdatedDate(), book.getUpdatedBy());
		return bookRepository.save(updatedBook);
	}

	public void deleteById(final Long id) {
		bookRepository.deleteById(id);

	}

	public void removeById(final Long id) {
		bookRepository.deleteById(id);

	}

	public Book getByTitle(final String title) {
		return bookRepository.findByTitle(title);
	}

	public List<Book> getByAuthor(final String author) {
		return bookRepository.findByAuthorFirstName(author);
	}

	public List<Book> getByPublisher(final String publisher) {
		return bookRepository.findByPublisherFirstName(publisher);
	}

	public List<Book> getByAuthorAndPublisher(final String author, final String publisher) {
		return bookRepository.findByAuthorFirstNameAndPublisherFirstName(author, publisher);

	}

}
