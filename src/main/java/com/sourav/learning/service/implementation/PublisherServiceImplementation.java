package com.sourav.learning.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourav.learning.entity.Address;
import com.sourav.learning.entity.Publisher;
import com.sourav.learning.exception.AddressNotFoundException;
import com.sourav.learning.repository.AddressRepository;
import com.sourav.learning.repository.PublisherRepository;
import com.sourav.learning.service.PublisherService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PublisherServiceImplementation implements PublisherService {

	@Autowired
	private final PublisherRepository publisherRepository;

	@Autowired
	private final AddressRepository addressRepository;

	public Publisher save(final Publisher publisher, final Long addressId) {
		Optional<Address> addressOptional = addressRepository.findById(addressId);

		if (addressOptional.isEmpty()) {
			throw new AddressNotFoundException(addressId);
		}
		publisher.setAddress(addressOptional.get());

		final Publisher newPublisher = publisherRepository.save(publisher);
		return newPublisher;
	}

	public List<Publisher> getAllPublisher() {
		return publisherRepository.findAll();
	}

	public Publisher findPublisherById(Long publisherId) {
		final Optional<Publisher> optionalPublisher = publisherRepository.findById(publisherId);

		if (optionalPublisher.isPresent()) {
			final Publisher publisher = optionalPublisher.get();
			return publisher;
		} else {
			System.out.println("Publisher not found!");
			return null;
		}
	}

	public Publisher updatePublisher(final Long id, final Publisher publisher) {
		Optional<Publisher> optionalPublisher = publisherRepository.findById(id);
		Publisher updatedPublisher = new Publisher(optionalPublisher.get().getId(), publisher.getFirstName(),
				publisher.getLastName(), publisher.getAddress());
		return publisherRepository.save(updatedPublisher);

	}

	@Override
	public void deletePublisher(final Long id) {
		publisherRepository.deleteById(id);

	}

}
