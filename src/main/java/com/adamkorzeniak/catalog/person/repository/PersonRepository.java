package com.adamkorzeniak.catalog.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adamkorzeniak.catalog.person.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
