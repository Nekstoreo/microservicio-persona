package com.onclass.persona.application.services.impl;

import com.onclass.persona.application.dtos.requests.PersonRequest;
import com.onclass.persona.application.dtos.responses.PersonResponse;
import com.onclass.persona.domain.api.PersonServicePort;
import com.onclass.persona.domain.exceptions.DuplicatePersonException;
import com.onclass.persona.domain.models.PersonModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("PersonServiceImpl")
class PersonServiceImplTest {

    private PersonServicePort personServicePort;
    private PersonServiceImpl personServiceImpl;

    @BeforeEach
    void setUp() {
        personServicePort = mock(PersonServicePort.class);
        personServiceImpl = new PersonServiceImpl(personServicePort);
    }

    @Nested
    @DisplayName("create")
    class Create {

        @Test
        @DisplayName("debe crear persona y retornar response")
        void shouldCreateAndReturnResponse() {
            PersonRequest request = new PersonRequest("Ana Vargas", "ana@onclass.com");
            PersonModel createdModel = PersonModel.of(10L, "Ana Vargas", "ana@onclass.com");
            when(personServicePort.create(any())).thenReturn(createdModel);

            PersonResponse result = personServiceImpl.create(request);

            assertNotNull(result);
            assertEquals(10L, result.id());
            assertEquals("Ana Vargas", result.name());
            assertEquals("ana@onclass.com", result.email());
            verify(personServicePort).create(any());
        }

        @Test
        @DisplayName("debe propagar DuplicatePersonException del port")
        void shouldPropagateDuplicatePersonException() {
            PersonRequest request = new PersonRequest("Ana", "ana@test.com");
            when(personServicePort.create(any())).thenThrow(new DuplicatePersonException("ana@test.com"));

            assertThrows(DuplicatePersonException.class, () -> personServiceImpl.create(request));
            verify(personServicePort).create(any());
        }
    }

    @Nested
    @DisplayName("findById")
    class FindById {

        @Test
        @DisplayName("debe retornar PersonResponse cuando existe")
        void shouldReturnResponseWhenExists() {
            PersonModel model = PersonModel.of(1L, "Ana", "ana@test.com");
            when(personServicePort.findById(1L)).thenReturn(model);

            PersonResponse result = personServiceImpl.findById(1L);

            assertNotNull(result);
            assertEquals(1L, result.id());
            assertEquals("Ana", result.name());
            assertEquals("ana@test.com", result.email());
            verify(personServicePort).findById(1L);
        }

        @Test
        @DisplayName("debe retornar null cuando no existe")
        void shouldReturnNullWhenNotFound() {
            when(personServicePort.findById(999L)).thenReturn(null);

            PersonResponse result = personServiceImpl.findById(999L);

            assertNull(result);
            verify(personServicePort).findById(999L);
        }
    }
}
