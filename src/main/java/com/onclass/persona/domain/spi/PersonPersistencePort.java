package com.onclass.persona.domain.spi;

import com.onclass.persona.domain.models.PersonModel;
import com.onclass.persona.domain.models.pagination.DomainPage;
import com.onclass.persona.domain.models.pagination.DomainPageRequest;

public interface PersonPersistencePort {

    PersonModel findById(Long id);

    PersonModel save(PersonModel person);

    boolean existsByEmail(String email);

    DomainPage<PersonModel> findAll(DomainPageRequest pageRequest);
}
