package com.sourav.learning.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourav.learning.entity.Address;
import com.sourav.learning.entity.Author;
import com.sourav.learning.exception.AddressNotFoundException;
import com.sourav.learning.repository.AddressRepository;
import com.sourav.learning.repository.AuthorRepository;
import com.sourav.learning.service.AuthorService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImplementation implements AuthorService {

	@Autowired
	private final AuthorRepository authorRepository;

	@Autowired
	private final AddressRepository addressRepository;

	public Author createAuthor(final Author author, final Long addressId) {

		final Optional<Address> addressOptional = addressRepository.findById(addressId);

		if (addressOptional.isEmpty()) {
			throw new AddressNotFoundException(addressId);
		}
		author.setAddress(addressOptional.get());

		final Author newAuthor = authorRepository.save(author);
		return newAuthor;
	}

	public List<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	public Author findAuthorById(final Long id) {
		final Optional<Author> optionalAuthor = authorRepository.findById(id);
		if (optionalAuthor.isPresent()) {
			Author author = optionalAuthor.get();
			return author;
		} else {
			System.out.println("Address not found!");
			return null;
		}
	}

	public Author updateAuthor(final Long id, final Author author) {
		final Author existingAuthor = authorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
		final Author updatedAuthor = new Author(existingAuthor.getId(), author.getFirstName(), author.getLastName(),
				author.getAddress());
		return authorRepository.save(updatedAuthor);
	}

	public void deleteAddress(final Long id) {
		authorRepository.deleteById(id);

	}

}
