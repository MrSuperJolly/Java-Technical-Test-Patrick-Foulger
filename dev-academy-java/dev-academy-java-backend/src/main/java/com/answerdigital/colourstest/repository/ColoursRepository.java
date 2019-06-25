package com.answerdigital.colourstest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.answerdigital.colourstest.model.Colour;

@Repository
public interface ColoursRepository extends JpaRepository<Colour, Long> {

}
