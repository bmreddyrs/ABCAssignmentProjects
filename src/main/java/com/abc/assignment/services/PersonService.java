package com.abc.assignment.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abc.assignment.model.Person;
import com.abc.assignment.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personDao;

	@Transactional
	public void createPerson(Person person) {
		personDao.save(person);
	}
	
	@Transactional
	public Optional<Person> getOne(String ID) {
		return personDao.findById(ID);
	}
	
	@Transactional
	public List<Person> getAll() {
		return personDao.findAll();
	}
	
	@Transactional
	public void updatePerson(Person existingPerson, Person newPerson ) {
		
		existingPerson.setFirstName(newPerson.getFirstName());
		existingPerson.setLastName(newPerson.getLastName());
		existingPerson.setBirthDate(newPerson.getBirthDate());
		personDao.save(existingPerson);
	}
	
	@Transactional
	public void deletePersonById(String id) {
		personDao.deleteById(id);
	}
		
	
}
