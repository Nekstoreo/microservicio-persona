package com.onclass.persona.application.services;

import com.onclass.persona.application.dtos.requests.PersonRequest;
import com.onclass.persona.application.dtos.responses.PersonResponse;
import com.onclass.persona.domain.models.pagination.DomainPage;
import com.onclass.persona.domain.models.pagination.DomainPageRequest;

public interface PersonService {

    PersonResponse create(PersonRequest request);

    PersonResponse findById(Long id);

    DomainPage<PersonResponse> findAll(DomainPageRequest pageRequest);
}
