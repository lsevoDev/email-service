package com.lsevo.emailservice.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;


@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleValidation(MethodArgumentNotValidException ex) {
        var errors = extractValidationErrors(ex.getBindingResult());
        return ResponseEntity.status(ex.getStatusCode()).body(new ValidationError(errors));
    }

    private Map<String, String> extractValidationErrors(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream()
                .filter(e -> e instanceof FieldError)
                .map(e -> (FieldError) e)
                .collect(Collectors.toMap(FieldError::getField, e -> e.getDefaultMessage() != null ? e.getDefaultMessage() : ""));
    }
}
