package com.onclass.persona.domain.exceptions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DuplicatePersonException")
class DuplicatePersonExceptionTest {

    @Test
    @DisplayName("debe crear excepción con mensaje formateado")
    void shouldCreateWithFormattedMessage() {
        DuplicatePersonException ex = new DuplicatePersonException("ana@test.com");

        assertNotNull(ex.getMessage());
        assertTrue(ex.getMessage().contains("ana@test.com"));
        assertTrue(ex.getMessage().contains("already exists"));
        assertNull(ex.getCause());
    }

    @Test
    @DisplayName("debe ser instancia de RuntimeException")
    void shouldBeRuntimeException() {
        DuplicatePersonException ex = new DuplicatePersonException("test@mail.com");

        assertInstanceOf(RuntimeException.class, ex);
    }
}
