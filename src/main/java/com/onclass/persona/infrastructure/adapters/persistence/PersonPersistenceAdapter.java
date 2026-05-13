package com.onclass.persona.infrastructure.adapters.persistence;

import com.onclass.persona.domain.models.PersonModel;
import com.onclass.persona.domain.models.pagination.DomainPage;
import com.onclass.persona.domain.models.pagination.DomainPageRequest;
import com.onclass.persona.domain.spi.PersonPersistencePort;
import com.onclass.persona.infrastructure.entities.PersonEntity;
import com.onclass.persona.infrastructure.mappers.PersonEntityMapper;
import com.onclass.persona.infrastructure.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public DomainPage<PersonModel> findAll(DomainPageRequest pageRequest) {
        Sort.Direction direction = "desc".equalsIgnoreCase(pageRequest.sortDirection())
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(pageRequest.page(), pageRequest.size(), Sort.by(direction, pageRequest.sortBy()));
        Page<PersonEntity> entities = personRepository.findAll(pageable);
        return new DomainPage<>(
                entities.getContent().stream().map(PersonEntityMapper::toModel).toList(),
                entities.getNumber(),
                entities.getSize(),
                entities.getTotalElements(),
                entities.getTotalPages(),
                entities.hasNext(),
                entities.hasPrevious()
        );
    }
}
