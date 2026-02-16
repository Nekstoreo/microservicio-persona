package com.onclass.persona.application.port.in;

import com.onclass.persona.domain.model.Person;
import com.onclass.persona.application.usecase.command.CreatePersonCommand;

public interface CreatePersonUseCase {

    Person execute(CreatePersonCommand command);
}
