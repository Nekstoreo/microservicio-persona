package com.onclass.persona.domain.api;

import com.onclass.persona.domain.models.PersonModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Input port (API) for Person business operations.
 */
public interface PersonServicePort {

    PersonModel create(PersonModel person);

    PersonModel findById(Long id);

    Page<PersonModel> findAll(Pageable pageable);
}
