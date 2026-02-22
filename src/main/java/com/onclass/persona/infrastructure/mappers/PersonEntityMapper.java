package com.onclass.persona.infrastructure.mappers;

import com.onclass.persona.domain.models.PersonModel;
import com.onclass.persona.infrastructure.entities.PersonEntity;

public final class PersonEntityMapper {

    private PersonEntityMapper() {
    }

    public static PersonModel toModel(PersonEntity entity) {
        return PersonModel.of(entity.getId(), entity.getName(), entity.getEmail());
    }

    public static PersonEntity toEntity(PersonModel model) {
        PersonEntity entity = new PersonEntity();
        entity.setId(model.id());
        entity.setName(model.name());
        entity.setEmail(model.email());
        return entity;
    }
}
