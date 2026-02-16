package com.onclass.persona.infrastructure.input.rest.controller;

import com.onclass.persona.application.dto.request.CreatePersonRequest;
import com.onclass.persona.application.dto.response.PersonResponse;
import com.onclass.persona.application.handler.PersonHandler;
import com.onclass.persona.infrastructure.constants.ApiConstants;
import com.onclass.persona.infrastructure.input.rest.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(ApiConstants.PERSONS_BASE_PATH)
@RequiredArgsConstructor
public class PersonController {

    private final PersonHandler personHandler;

    @Operation(summary = ApiConstants.OPENAPI_GET_PERSON_SUMMARY, description = ApiConstants.OPENAPI_GET_PERSON_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person found", content = @Content(schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "404", description = ApiConstants.OPENAPI_PERSON_NOT_FOUND)
    })
    @GetMapping("/{id}")
    public Mono<PersonResponse> findById(@PathVariable("id") Long id) {
        return Mono.fromCallable(() -> {
            PersonResponse response = personHandler.findById(id);
            if (response == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, ApiConstants.OPENAPI_PERSON_NOT_FOUND);
            }
            return response;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Operation(summary = ApiConstants.OPENAPI_CREATE_PERSON_SUMMARY, description = ApiConstants.OPENAPI_CREATE_PERSON_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = ApiConstants.OPENAPI_PERSON_CREATED, content = @Content(schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "400", description = ApiConstants.OPENAPI_INVALID_REQUEST, content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = ApiConstants.OPENAPI_UNAUTHORIZED),
            @ApiResponse(responseCode = "403", description = ApiConstants.OPENAPI_FORBIDDEN),
            @ApiResponse(responseCode = "409", description = ApiConstants.OPENAPI_DUPLICATE_PERSON, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<PersonResponse> create(@Valid @RequestBody CreatePersonRequest request) {
        return Mono.fromCallable(() -> personHandler.create(request)).subscribeOn(Schedulers.boundedElastic());
    }
}
