package com.onclass.persona.domain.exception;

import com.onclass.persona.domain.constants.DomainConstants;

public class DuplicatePersonException extends RuntimeException {

    public DuplicatePersonException(String email) {
        super(DomainConstants.duplicatePersonMessage(email));
    }
} 
