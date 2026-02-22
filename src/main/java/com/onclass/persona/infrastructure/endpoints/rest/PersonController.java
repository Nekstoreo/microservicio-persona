package com.onclass.persona.infrastructure.endpoints.rest;

import com.onclass.persona.application.dtos.requests.PersonRequest;
import com.onclass.persona.application.dtos.responses.PersonResponse;
import com.onclass.persona.application.services.PersonService;
import com.onclass.persona.infrastructure.constants.ApiConstants;
import com.onclass.persona.infrastructure.exceptionshandler.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(ApiConstants.PERSONS_BASE_PATH)
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @Operation(summary = ApiConstants.OPENAPI_LIST_PERSONS_SUMMARY, description = ApiConstants.OPENAPI_LIST_PERSONS_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Persons listed successfully"),
            @ApiResponse(responseCode = "400", description = ApiConstants.OPENAPI_INVALID_REQUEST, content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public Mono<Page<PersonResponse>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        return Mono.fromCallable(() -> {
            Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
            return personService.findAll(pageable);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Operation(summary = ApiConstants.OPENAPI_GET_PERSON_SUMMARY, description = ApiConstants.OPENAPI_GET_PERSON_DESCRIPTION)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person found", content = @Content(schema = @Schema(implementation = PersonResponse.class))),
            @ApiResponse(responseCode = "404", description = ApiConstants.OPENAPI_PERSON_NOT_FOUND)
    })
    @GetMapping("/{id}")
    public Mono<PersonResponse> findById(@PathVariable("id") Long id) {
        return Mono.fromCallable(() -> {
            PersonResponse response = personService.findById(id);
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
    public Mono<PersonResponse> create(@Valid @RequestBody PersonRequest request) {
        return Mono.fromCallable(() -> personService.create(request)).subscribeOn(Schedulers.boundedElastic());
    }
}
