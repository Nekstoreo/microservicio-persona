package com.onclass.persona.infrastructure.output.jpa.adapter;

import com.onclass.persona.domain.model.Person;
import com.onclass.persona.domain.spi.PersonRepositoryPort;
import com.onclass.persona.infrastructure.output.jpa.entity.PersonEntity;
import com.onclass.persona.infrastructure.output.jpa.repository.PersonJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonJpaAdapter implements PersonRepositoryPort {

    private final PersonJpaRepository personJpaRepository;

    @Override
    public Person findById(Long id) {
        Optional<PersonEntity> entity = personJpaRepository.findById(id);
        return entity.map(this::toDomain).orElse(null);
    }

    @Override
    public Person save(Person person) {
        PersonEntity entity = toEntity(person);
        PersonEntity saved = personJpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public boolean existsByEmail(String email) {
        return personJpaRepository.existsByEmail(email);
    }

    private Person toDomain(PersonEntity e) {
        return Person.of(e.getId(), e.getName(), e.getEmail());
    }

    private PersonEntity toEntity(Person p) {
        PersonEntity e = new PersonEntity();
        e.setId(p.id());
        e.setName(p.name());
        e.setEmail(p.email());
        return e;
    }
}
