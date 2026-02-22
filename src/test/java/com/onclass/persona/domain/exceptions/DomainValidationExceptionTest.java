package com.onclass.persona.domain.exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DomainValidationException")
class DomainValidationExceptionTest {

    @Test
    @DisplayName("debe crear excepción con mensaje")
    void shouldCreateWithMessage() {
        String message = "Validation failed";
        DomainValidationException ex = new DomainValidationException(message);

        assertEquals(message, ex.getMessage());
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("debe ser instancia de RuntimeException")
    void shouldBeRuntimeException() {
        DomainValidationException ex = new DomainValidationException("test");

        assertInstanceOf(RuntimeException.class, ex);
    }
}
