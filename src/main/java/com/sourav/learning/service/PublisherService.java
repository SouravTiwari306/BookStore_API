package com.sourav.learning.service;

import java.util.List;

import com.sourav.learning.entity.Publisher;

public interface PublisherService {

	Publisher save(Publisher publisher, Long addressId);

	List<Publisher> getAllPublisher();

	Publisher updatePublisher(Long id, Publisher publisher);

	void deletePublisher(Long id);

	Publisher findPublisherById(Long publisherId);

}
