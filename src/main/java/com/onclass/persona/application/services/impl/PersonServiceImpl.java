package com.onclass.persona.application.services.impl;

import com.onclass.persona.application.dtos.requests.PersonRequest;
import com.onclass.persona.application.dtos.responses.PersonResponse;
import com.onclass.persona.application.mappers.PersonDtoMapper;
import com.onclass.persona.application.services.PersonService;
import com.onclass.persona.domain.api.PersonServicePort;
import com.onclass.persona.domain.models.PersonModel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonServicePort personServicePort;

    @Override
    public PersonResponse create(PersonRequest request) {
        PersonModel model = PersonDtoMapper.toModel(request);
        PersonModel created = personServicePort.create(model);
        return PersonDtoMapper.toResponse(created);
    }

    @Override
    public PersonResponse findById(Long id) {
        PersonModel model = personServicePort.findById(id);
        return model != null ? PersonDtoMapper.toResponse(model) : null;
    }

    @Override
    public Page<PersonResponse> findAll(Pageable pageable) {
        Page<PersonModel> models = personServicePort.findAll(pageable);
        return models.map(PersonDtoMapper::toResponse);
    }
}
