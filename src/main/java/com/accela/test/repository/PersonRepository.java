package com.accela.test.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accela.test.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

}
