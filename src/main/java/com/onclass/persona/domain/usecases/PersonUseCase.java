package com.onclass.persona.domain.usecases;

import com.onclass.persona.domain.api.PersonServicePort;
import com.onclass.persona.domain.exceptions.DuplicatePersonException;
import com.onclass.persona.domain.models.PersonModel;
import com.onclass.persona.domain.models.pagination.DomainPage;
import com.onclass.persona.domain.models.pagination.DomainPageRequest;
import com.onclass.persona.domain.spi.PersonPersistencePort;

public class PersonUseCase implements PersonServicePort {

    private final PersonPersistencePort personPersistencePort;

    public PersonUseCase(PersonPersistencePort personPersistencePort) {
        this.personPersistencePort = personPersistencePort;
    }

    @Override
    public PersonModel create(PersonModel person) {
        PersonModel model = PersonModel.create(person.name(), person.email());

        if (personPersistencePort.existsByEmail(model.email())) {
            throw new DuplicatePersonException(model.email());
        }

        return personPersistencePort.save(model);
    }

    @Override
    public PersonModel findById(Long id) {
        return personPersistencePort.findById(id);
    }

    @Override
    public DomainPage<PersonModel> findAll(DomainPageRequest pageRequest) {
        return personPersistencePort.findAll(pageRequest);
    }
}
