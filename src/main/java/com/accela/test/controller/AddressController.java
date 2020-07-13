package com.accela.test.controller;

import java.util.UUID;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.accela.test.exception.ResourceNotFoundException;
import com.accela.test.model.Address;
import com.accela.test.service.AddressService;

@RestController
@Validated
public class AddressController {
	
	@Autowired
	private AddressService addressService;

	@PostMapping("/persons/{personId}/addresses")
	public void addAddress(@Valid @RequestBody Address address, @PathVariable UUID personId) throws ResourceNotFoundException {
		addressService.addAddress(address, personId);
	}
	
	@PutMapping("/persons/{personId}/addresses/{addressId}")
	public void updateAddress(@Valid @RequestBody Address address, @PathVariable UUID addressId) throws ResourceNotFoundException {
		addressService.updateAddress(addressId, address);
	}
	
	@DeleteMapping("/persons/{personId}/addresses/{addressId}")
	public void deleteAddress(@PathVariable UUID personId, @PathVariable UUID addressId) throws ResourceNotFoundException {
		addressService.deleteAddress(personId, addressId);	
	}

}
