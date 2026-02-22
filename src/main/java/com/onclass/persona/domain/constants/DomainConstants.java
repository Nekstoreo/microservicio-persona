package com.onclass.persona.domain.constants;

public final class DomainConstants {

    public static final int PERSON_NAME_MAX_LENGTH = 150;
    public static final int PERSON_EMAIL_MAX_LENGTH = 150;

    public static final String PERSON_ID_REQUIRED_MESSAGE = "Person id is required";
    public static final String PERSON_NAME_REQUIRED_MESSAGE = "Person name is required";
    public static final String PERSON_EMAIL_REQUIRED_MESSAGE = "Person email is required";
    public static final String PERSON_NAME_EMAIL_REQUIRED_MESSAGE = "Person name and email must be provided";

    private static final String DUPLICATE_PERSON_TEMPLATE = "A person with email already exists: %s";

    private DomainConstants() {
    }

    public static String duplicatePersonMessage(String email) {
        return DUPLICATE_PERSON_TEMPLATE.formatted(email);
    }
}
