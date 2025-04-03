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

import com.sourav.learning.controller.POJO.PublisherRequest;
import com.sourav.learning.controller.POJO.PublisherResponse;
import com.sourav.learning.entity.Publisher;
import com.sourav.learning.service.PublisherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bookstore/api/publisher")
@RequiredArgsConstructor
public class PublisherController {

	public List<PublisherResponse> convertToPublisherResponses(List<Publisher> publishers) {

		return publishers.stream().map(publisher -> new PublisherResponse(publisher.getId(), publisher.getFirstName(),
				publisher.getLastName(), publisher.getAddress())).collect(Collectors.toList());

	}

	public PublisherResponse convertToPublisherResponse(final Publisher publisher) {

		final PublisherResponse publisherResponse = new PublisherResponse(publisher.getId(), publisher.getFirstName(),
				publisher.getLastName(), publisher.getAddress());
		return publisherResponse;

	}

	public Publisher convertToPublisher(final PublisherRequest publisherRequest) {

		final Publisher publisher = new Publisher(null, publisherRequest.getFirstName(), publisherRequest.getLastName(),
				null);
		return publisher;

	}

	@Autowired
	private PublisherService publisherService;

	@PostMapping("/add")
	private ResponseEntity<PublisherResponse> addPubllisher(@RequestBody final PublisherRequest publisherRequest) {

		final Publisher publisher = convertToPublisher(publisherRequest);
		final Publisher newPublisher = publisherService.save(publisher, publisherRequest.getAddressId());
		final PublisherResponse publisherResponse = convertToPublisherResponse(newPublisher);

		return new ResponseEntity<>(publisherResponse, HttpStatus.CREATED);

	}

	@GetMapping("/getAll")
	private ResponseEntity<List<PublisherResponse>> getAllPublisher() {

		List<Publisher> publishers = publisherService.getAllPublisher();
		List<PublisherResponse> publisherResponses = convertToPublisherResponses(publishers);

		return new ResponseEntity<>(publisherResponses, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	private ResponseEntity<PublisherResponse> getPublisherById(@PathVariable(value = "id") final Long id) {

		Publisher publisher = publisherService.findPublisherById(id);
		PublisherResponse publisherResponse = new PublisherResponse(publisher.getId(), publisher.getFirstName(),
				publisher.getLastName(), publisher.getAddress());

		return new ResponseEntity<>(publisherResponse, HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<PublisherResponse> updatePublisherById(@PathVariable Long id,
			@RequestBody PublisherRequest publisherRequest) {
		Publisher publisher = convertToPublisher(publisherRequest);
		Publisher newPublisher = publisherService.updatePublisher(id, publisher);
		PublisherResponse publisherResponse = convertToPublisherResponse(newPublisher);
		return new ResponseEntity<>(publisherResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePublisher(@PathVariable Long id) {
		try {
			publisherService.deletePublisher(id);
			return ResponseEntity.ok("Publisher with ID " + id + " deleted successfully");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Publisher with ID " + id + " not found: " + e.getMessage());
		}

	}

}
