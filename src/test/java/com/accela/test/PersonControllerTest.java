package com.accela.test;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.accela.test.controller.PersonController;
import com.accela.test.model.Address;
import com.accela.test.model.Person;
import com.accela.test.service.PersonService;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;    

	@MockBean
	private PersonService personService;

	@Test
	public void getAllPersonsTest() throws Exception {
		UUID personId = UUID.randomUUID();
		when(personService.getAllPersons()).thenReturn(
				List.of(new Person(personId, "testFirst", "testLast", List.of(new Address()))));

		RequestBuilder request = MockMvcRequestBuilders
				.get("/persons") 
				.accept(MediaType.APPLICATION_JSON); 

		mockMvc.perform(request)
		.andExpect(status().isOk())
		.andExpect(content().json("[{id:" + personId + ", firstName:testFirst, lastName:testLast}]"))
		.andReturn();  
	}

	@Test
	// Test to make sure persons without first name are not created.
	public void addPersonNegativeTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/persons")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"firstName\":\"\",\"lastName\":\"Accela\"}")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)
		.andExpect(status().isBadRequest())
		.andReturn();
	}

	@Test
	public void addPersonTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/persons")
				.accept(MediaType.APPLICATION_JSON)
				.content("{\"firstName\":\"test\",\"lastName\":\"Accela\"}")
				.contentType(MediaType.APPLICATION_JSON);

		mockMvc.perform(request)
		.andExpect(status().isOk())
		.andReturn();
	}

	@Test
	public void getPersonTest() throws Exception {
		UUID personId = UUID.randomUUID();
		when(personService.getPerson(personId)).thenReturn(
				new Person(personId, "testFirst", "testLast", List.of(new Address())));

		RequestBuilder request = MockMvcRequestBuilders
				.get("/persons/" + personId) 
				.accept(MediaType.APPLICATION_JSON); 

		mockMvc.perform(request)
		.andExpect(status().isOk())
		.andExpect(content().json("{id:" + personId + ", firstName:testFirst, lastName:testLast}"))
		.andReturn();  
	} 

}
