package com.onclass.persona.domain.spi;

import com.onclass.persona.domain.models.PersonModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonPersistencePort {

    PersonModel findById(Long id);

    PersonModel save(PersonModel person);

    boolean existsByEmail(String email);

    Page<PersonModel> findAll(Pageable pageable);
}
