package com.adamkorzeniak.catalog.person.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adamkorzeniak.catalog.person.model.Person;
import com.adamkorzeniak.catalog.person.repository.PersonRepository;

@Service("personService")
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	public Person create(Person person) {
		return personRepository.save(person);
	}

	public List<Person> findAllPeople() {
		return personRepository.findAll();
	}

	@Override
	public Person findPerson(long personId) {
		return personRepository.getOne(personId);
	}

	@Override
	public Person updatePerson(Person person, long personId) {
		
		Person currentPerson = personRepository.getOne(personId);
		if (currentPerson == null) {
			return null;
		}
		currentPerson.update(person);
		return personRepository.save(currentPerson);
	}
	
	@Override
	public boolean deletePerson(long id) {
		Person person = personRepository.getOne(id);
		if (person == null) {
			return false;
		}
		personRepository.delete(person);
		return true;
	}
}
