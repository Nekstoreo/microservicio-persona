package com.onclass.persona.infrastructure.configurations;

import com.onclass.persona.domain.api.PersonServicePort;
import com.onclass.persona.domain.spi.PersonPersistencePort;
import com.onclass.persona.domain.usecases.PersonUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public PersonServicePort personServicePort(PersonPersistencePort personPersistencePort) {
        return new PersonUseCase(personPersistencePort);
    }
}
