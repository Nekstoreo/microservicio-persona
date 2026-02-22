package com.onclass.persona.application.services;

import com.onclass.persona.application.dtos.requests.PersonRequest;
import com.onclass.persona.application.dtos.responses.PersonResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService {

    PersonResponse create(PersonRequest request);

    PersonResponse findById(Long id);

    Page<PersonResponse> findAll(Pageable pageable);
}
