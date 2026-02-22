package com.onclass.persona.application.dtos.requests;

import com.onclass.persona.domain.constants.DomainConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PersonRequest(
        @NotBlank(message = "Name is required")
        @Size(max = DomainConstants.PERSON_NAME_MAX_LENGTH, message = "Name must be at most 150 characters") String name,
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be a valid email")
        @Size(max = DomainConstants.PERSON_EMAIL_MAX_LENGTH, message = "Email must be at most 150 characters") String email
) {
}
