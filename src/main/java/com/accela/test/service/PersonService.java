package com.accela.test.service;

import java.util.List;
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
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	
	public List<Person> getAllPersons() {
		return personRepository.findAll();
	}
	
	public int countPersons() {
		return getAllPersons().size();
	}
	
	public void addPerson(Person person) {
		personRepository.save(person);
	}
	
	public Person getPerson(UUID id) throws ResourceNotFoundException {
		return personRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
	}
	
	public void updatePerson(Person person, UUID id) throws ResourceNotFoundException {
		Optional<Person> existingPerson = personRepository.findById(id);
		if (existingPerson.isPresent()) {
			personRepository.save(person);
		} else { 
			throw new ResourceNotFoundException("Person not found with id " + id);
		}
	}
	
	public void deletePerson(UUID id) {
		List<Address> addresses = addressRepository.getAddressesByPersonId(id);
		addresses.forEach(deleteAddress -> addressRepository.delete(deleteAddress));
		personRepository.deleteById(id);
	}

}
