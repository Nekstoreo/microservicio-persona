package com.onclass.persona.infrastructure.repositories;

import com.onclass.persona.infrastructure.entities.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    boolean existsByEmail(String email);
}
