package com.onclass.persona.infrastructure.output.jpa.repository;

import com.onclass.persona.infrastructure.output.jpa.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJpaRepository extends JpaRepository<PersonEntity, Long> {

    boolean existsByEmail(String email);
}
