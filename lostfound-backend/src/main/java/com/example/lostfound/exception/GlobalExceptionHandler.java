package com.example.lostfound.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(NotFoundException ex) {
        ApiError err = new ApiError(HttpStatus.NOT_FOUND.value(),
                "Not Found", ex.getMessage(), List.of());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAll(Exception ex) {
        ApiError err = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error", ex.getMessage(), List.of());
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
        List<String> details = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .collect(Collectors.toList());

        ApiError err = new ApiError(HttpStatus.BAD_REQUEST.value(),
                "Validation Failed", "Input validation failed", details);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
