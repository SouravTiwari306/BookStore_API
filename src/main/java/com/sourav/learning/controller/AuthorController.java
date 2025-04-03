package com.sourav.learning.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.sourav.learning.controller.POJO.AuthorRequest;
import com.sourav.learning.controller.POJO.AuthorResponse;
import com.sourav.learning.entity.Author;
import com.sourav.learning.service.AuthorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bookstore/api/author")
@RequiredArgsConstructor
public class AuthorController {

	public List<AuthorResponse> convertToAuthorResponses(List<Author> authors) {
		return authors.stream().map(author -> new AuthorResponse(author.getId(), author.getFirstName(),
				author.getLastName(), author.getAddress())).collect(Collectors.toList());
	}

	public AuthorResponse convertToAuthorResponse(Author author) {
		final AuthorResponse authorResponse = new AuthorResponse(author.getId(), author.getFirstName(),
				author.getLastName(), author.getAddress());

		return authorResponse;
	}

	public Author convertToAuthor(AuthorRequest authorRequest) {
		final Author author = new Author(null, authorRequest.getFirstName(), authorRequest.getLastName(), null);
		return author;
	}

	@Autowired
	private AuthorService authorService;

	@PostMapping("/add")
	public ResponseEntity<AuthorResponse> addAuthor(@RequestBody AuthorRequest authorRequest) {

		final Author author = convertToAuthor(authorRequest);
		final Author newAuthor = authorService.createAuthor(author, authorRequest.getAddressId());
		final AuthorResponse authorReponse = convertToAuthorResponse(newAuthor);

		return new ResponseEntity<>(authorReponse, HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
		List<Author> authors = authorService.findAllAuthors();
		List<AuthorResponse> authorResponse = convertToAuthorResponses(authors);
		return new ResponseEntity<>(authorResponse, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id) {
		Author author = authorService.findAuthorById(id);
		AuthorResponse authorResponse = convertToAuthorResponse(author);
		return new ResponseEntity<>(authorResponse, HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<AuthorResponse> updateAuthorById(@PathVariable Long id,
			@RequestBody AuthorRequest authorRequest) {
		Author author = convertToAuthor(authorRequest);
		Author newAuthor = authorService.updateAuthor(id, author);
		AuthorResponse authorResponse = convertToAuthorResponse(newAuthor);
		return new ResponseEntity<>(authorResponse, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
		try {
			authorService.deleteAddress(id);
			return ResponseEntity.ok("Author with ID " + id + " deleted successfully");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Author with ID " + id + " not found: " + e.getMessage());
		}

	}

}
