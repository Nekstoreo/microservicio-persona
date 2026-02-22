package com.onclass.persona.application.mappers;

import com.onclass.persona.application.dtos.requests.PersonRequest;
import com.onclass.persona.application.dtos.responses.PersonResponse;
import com.onclass.persona.domain.models.PersonModel;

public final class PersonDtoMapper {

    private PersonDtoMapper() {
    }

    public static PersonModel toModel(PersonRequest request) {
        return PersonModel.create(request.name(), request.email());
    }

    public static PersonResponse toResponse(PersonModel model) {
        return new PersonResponse(model.id(), model.name(), model.email());
    }
}
