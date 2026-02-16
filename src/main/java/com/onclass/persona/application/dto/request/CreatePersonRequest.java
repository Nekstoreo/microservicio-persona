package com.onclass.persona.application.dto.request;

import com.onclass.persona.domain.constants.DomainConstants;
import com.onclass.persona.infrastructure.constants.ApiConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreatePersonRequest(
        @NotBlank(message = ApiConstants.VALIDATION_NAME_REQUIRED)
        @Size(max = DomainConstants.PERSON_NAME_MAX_LENGTH, message = ApiConstants.VALIDATION_NAME_MAX_LENGTH) String name,
        @NotBlank(message = ApiConstants.VALIDATION_EMAIL_REQUIRED)
        @Email(message = ApiConstants.VALIDATION_EMAIL_INVALID)
        @Size(max = DomainConstants.PERSON_EMAIL_MAX_LENGTH, message = ApiConstants.VALIDATION_EMAIL_MAX_LENGTH) String email
) {
} 
