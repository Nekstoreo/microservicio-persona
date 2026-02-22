package com.onclass.persona.domain.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DomainConstants")
class DomainConstantsTest {

    @Test
    @DisplayName("duplicatePersonMessage debe formatear email correctamente")
    void duplicatePersonMessage_shouldFormatEmail() {
        String result = DomainConstants.duplicatePersonMessage("ana@test.com");

        assertNotNull(result);
        assertTrue(result.contains("ana@test.com"));
        assertTrue(result.contains("already exists"));
    }

    @Test
    @DisplayName("debe exponer constantes de longitud máxima")
    void shouldExposeMaxLengthConstants() {
        assertEquals(150, DomainConstants.PERSON_NAME_MAX_LENGTH);
        assertEquals(150, DomainConstants.PERSON_EMAIL_MAX_LENGTH);
    }

    @Test
    @DisplayName("debe exponer mensajes de validación")
    void shouldExposeValidationMessages() {
        assertNotNull(DomainConstants.PERSON_ID_REQUIRED_MESSAGE);
        assertNotNull(DomainConstants.PERSON_NAME_REQUIRED_MESSAGE);
        assertNotNull(DomainConstants.PERSON_EMAIL_REQUIRED_MESSAGE);
        assertNotNull(DomainConstants.PERSON_NAME_EMAIL_REQUIRED_MESSAGE);
    }
}
