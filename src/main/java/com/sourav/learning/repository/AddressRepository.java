package com.sourav.learning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sourav.learning.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
