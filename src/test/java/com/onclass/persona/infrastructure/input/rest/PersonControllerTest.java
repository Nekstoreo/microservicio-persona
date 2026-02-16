package com.onclass.persona.infrastructure.input.rest;

import com.onclass.persona.application.dto.request.CreatePersonRequest;
import com.onclass.persona.application.dto.response.PersonResponse;
import com.onclass.persona.application.handler.PersonHandler;
import com.onclass.persona.domain.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PersonControllerTest {

    private PersonHandler handler;
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        handler = mock(PersonHandler.class);
        webTestClient = WebTestClient.bindToController(new com.onclass.persona.infrastructure.input.rest.controller.PersonController(handler)).build();
    }

    @Test
    void shouldReturnPersonById() {
        when(handler.findById(1L)).thenReturn(new PersonResponse(1L, "Ana", "ana@onclass.com"));

        webTestClient.get()
                .uri("/persons/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("Ana");
    }

    @Test
    void shouldCreatePerson() {
        CreatePersonRequest request = new CreatePersonRequest("Ana Vargas", "ana.vargas@onclass.com");
        when(handler.create(any())).thenReturn(new PersonResponse(10L, request.name(), request.email()));

        webTestClient.post()
                .uri("/persons")
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo(10)
                .jsonPath("$.email").isEqualTo("ana.vargas@onclass.com");
    }
}
