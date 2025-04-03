package com.sourav.learning.service;

import java.util.List;

import com.sourav.learning.entity.Address;

public interface AddressService {

	Address createAddress(final Address address);

	List<Address> findAllAddress();

	Address findAddressById(Long id);

	Address updateAddress(Long id, Address address);

	void deleteAddress(Long id);

}
