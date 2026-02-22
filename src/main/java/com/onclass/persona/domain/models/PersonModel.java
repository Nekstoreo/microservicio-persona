package com.onclass.persona.domain.models;

import com.onclass.persona.domain.constants.DomainConstants;

import java.util.Objects;

public record PersonModel(Long id, String name, String email) {

    public static PersonModel of(Long id, String name, String email) {
        Objects.requireNonNull(id, DomainConstants.PERSON_ID_REQUIRED_MESSAGE);
        Objects.requireNonNull(name, DomainConstants.PERSON_NAME_REQUIRED_MESSAGE);
        Objects.requireNonNull(email, DomainConstants.PERSON_EMAIL_REQUIRED_MESSAGE);
        String normalizedName = name.trim();
        String normalizedEmail = email.trim();
        if (normalizedName.isEmpty() || normalizedEmail.isEmpty()) {
            throw new com.onclass.persona.domain.exceptions.DomainValidationException(DomainConstants.PERSON_NAME_EMAIL_REQUIRED_MESSAGE);
        }
        return new PersonModel(id, normalizedName, normalizedEmail);
    }

    public static PersonModel create(String name, String email) {
        Objects.requireNonNull(name, DomainConstants.PERSON_NAME_REQUIRED_MESSAGE);
        Objects.requireNonNull(email, DomainConstants.PERSON_EMAIL_REQUIRED_MESSAGE);
        String normalizedName = name.trim();
        String normalizedEmail = email.trim();
        if (normalizedName.isEmpty() || normalizedEmail.isEmpty()) {
            throw new com.onclass.persona.domain.exceptions.DomainValidationException(DomainConstants.PERSON_NAME_EMAIL_REQUIRED_MESSAGE);
        }
        return new PersonModel(null, normalizedName, normalizedEmail);
    }
}
