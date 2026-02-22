package com.onclass.persona.infrastructure.adapters.persistence;

import com.onclass.persona.domain.models.PersonModel;
import com.onclass.persona.domain.spi.PersonPersistencePort;
import com.onclass.persona.infrastructure.entities.PersonEntity;
import com.onclass.persona.infrastructure.mappers.PersonEntityMapper;
import com.onclass.persona.infrastructure.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonPersistenceAdapter implements PersonPersistencePort {

    private final PersonRepository personRepository;

    @Override
    public PersonModel findById(Long id) {
        Optional<PersonEntity> entity = personRepository.findById(id);
        return entity.map(PersonEntityMapper::toModel).orElse(null);
    }

    @Override
    public PersonModel save(PersonModel model) {
        PersonEntity entity = PersonEntityMapper.toEntity(model);
        PersonEntity saved = personRepository.save(entity);
        return PersonEntityMapper.toModel(saved);
    }

    @Override
    public boolean existsByEmail(String email) {
        return personRepository.existsByEmail(email);
    }

    @Override
    public Page<PersonModel> findAll(Pageable pageable) {
        Page<PersonEntity> entities = personRepository.findAll(pageable);
        return entities.map(PersonEntityMapper::toModel);
    }
}
