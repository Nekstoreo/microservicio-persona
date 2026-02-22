package com.onclass.persona.infrastructure.constants;

public final class ApiConstants {

    public static final String PERSONS_BASE_PATH = "/persons";

    public static final String OPENAPI_GET_PERSON_SUMMARY = "Get Person";
    public static final String OPENAPI_GET_PERSON_DESCRIPTION = "Gets a Person by ID";
    public static final String OPENAPI_CREATE_PERSON_SUMMARY = "Create Person";
    public static final String OPENAPI_CREATE_PERSON_DESCRIPTION = "Registers a new Person in the system";
    public static final String OPENAPI_PERSON_CREATED = "Person created";
    public static final String OPENAPI_PERSON_NOT_FOUND = "Person not found";
    public static final String OPENAPI_INVALID_REQUEST = "Invalid request";
    public static final String OPENAPI_UNAUTHORIZED = "Unauthorized";
    public static final String OPENAPI_FORBIDDEN = "Forbidden";
    public static final String OPENAPI_DUPLICATE_PERSON = "Duplicate email";

    public static final String VALIDATION_NAME_REQUIRED = "Name is required";
    public static final String VALIDATION_EMAIL_REQUIRED = "Email is required";
    public static final String VALIDATION_EMAIL_INVALID = "Email must be a valid email";
    public static final String VALIDATION_NAME_MAX_LENGTH = "Name must be at most 150 characters";
    public static final String VALIDATION_EMAIL_MAX_LENGTH = "Email must be at most 150 characters";

    public static final String DEFAULT_INVALID_REQUEST_MESSAGE = "Invalid request";

    private ApiConstants() {
    }
}
