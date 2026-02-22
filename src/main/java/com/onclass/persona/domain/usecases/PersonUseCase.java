package com.onclass.persona.domain.usecases;

import com.onclass.persona.domain.api.PersonServicePort;
import com.onclass.persona.domain.exceptions.DuplicatePersonException;
import com.onclass.persona.domain.models.PersonModel;
import com.onclass.persona.domain.spi.PersonPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonUseCase implements PersonServicePort {

    private final PersonPersistencePort personPersistencePort;

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
    public Page<PersonModel> findAll(Pageable pageable) {
        return personPersistencePort.findAll(pageable);
    }
}
