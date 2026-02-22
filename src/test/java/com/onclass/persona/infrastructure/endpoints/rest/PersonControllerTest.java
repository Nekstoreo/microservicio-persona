package com.onclass.persona.infrastructure.endpoints.rest;

import com.onclass.persona.application.dtos.requests.PersonRequest;
import com.onclass.persona.application.dtos.responses.PersonResponse;
import com.onclass.persona.application.services.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("PersonController")
class PersonControllerTest {

    private PersonService personService;
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        personService = mock(PersonService.class);
        webTestClient = WebTestClient.bindToController(new PersonController(personService)).build();
    }

    @Test
    @DisplayName("debe retornar persona por id")
    void shouldReturnPersonById() {
        when(personService.findById(1L)).thenReturn(new PersonResponse(1L, "Ana", "ana@onclass.com"));

        webTestClient.get()
                .uri("/persons/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Ana");
    }

    @Test
    @DisplayName("debe crear persona")
    void shouldCreatePerson() {
        PersonRequest request = new PersonRequest("Ana Vargas", "ana.vargas@onclass.com");
        when(personService.create(any())).thenReturn(new PersonResponse(10L, request.name(), request.email()));

        webTestClient.post()
                .uri("/persons")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo(10)
                .jsonPath("$.email").isEqualTo("ana.vargas@onclass.com");
    }

    @Test
    @DisplayName("debe retornar 404 cuando persona no existe")
    void shouldReturn404WhenPersonNotFound() {
        when(personService.findById(999L)).thenReturn(null);

        webTestClient.get()
                .uri("/persons/999")
                .exchange()
                .expectStatus().isNotFound();
    }
}
