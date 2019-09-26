package com.abc.assignment.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.util.UriComponentsBuilder;

import com.abc.assignment.exceptions.PersonNotFoundException;
import com.abc.assignment.model.Person;
import com.abc.assignment.services.PersonService;

@RestController
@RequestMapping(value="/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping(value = "/create", headers = "Accept=application/json")
	public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person, UriComponentsBuilder ucBuilder) {
		personService.createPerson(person);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucBuilder.path("person/{id}").buildAndExpand(person.getID()).toUri());
		return new ResponseEntity<Person>(person, header, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<? extends Object> getOne(@PathVariable String id) {
		Optional<Person> person = personService.getOne(id);
		
		if (!person.isPresent()) {
			person.orElseThrow(() -> new PersonNotFoundException(id));
		}
		return new ResponseEntity<Person>(person.get(), HttpStatus.OK);
	}

	@GetMapping(value = "/get", headers = "Accept=application/json")
	public List<Person> getAll() {
		return personService.getAll();

	}

	@PutMapping(value="/update/{id}", headers="Accept=application/json")
	public ResponseEntity<Person> updatePerson(@PathVariable String id, @Valid @RequestBody Person newPerson) {
		if (id == null)
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);

		Optional<Person> existingPerson = personService.getOne(id);
		if (!existingPerson.isPresent()) {
			existingPerson.orElseThrow(() -> new PersonNotFoundException(id));
		}
		
		personService.updatePerson(existingPerson.get(), newPerson);
		return new ResponseEntity<Person>(existingPerson.get(), HttpStatus.OK);

	}

	@DeleteMapping(value = "/{id}", headers = "Accept=application/json")
	public ResponseEntity<Person> deletePersonById(@PathVariable("id") String id) {
		Optional<Person> person = personService.getOne(id);
		
		if (!person.isPresent()) {
			person.orElseThrow(() -> new PersonNotFoundException(id));
		}
		personService.deletePersonById(id);
		return new ResponseEntity<Person>(HttpStatus.NO_CONTENT);
	}

}
