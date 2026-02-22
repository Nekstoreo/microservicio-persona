package com.onclass.persona.application.mappers;

import com.onclass.persona.application.dtos.requests.PersonRequest;
import com.onclass.persona.application.dtos.responses.PersonResponse;
import com.onclass.persona.domain.models.PersonModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PersonDtoMapper")
class PersonDtoMapperTest {

    @Nested
    @DisplayName("toModel")
    class ToModel {

        @Test
        @DisplayName("debe mapear PersonRequest a PersonModel")
        void shouldMapRequestToModel() {
            PersonRequest request = new PersonRequest("Ana Vargas", "ana@onclass.com");

            PersonModel result = PersonDtoMapper.toModel(request);

            assertNotNull(result);
            assertEquals(request.name(), result.name());
            assertEquals(request.email(), result.email());
            assertNull(result.id());
        }

        @Test
        @DisplayName("debe producir modelo válido")
        void shouldProduceValidModel() {
            PersonRequest request = new PersonRequest("Test User", "test@example.com");

            PersonModel model = PersonDtoMapper.toModel(request);

            assertEquals("Test User", model.name());
            assertEquals("test@example.com", model.email());
        }
    }

    @Nested
    @DisplayName("toResponse")
    class ToResponse {

        @Test
        @DisplayName("debe mapear PersonModel a PersonResponse")
        void shouldMapModelToResponse() {
            PersonModel model = PersonModel.of(10L, "Ana Vargas", "ana@onclass.com");

            PersonResponse result = PersonDtoMapper.toResponse(model);

            assertNotNull(result);
            assertEquals(10L, result.id());
            assertEquals("Ana Vargas", result.name());
            assertEquals("ana@onclass.com", result.email());
        }

        @Test
        @DisplayName("debe manejar modelo con id null")
        void shouldHandleModelWithNullId() {
            PersonModel model = PersonModel.create("New", "new@test.com");

            PersonResponse result = PersonDtoMapper.toResponse(model);

            assertNotNull(result);
            assertNull(result.id());
            assertEquals("New", result.name());
            assertEquals("new@test.com", result.email());
        }
    }
}
