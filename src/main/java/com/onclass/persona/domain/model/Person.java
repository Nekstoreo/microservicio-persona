package com.onclass.persona.domain.model;

import com.onclass.persona.domain.constants.DomainConstants;
import java.util.Objects;

public record Person(Long id, String name, String email) {

    public static Person of(Long id, String name, String email) {
        Objects.requireNonNull(id, DomainConstants.PERSON_ID_REQUIRED_MESSAGE);
        Objects.requireNonNull(name, DomainConstants.PERSON_NAME_REQUIRED_MESSAGE);
        Objects.requireNonNull(email, DomainConstants.PERSON_EMAIL_REQUIRED_MESSAGE);
        String normalizedName = name.trim();
        String normalizedEmail = email.trim();
        if (normalizedName.isEmpty() || normalizedEmail.isEmpty()) {
            throw new com.onclass.persona.domain.exception.DomainValidationException(DomainConstants.PERSON_NAME_EMAIL_REQUIRED_MESSAGE);
        }
        return new Person(id, normalizedName, normalizedEmail);
    }

    public static Person create(String name, String email) {
        Objects.requireNonNull(name, DomainConstants.PERSON_NAME_REQUIRED_MESSAGE);
        Objects.requireNonNull(email, DomainConstants.PERSON_EMAIL_REQUIRED_MESSAGE);
        String normalizedName = name.trim();
        String normalizedEmail = email.trim();
        if (normalizedName.isEmpty() || normalizedEmail.isEmpty()) {
            throw new com.onclass.persona.domain.exception.DomainValidationException(DomainConstants.PERSON_NAME_EMAIL_REQUIRED_MESSAGE);
        }
        return new Person(null, normalizedName, normalizedEmail);
    }
}
