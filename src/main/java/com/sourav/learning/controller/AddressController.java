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

import com.sourav.learning.controller.POJO.AddressRequest;
import com.sourav.learning.controller.POJO.AddressResponse;
import com.sourav.learning.entity.Address;
import com.sourav.learning.service.AddressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bookstore/api/address")
@RequiredArgsConstructor
public class AddressController {

	public List<AddressResponse> convertToAddressResponses(List<Address> addresses) {
		return addresses.stream()
				.map(address -> new AddressResponse(address.getId(), address.getLine1(), address.getLine2(),
						address.getCity(), address.getPincode(), address.getMobileNumber()))
				.collect(Collectors.toList());
	}

	public AddressResponse convertToAddressResponse(Address address) {
		final AddressResponse addressResponse = new AddressResponse(address.getId(), address.getLine1(),
				address.getLine2(), address.getCity(), address.getPincode(), address.getMobileNumber());

		return addressResponse;

	}

	public Address convertToAddress(AddressRequest addressRequest) {
		final Address address = new Address(null, addressRequest.getLine1(), addressRequest.getLine2(),
				addressRequest.getCity(), addressRequest.getPincode(), addressRequest.getMobileNumber());
		return address;

	}

	@Autowired
	private final AddressService addressService;

	@PostMapping("/add")
	public ResponseEntity<AddressResponse> addAddress(@RequestBody final AddressRequest addressRequest) {

		final Address address = convertToAddress(addressRequest);

		final Address newAddress = addressService.createAddress(address);

		final AddressResponse addressResponse = convertToAddressResponse(newAddress);

		return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);

	}

	@GetMapping("/getAll")
	public ResponseEntity<List<AddressResponse>> getAllAddresses() {
		final List<Address> addresses = addressService.findAllAddress();
		List<AddressResponse> addressResponses = convertToAddressResponses(addresses);

		return new ResponseEntity<>(addressResponses, HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<AddressResponse> getAddressById(@PathVariable final Long id) {
		final Address address = addressService.findAddressById(id);
		final AddressResponse addressResponse = convertToAddressResponse(address);
		return new ResponseEntity<>(addressResponse, HttpStatus.OK);

	}

	@PutMapping("/{id}")
	public ResponseEntity<AddressResponse> updateAddress(@PathVariable final Long id,
			@RequestBody final AddressRequest addressRequest) {
		final Address address = convertToAddress(addressRequest);

		final Address newAddress = addressService.updateAddress(id, address);

		final AddressResponse addressResponse = convertToAddressResponse(newAddress);

		return new ResponseEntity<>(addressResponse, HttpStatus.CREATED);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAddress(@PathVariable final Long id) {
		try {
			addressService.deleteAddress(id);
			return ResponseEntity.ok("Address with ID " + id + " deleted successfully");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Address with ID " + id + " not found: " + e.getMessage());
		}
	}

}
