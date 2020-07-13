package com.accela.test.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accela.test.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
	
	List<Address> getAddressesByPersonId(UUID personId);
	
}
