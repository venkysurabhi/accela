package com.accela.test.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Person {
	@Id
    @GeneratedValue(generator = "uuidGen")
    @GenericGenerator(name = "uuidGen", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = false, nullable = false)
	public UUID id;
	
	@NotBlank(message = "First name is mandatory")
	public String firstName;
	
	@NotBlank(message = "Last name is mandatory")
	public String lastName;
	
	@OneToMany(targetEntity = Address.class, fetch = FetchType.LAZY, 
			mappedBy="person", cascade = CascadeType.ALL)
	public List<Address> addresses;
	
	public Person() {
		
	}
	
	public Person(String firstName, String lastName, List<Address> addresses) {
    	this.id = UUID.randomUUID();
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.addresses.addAll(addresses);
	}
    
	public List<Address> getAddresses() {
		return List.copyOf(addresses);
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses.clear();
		this.addresses.addAll(addresses);
	}

	public UUID getId() {
		return this.id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
