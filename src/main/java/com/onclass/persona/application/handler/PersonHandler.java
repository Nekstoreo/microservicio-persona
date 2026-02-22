package com.onclass.persona.application.handler;

import com.onclass.persona.application.dto.request.CreatePersonRequest;
import com.onclass.persona.application.dto.response.PersonResponse;
import com.onclass.persona.application.port.in.CreatePersonUseCase;
import com.onclass.persona.domain.spi.PersonRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonHandler {

    private final PersonRepositoryPort personProvider;
    private final CreatePersonUseCase createPersonUseCase;

    public PersonResponse findById(Long id) {
        var person = personProvider.findById(id);
        if (person == null) {
            return null;
        }
        return new PersonResponse(person.id(), person.name(), person.email());
    }

    public PersonResponse create(CreatePersonRequest request) {
        var person = createPersonUseCase.execute(new com.onclass.persona.application.usecase.command.CreatePersonCommand(request.name(), request.email()));
        return new PersonResponse(person.id(), person.name(), person.email());
    }
}
