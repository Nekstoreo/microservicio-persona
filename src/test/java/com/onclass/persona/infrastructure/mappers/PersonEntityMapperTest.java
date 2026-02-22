package com.onclass.persona.infrastructure.mappers;

import com.onclass.persona.domain.models.PersonModel;
import com.onclass.persona.infrastructure.entities.PersonEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PersonEntityMapper")
class PersonEntityMapperTest {

    @Test
    @DisplayName("debe mapear PersonEntity a PersonModel")
    void toModel_shouldMapEntityToModel() {
        PersonEntity entity = new PersonEntity();
        entity.setId(5L);
        entity.setName("Ana Vargas");
        entity.setEmail("ana@onclass.com");

        PersonModel result = PersonEntityMapper.toModel(entity);

        assertNotNull(result);
        assertEquals(5L, result.id());
        assertEquals("Ana Vargas", result.name());
        assertEquals("ana@onclass.com", result.email());
    }

    @Test
    @DisplayName("debe mapear PersonModel a PersonEntity")
    void toEntity_shouldMapModelToEntity() {
        PersonModel model = PersonModel.of(7L, "Juan Perez", "juan@test.com");

        PersonEntity result = PersonEntityMapper.toEntity(model);

        assertNotNull(result);
        assertEquals(7L, result.getId());
        assertEquals("Juan Perez", result.getName());
        assertEquals("juan@test.com", result.getEmail());
    }

    @Test
    @DisplayName("debe mapear modelo sin id (creación)")
    void toEntity_shouldMapModelWithoutId() {
        PersonModel model = PersonModel.create("New User", "new@test.com");

        PersonEntity result = PersonEntityMapper.toEntity(model);

        assertNotNull(result);
        assertNull(result.getId());
        assertEquals("New User", result.getName());
        assertEquals("new@test.com", result.getEmail());
    }
}
