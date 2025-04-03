package com.sourav.learning.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sourav.learning.controller.POJO.BookRequest;
import com.sourav.learning.controller.POJO.BookResponse;
import com.sourav.learning.entity.Book;
import com.sourav.learning.service.AuthorService;
import com.sourav.learning.service.BookService;
import com.sourav.learning.service.PublisherService;
import com.sourav.learning.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bookstore/api/book")
@RequiredArgsConstructor
public class BookController {

	private List<BookResponse> convertToBooksResponse(List<Book> books) {

		return books.stream()
				.map(book -> new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getPublisher()))
				.collect(Collectors.toList());
	}

	private Book convertToBook(BookRequest bookRequest) {
		Book book = new Book(null, bookRequest.getTitle(), null, null, false, null, null, null, null);
		return book;
	}

	private BookResponse convertToBookResponse(Book book) {
		BookResponse bookResponse = new BookResponse(book.getId(), book.getTitle(), book.getAuthor(),
				book.getPublisher());
		return bookResponse;
	}

	@Autowired
	private BookService bookService;
	@Autowired
	private UserService userService;
	

	public boolean validateUser(String username, String password) {
		return userService.login(username, password);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<BookResponse>> getBooks(@RequestParam String username, @RequestParam String password) {
		if (validateUser(username, password)) {
			List<Book> books = bookService.getBooks();
			List<BookResponse> booksResponse = convertToBooksResponse(books);

			return new ResponseEntity<>(booksResponse, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("/add")
	public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest bookRequest, @RequestParam String username,
			@RequestParam String password) {
		if (validateUser(username, password)) {
			
			Book book = convertToBook(bookRequest);
			book.setCreatedBy(username);
			book.setCreatedDate(LocalDateTime.now());
			
			Book newBook = bookService.addBook(book, bookRequest.getAuthorId(), bookRequest.getPublisherId());
			BookResponse bookResponse = convertToBookResponse(newBook);
			return new ResponseEntity<>(bookResponse, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookResponse> getBook(@PathVariable Long id, @RequestParam String username,
			@RequestParam String password) {
		if (validateUser(username, password)) {
			Book book = bookService.findById(id);
			BookResponse bookResponse = convertToBookResponse(book);
			return new ResponseEntity<>(bookResponse, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id, @RequestParam String username,
			@RequestParam String password) {
		if (validateUser(username, password)) {
			bookService.deleteById(id);
			return new ResponseEntity<>("Book with id " + id + " deleted Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);

	}

	@PutMapping("/remove/{id}")
	public ResponseEntity<String> remove(@PathVariable Long id, @RequestParam String username,
			@RequestParam String password) {
		if (validateUser(username, password)) {
			bookService.removeById(id);
			return new ResponseEntity<>("Book with id " + id + " removed Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Invalid Credentials", HttpStatus.UNAUTHORIZED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<BookResponse> update(@PathVariable Long id, @RequestBody BookRequest bookRequest,
			@RequestParam String username, @RequestParam String password) {
		if (validateUser(username, password)) {
			Book book = convertToBook(bookRequest);
			book.setUpdatedBy(username);
			book.setUpdatedDate(LocalDateTime.now());
			Book newBook = bookService.updateById(id, book, bookRequest.getAuthorId(), bookRequest.getPublisherId());
			BookResponse bookResponse = convertToBookResponse(newBook);
			return new ResponseEntity<>(bookResponse, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/title")
	public ResponseEntity<BookResponse> getBooksByTitle(@RequestParam String title, @RequestParam String username,
			@RequestParam String password) {
		if (validateUser(username, password)) {
			System.out.print("3");
			Book book = bookService.getByTitle(title);
			System.out.println(book);
			BookResponse bookResponse = convertToBookResponse(book);
			return new ResponseEntity<>(bookResponse, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/author")
	public ResponseEntity<List<BookResponse>> getBookByAuthor(@RequestParam String author,
			@RequestParam String username, @RequestParam String password) {

		if (validateUser(username, password)) {
			List<Book> books = bookService.getByAuthor(author);
			List<BookResponse> booksResponse = convertToBooksResponse(books);
			return new ResponseEntity<>(booksResponse, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/publisher")
	public ResponseEntity<List<BookResponse>> getBookByPublisher(@RequestParam String publisher,
			@RequestParam String username, @RequestParam String password) {
		if (validateUser(username, password)) {
			List<Book> books = bookService.getByPublisher(publisher);
			List<BookResponse> booksResponse = convertToBooksResponse(books);
			return new ResponseEntity<>(booksResponse, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@GetMapping("/authorPublisher")
	public ResponseEntity<List<BookResponse>> getBooksByAuthorAndPublisher(@RequestParam String author,
			@RequestParam String publisher, @RequestParam String username, @RequestParam String password) {
		if (validateUser(username, password)) {
			List<Book> books = bookService.getByAuthorAndPublisher(author, publisher);
			List<BookResponse> booksResponse = convertToBooksResponse(books);
			return new ResponseEntity<>(booksResponse, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
}

}
