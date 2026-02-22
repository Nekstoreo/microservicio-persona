package com.onclass.persona.domain.usecases;

import com.onclass.persona.domain.exceptions.DuplicatePersonException;
import com.onclass.persona.domain.models.PersonModel;
import com.onclass.persona.domain.spi.PersonPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("PersonUseCase")
class PersonUseCaseTest {

    private PersonPersistencePort personPersistencePort;
    private PersonUseCase personUseCase;

    @BeforeEach
    void setUp() {
        personPersistencePort = mock(PersonPersistencePort.class);
        personUseCase = new PersonUseCase(personPersistencePort);
    }

    @Test
    @DisplayName("debe crear persona exitosamente")
    void shouldCreatePersonSuccessfully() {
        when(personPersistencePort.existsByEmail("ana.vargas@onclass.com")).thenReturn(false);
        when(personPersistencePort.save(any())).thenAnswer(invocation -> {
            PersonModel p = invocation.getArgument(0);
            return PersonModel.of(10L, p.name(), p.email());
        });

        PersonModel model = PersonModel.create("Ana Vargas", "ana.vargas@onclass.com");
        PersonModel result = personUseCase.create(model);

        assertNotNull(result);
        assertEquals(10L, result.id());
        assertEquals("Ana Vargas", result.name());
        assertEquals("ana.vargas@onclass.com", result.email());

        verify(personPersistencePort).existsByEmail("ana.vargas@onclass.com");
        verify(personPersistencePort).save(any());
    }

    @Test
    @DisplayName("debe lanzar cuando email ya existe")
    void shouldThrowWhenEmailAlreadyExists() {
        when(personPersistencePort.existsByEmail("ana.vargas@onclass.com")).thenReturn(true);

        PersonModel model = PersonModel.create("Ana", "ana.vargas@onclass.com");

        assertThrows(DuplicatePersonException.class, () -> personUseCase.create(model));

        verify(personPersistencePort).existsByEmail("ana.vargas@onclass.com");
        verify(personPersistencePort, never()).save(any());
    }

    @Test
    @DisplayName("debe lanzar cuando request inválido")
    void shouldThrowWhenRequestInvalid() {
        assertThrows(com.onclass.persona.domain.exceptions.DomainValidationException.class,
                () -> personUseCase.create(PersonModel.create(" ", " ")));

        verify(personPersistencePort, never()).existsByEmail(any());
        verify(personPersistencePort, never()).save(any());
    }

    @Test
    @DisplayName("debe encontrar persona por id")
    void shouldFindPersonById() {
        PersonModel model = PersonModel.of(1L, "Ana", "ana@test.com");
        when(personPersistencePort.findById(1L)).thenReturn(model);

        PersonModel result = personUseCase.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Ana", result.name());
        assertEquals("ana@test.com", result.email());
        verify(personPersistencePort).findById(1L);
    }

    @Test
    @DisplayName("debe retornar null cuando findById no encuentra")
    void shouldReturnNullWhenFindByIdNotFound() {
        when(personPersistencePort.findById(999L)).thenReturn(null);

        PersonModel result = personUseCase.findById(999L);

        assertNull(result);
        verify(personPersistencePort).findById(999L);
    }
}
