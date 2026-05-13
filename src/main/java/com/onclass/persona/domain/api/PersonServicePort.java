package com.onclass.persona.domain.api;

import com.onclass.persona.domain.models.PersonModel;
import com.onclass.persona.domain.models.pagination.DomainPage;
import com.onclass.persona.domain.models.pagination.DomainPageRequest;

/**
 * Input port (API) for Person business operations.
 */
public interface PersonServicePort {

    PersonModel create(PersonModel person);

    PersonModel findById(Long id);

    DomainPage<PersonModel> findAll(DomainPageRequest pageRequest);
}
