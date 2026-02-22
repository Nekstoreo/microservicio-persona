package com.onclass.persona.infrastructure.exceptionshandler;

import com.onclass.persona.domain.exceptions.DomainValidationException;
import com.onclass.persona.domain.exceptions.DuplicatePersonException;
import com.onclass.persona.infrastructure.constants.ApiConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.support.WebExchangeBindException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("GlobalExceptionHandler")
class ControllerAdvisorTest {

    private GlobalExceptionHandler controllerAdvisor;

    @BeforeEach
    void setUp() {
        controllerAdvisor = new GlobalExceptionHandler();
    }

    @Nested
    @DisplayName("handleDomainValidation")
    class HandleDomainValidation {

        @Test
        @DisplayName("debe retornar 400 con mensaje de validación")
        void shouldReturn400WithMessage() {
            DomainValidationException ex = new DomainValidationException("Name and email are required");

            ResponseEntity<ErrorResponse> result = controllerAdvisor.handleDomainValidation(ex);

            assertNotNull(result);
            assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
            assertNotNull(result.getBody());
            assertEquals("Name and email are required", result.getBody().message());
        }
    }

    @Nested
    @DisplayName("handleDuplicate")
    class HandleDuplicate {

        @Test
        @DisplayName("debe retornar 409 con mensaje de duplicado")
        void shouldReturn409WithMessage() {
            DuplicatePersonException ex = new DuplicatePersonException("ana@test.com");

            ResponseEntity<ErrorResponse> result = controllerAdvisor.handleDuplicate(ex);

            assertNotNull(result);
            assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
            assertNotNull(result.getBody());
            assertTrue(result.getBody().message().contains("ana@test.com"));
        }
    }

    @Nested
    @DisplayName("handleBindException")
    class HandleBindException {

        @Test
        @DisplayName("debe retornar 400 con mensaje del FieldError")
        void shouldReturn400WithFieldErrorMessage() {
            org.springframework.validation.FieldError fieldError = new org.springframework.validation.FieldError(
                    "personRequest", "name", "Name is required"
            );
            WebExchangeBindException ex = mock(WebExchangeBindException.class);
            when(ex.getFieldError()).thenReturn(fieldError);

            ResponseEntity<ErrorResponse> result = controllerAdvisor.handleBindException(ex);

            assertNotNull(result);
            assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
            assertNotNull(result.getBody());
            assertEquals("Name is required", result.getBody().message());
        }

        @Test
        @DisplayName("debe retornar mensaje por defecto cuando fieldError es null")
        void shouldReturnDefaultMessageWhenFieldErrorIsNull() {
            WebExchangeBindException ex = mock(WebExchangeBindException.class);
            when(ex.getFieldError()).thenReturn(null);

            ResponseEntity<ErrorResponse> result = controllerAdvisor.handleBindException(ex);

            assertNotNull(result);
            assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
            assertNotNull(result.getBody());
            assertEquals(ApiConstants.DEFAULT_INVALID_REQUEST_MESSAGE, result.getBody().message());
        }
    }
}
