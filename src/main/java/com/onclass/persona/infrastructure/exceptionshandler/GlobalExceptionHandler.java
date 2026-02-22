package com.onclass.persona.infrastructure.exceptionshandler;

import com.onclass.persona.domain.exceptions.DomainValidationException;
import com.onclass.persona.domain.exceptions.DuplicatePersonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainValidationException.class)
    public ResponseEntity<ErrorResponse> handleDomainValidation(DomainValidationException ex, ServerWebExchange exchange) {
        return buildError(HttpStatus.BAD_REQUEST, ex.getMessage(), exchange);
    }
    public ResponseEntity<ErrorResponse> handleDomainValidation(DomainValidationException ex) {
        return handleDomainValidation(ex, null);
    }

    @ExceptionHandler(DuplicatePersonException.class)
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicatePersonException ex, ServerWebExchange exchange) {
        return buildError(HttpStatus.CONFLICT, ex.getMessage(), exchange);
    }
    public ResponseEntity<ErrorResponse> handleDuplicate(DuplicatePersonException ex) {
        return handleDuplicate(ex, null);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(WebExchangeBindException ex, ServerWebExchange exchange) {
        FieldError fieldError = ex.getFieldError();
        String message = fieldError != null ? fieldError.getDefaultMessage() : com.onclass.persona.infrastructure.constants.ApiConstants.DEFAULT_INVALID_REQUEST_MESSAGE;
        return buildError(HttpStatus.BAD_REQUEST, message, exchange);
    }
    public ResponseEntity<ErrorResponse> handleBindException(WebExchangeBindException ex) {
        return handleBindException(ex, null);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex, ServerWebExchange exchange) {
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error", exchange);
    }

    private ResponseEntity<ErrorResponse> buildError(HttpStatus status, String message, ServerWebExchange exchange) {
        String path = exchange != null ? exchange.getRequest().getPath().value() : null;
        String requestId = exchange != null ? exchange.getRequest().getId() : null;
        return ResponseEntity.status(status).body(new ErrorResponse(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                path,
                requestId
        ));
    }
}
