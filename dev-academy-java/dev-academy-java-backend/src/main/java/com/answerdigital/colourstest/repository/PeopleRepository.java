package com.answerdigital.colourstest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.answerdigital.colourstest.model.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {

}
