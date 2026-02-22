package com.onclass.persona.domain.models;

import com.onclass.persona.domain.exceptions.DomainValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PersonModel")
class PersonModelTest {

    @Nested
    @DisplayName("create")
    class Create {

        @Test
        @DisplayName("debe crear modelo correctamente con name y email válidos")
        void shouldCreateWithValidData() {
            PersonModel result = PersonModel.create("Ana Vargas", "ana@onclass.com");

            assertNotNull(result);
            assertNull(result.id());
            assertEquals("Ana Vargas", result.name());
            assertEquals("ana@onclass.com", result.email());
        }

        @Test
        @DisplayName("debe normalizar espacios en blanco")
        void shouldTrimWhitespace() {
            PersonModel result = PersonModel.create("  Ana  ", "  ana@test.com  ");

            assertEquals("Ana", result.name());
            assertEquals("ana@test.com", result.email());
        }

        @Test
        @DisplayName("debe lanzar cuando name es null")
        void shouldThrowWhenNameIsNull() {
            assertThrows(NullPointerException.class, () -> PersonModel.create(null, "email@test.com"));
        }

        @Test
        @DisplayName("debe lanzar cuando email es null")
        void shouldThrowWhenEmailIsNull() {
            assertThrows(NullPointerException.class, () -> PersonModel.create("Ana", null));
        }

        @Test
        @DisplayName("debe lanzar DomainValidationException cuando name está vacío tras trim")
        void shouldThrowWhenNameIsEmpty() {
            assertThrows(DomainValidationException.class, () -> PersonModel.create("   ", "a@b.com"));
        }

        @Test
        @DisplayName("debe lanzar DomainValidationException cuando email está vacío tras trim")
        void shouldThrowWhenEmailIsEmpty() {
            assertThrows(DomainValidationException.class, () -> PersonModel.create("Ana", ""));
        }
    }

    @Nested
    @DisplayName("of")
    class Of {

        @Test
        @DisplayName("debe crear modelo con id")
        void shouldCreateWithId() {
            PersonModel result = PersonModel.of(1L, "Ana", "ana@test.com");

            assertEquals(1L, result.id());
            assertEquals("Ana", result.name());
            assertEquals("ana@test.com", result.email());
        }

        @Test
        @DisplayName("debe normalizar espacios en blanco")
        void shouldTrimWhitespace() {
            PersonModel result = PersonModel.of(1L, "  Ana  ", "  a@b.com  ");

            assertEquals("Ana", result.name());
            assertEquals("a@b.com", result.email());
        }

        @Test
        @DisplayName("debe lanzar cuando id es null")
        void shouldThrowWhenIdIsNull() {
            assertThrows(NullPointerException.class, () -> PersonModel.of(null, "Ana", "a@b.com"));
        }

        @Test
        @DisplayName("debe lanzar cuando name es null")
        void shouldThrowWhenNameIsNull() {
            assertThrows(NullPointerException.class, () -> PersonModel.of(1L, null, "a@b.com"));
        }

        @Test
        @DisplayName("debe lanzar cuando email es null")
        void shouldThrowWhenEmailIsNull() {
            assertThrows(NullPointerException.class, () -> PersonModel.of(1L, "Ana", null));
        }

        @Test
        @DisplayName("debe lanzar DomainValidationException cuando name está vacío")
        void shouldThrowWhenNameIsEmpty() {
            assertThrows(DomainValidationException.class, () -> PersonModel.of(1L, "  ", "a@b.com"));
        }

        @Test
        @DisplayName("debe lanzar DomainValidationException cuando email está vacío")
        void shouldThrowWhenEmailIsEmpty() {
            assertThrows(DomainValidationException.class, () -> PersonModel.of(1L, "Ana", ""));
        }
    }
}
