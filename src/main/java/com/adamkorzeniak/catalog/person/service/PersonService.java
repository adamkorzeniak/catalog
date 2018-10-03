package com.adamkorzeniak.catalog.person.service;

import java.util.List;

import com.adamkorzeniak.catalog.person.model.Person;

public interface PersonService {

	Person create(Person person);

	List<Person> findAllPeople();

	Person findPerson(long personId);

	Person updatePerson(Person person, long id);

	boolean deletePerson(long id);
}
