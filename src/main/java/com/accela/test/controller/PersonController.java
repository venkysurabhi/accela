package com.accela.test.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accela.test.exception.ResourceNotFoundException;
import com.accela.test.model.Person;
import com.accela.test.service.PersonService;

@RestController
@Validated
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("/persons")
	public List<Person> getAllPersons() {
		return personService.getAllPersons();
	}
	
	@GetMapping("/persons/{id}")
	public Person getPerson(@PathVariable UUID id) throws ResourceNotFoundException {
		return personService.getPerson(id);
	}
	
	@RequestMapping(method = RequestMethod.HEAD, value="/persons")
	public void countPersons(HttpServletResponse response) {
		response.addHeader("X-Total-Count", String.valueOf(personService.countPersons()));
	}
	
	@PostMapping("/persons")
	public void addPerson(@Valid @RequestBody Person person) {
		personService.addPerson(person);
	}
	
	@PutMapping("/persons/{id}")
	public void updatePerson(@Valid @RequestBody Person person, @PathVariable UUID id) throws ResourceNotFoundException {
		personService.updatePerson(person, id);
	}
	
	@DeleteMapping("/persons/{id}")
	public void deletePerson(@PathVariable UUID id) {
		personService.deletePerson(id);
	}
	
}
