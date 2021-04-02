package com.jg.transactional.repository;

import com.jg.transactional.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

    void deleteByFirstName(final String firstName);

    Person findByFirstName(final String firstName);
}
