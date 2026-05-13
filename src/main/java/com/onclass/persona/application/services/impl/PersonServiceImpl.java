package com.onclass.persona.application.services.impl;

import com.onclass.persona.application.dtos.requests.PersonRequest;
import com.onclass.persona.application.dtos.responses.PersonResponse;
import com.onclass.persona.application.mappers.PersonDtoMapper;
import com.onclass.persona.application.services.PersonService;
import com.onclass.persona.domain.api.PersonServicePort;
import com.onclass.persona.domain.models.PersonModel;
import com.onclass.persona.domain.models.pagination.DomainPage;
import com.onclass.persona.domain.models.pagination.DomainPageRequest;
import lombok.RequiredArgsConstructor;
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
    public DomainPage<PersonResponse> findAll(DomainPageRequest pageRequest) {
        DomainPage<PersonModel> models = personServicePort.findAll(pageRequest);
        return new DomainPage<>(
                models.content().stream().map(PersonDtoMapper::toResponse).toList(),
                models.pageNumber(),
                models.pageSize(),
                models.totalElements(),
                models.totalPages(),
                models.hasNext(),
                models.hasPrevious()
        );
    }
}
