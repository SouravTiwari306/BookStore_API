package com.sourav.learning.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.sourav.learning.entity.Address;
import com.sourav.learning.exception.AddressServiceException;
import com.sourav.learning.repository.AddressRepository;
import com.sourav.learning.service.AddressService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressServiceImplementation implements AddressService {

	@Autowired
	private final AddressRepository addressRepository;

	public Address createAddress(Address address) {
		
		try{
			return addressRepository.save(address);
		}catch(DataAccessException e ) {
			throw new AddressServiceException("Failed to save the address. Please try again.", e);
		}
	}

	public List<Address> findAllAddress() {
		return addressRepository.findAll();
	}

	public Address findAddressById(Long id) {
		Optional<Address> optionalAddress = addressRepository.findById(id);
		if (optionalAddress.isPresent()) {
			Address address = optionalAddress.get();
			return address;
		} else {
			System.out.println("Address not found!");
			return null;
		}
	}

	public Address updateAddress(Long id, Address address) {
		Address existingAddress = addressRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
		Address updatedAddress = new Address(existingAddress.getId(), address.getLine1(), address.getLine2(),
				address.getCity(), address.getPincode(), address.getMobileNumber());
		return addressRepository.save(updatedAddress);
	}

	public void deleteAddress(Long id) {
		addressRepository.deleteById(id);
	}

}
