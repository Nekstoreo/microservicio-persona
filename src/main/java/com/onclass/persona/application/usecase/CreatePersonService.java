package com.onclass.persona.application.usecase;

import com.onclass.persona.application.port.in.CreatePersonUseCase;
import com.onclass.persona.application.usecase.command.CreatePersonCommand;
import com.onclass.persona.domain.exception.DuplicatePersonException;
import com.onclass.persona.domain.model.Person;
import com.onclass.persona.domain.spi.PersonRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePersonService implements CreatePersonUseCase {

    private final PersonRepositoryPort personProvider;

    @Override
    public Person execute(CreatePersonCommand command) {
        Person person = Person.create(command.name(), command.email());

        if (personProvider.existsByEmail(person.email())) {
            throw new DuplicatePersonException(person.email());
        }

        return personProvider.save(person);
    }
}
