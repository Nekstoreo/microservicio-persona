package com.onclass.persona.application.usecase;

import com.onclass.persona.application.usecase.command.CreatePersonCommand;
import com.onclass.persona.domain.exception.DuplicatePersonException;
import com.onclass.persona.domain.model.Person;
import com.onclass.persona.domain.spi.PersonRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreatePersonServiceTest {

    private PersonRepositoryPort personProvider;
    private CreatePersonService createPersonService;

    @BeforeEach
    void setUp() {
        personProvider = mock(PersonRepositoryPort.class);
        createPersonService = new CreatePersonService(personProvider);
    }

    @Test
    void shouldCreatePersonSuccessfully() {
        when(personProvider.existsByEmail("ana.vargas@onclass.com")).thenReturn(false);
        when(personProvider.save(any())).thenAnswer(invocation -> {
            Person p = invocation.getArgument(0);
            return Person.of(10L, p.name(), p.email());
        });

        Person result = createPersonService.execute(new CreatePersonCommand("Ana Vargas", "ana.vargas@onclass.com"));

        assertNotNull(result);
        assertEquals(10L, result.id());
        assertEquals("Ana Vargas", result.name());
        assertEquals("ana.vargas@onclass.com", result.email());

        verify(personProvider).existsByEmail("ana.vargas@onclass.com");
        verify(personProvider).save(any());
    }

    @Test
    void shouldThrowWhenEmailAlreadyExists() {
        when(personProvider.existsByEmail("ana.vargas@onclass.com")).thenReturn(true);

        assertThrows(DuplicatePersonException.class,
                () -> createPersonService.execute(new CreatePersonCommand("Ana", "ana.vargas@onclass.com")));

        verify(personProvider).existsByEmail("ana.vargas@onclass.com");
        verify(personProvider, never()).save(any());
    }

    @Test
    void shouldThrowWhenRequestInvalid() {
        assertThrows(RuntimeException.class,
                () -> createPersonService.execute(new CreatePersonCommand(" ", " ")));

        verify(personProvider, never()).existsByEmail(any());
        verify(personProvider, never()).save(any());
    }
}
