package com.onclass.persona.infrastructure.adapters.persistence;

import com.onclass.persona.domain.models.PersonModel;
import com.onclass.persona.infrastructure.entities.PersonEntity;
import com.onclass.persona.infrastructure.repositories.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("PersonPersistenceAdapter")
class PersonPersistenceAdapterTest {

    private PersonRepository personRepository;
    private PersonPersistenceAdapter personPersistenceAdapter;

    @BeforeEach
    void setUp() {
        personRepository = mock(PersonRepository.class);
        personPersistenceAdapter = new PersonPersistenceAdapter(personRepository);
    }

    @Nested
    @DisplayName("findById")
    class FindById {

        @Test
        @DisplayName("debe retornar PersonModel cuando existe")
        void shouldReturnModelWhenExists() {
            PersonEntity entity = new PersonEntity();
            entity.setId(1L);
            entity.setName("Ana");
            entity.setEmail("ana@test.com");
            when(personRepository.findById(1L)).thenReturn(Optional.of(entity));

            PersonModel result = personPersistenceAdapter.findById(1L);

            assertNotNull(result);
            assertEquals(1L, result.id());
            assertEquals("Ana", result.name());
            assertEquals("ana@test.com", result.email());
            verify(personRepository).findById(1L);
        }

        @Test
        @DisplayName("debe retornar null cuando no existe")
        void shouldReturnNullWhenNotFound() {
            when(personRepository.findById(999L)).thenReturn(Optional.empty());

            PersonModel result = personPersistenceAdapter.findById(999L);

            assertNull(result);
            verify(personRepository).findById(999L);
        }
    }

    @Nested
    @DisplayName("save")
    class Save {

        @Test
        @DisplayName("debe guardar y retornar modelo con id generado")
        void shouldSaveAndReturnModelWithId() {
            PersonModel input = PersonModel.create("Ana Vargas", "ana@onclass.com");
            PersonEntity savedEntity = new PersonEntity();
            savedEntity.setId(10L);
            savedEntity.setName("Ana Vargas");
            savedEntity.setEmail("ana@onclass.com");
            when(personRepository.save(any())).thenReturn(savedEntity);

            PersonModel result = personPersistenceAdapter.save(input);

            assertNotNull(result);
            assertEquals(10L, result.id());
            assertEquals("Ana Vargas", result.name());
            assertEquals("ana@onclass.com", result.email());
            verify(personRepository).save(any());
        }
    }

    @Nested
    @DisplayName("existsByEmail")
    class ExistsByEmail {

        @Test
        @DisplayName("debe retornar true cuando el email existe")
        void shouldReturnTrueWhenEmailExists() {
            when(personRepository.existsByEmail("ana@test.com")).thenReturn(true);

            boolean result = personPersistenceAdapter.existsByEmail("ana@test.com");

            assertTrue(result);
            verify(personRepository).existsByEmail("ana@test.com");
        }

        @Test
        @DisplayName("debe retornar false cuando el email no existe")
        void shouldReturnFalseWhenEmailNotExists() {
            when(personRepository.existsByEmail("nuevo@test.com")).thenReturn(false);

            boolean result = personPersistenceAdapter.existsByEmail("nuevo@test.com");

            assertFalse(result);
            verify(personRepository).existsByEmail("nuevo@test.com");
        }
    }
}
