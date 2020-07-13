package com.accela.test.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accela.test.exception.ResourceNotFoundException;
import com.accela.test.model.Address;
import com.accela.test.model.Person;
import com.accela.test.repository.AddressRepository;
import com.accela.test.repository.PersonRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	public void addAddress(Address address, UUID personId) throws ResourceNotFoundException {
		Optional<Person> person = personRepository.findById(personId);
		if (person.isPresent()) {
			address.setPerson(person.get());
			addressRepository.save(address);
		} else {
			throw new ResourceNotFoundException("No person found with id " + personId
					+ " to add the address");
		}
	}
	
	public void deleteAddress(UUID personId, UUID addressId) throws ResourceNotFoundException {
		Optional<Address> addressToDelete = addressRepository.findById(addressId);
		if (addressToDelete.isPresent()) {
			addressRepository.delete(addressToDelete.get());
		} else {
			throw new ResourceNotFoundException("Address not found with id " + addressId
					+ " and personId " + personId);
		}
	}
	
	public void updateAddress(UUID addressId, Address address) throws ResourceNotFoundException {
		Optional<Address> existingAddress = addressRepository.findById(addressId);
		if (existingAddress.isPresent()) {
		    address.setPerson(existingAddress.get().getPerson());
			addressRepository.save(address);
		} else { 
			throw new ResourceNotFoundException("Address not found with id " + addressId);
		}
	}

}
