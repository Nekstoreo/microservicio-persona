package com.onclass.persona.domain.spi;

import com.onclass.persona.domain.model.Person;

/**
 * Output port (repository) for Person persistence operations.
 */
public interface PersonRepositoryPort {

    Person findById(Long id);

    Person save(Person person);

    boolean existsByEmail(String email);
}
