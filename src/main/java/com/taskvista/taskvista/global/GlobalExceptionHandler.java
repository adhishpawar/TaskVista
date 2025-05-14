package com.taskvista.taskvista.global;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFound(EntityNotFoundException ex)
    {
        return new ResponseEntity<>(Map.of("error",ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex)
    {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream().map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();

        return new ResponseEntity<>(Map.of("errors",errors),HttpStatus.BAD_REQUEST);
    }
}
