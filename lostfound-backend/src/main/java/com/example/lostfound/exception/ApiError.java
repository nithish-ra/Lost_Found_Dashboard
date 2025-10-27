package com.example.lostfound.exception;

import java.time.Instant;
import java.util.List;

public class ApiError {
    private Instant timestamp = Instant.now();
    private int status;
    private String error;
    private String message;
    private List<String> details;

    // constructors, getters, setters
    public ApiError() {}
    public ApiError(int status, String error, String message, List<String> details) {
        this.status = status; this.error = error; this.message = message; this.details = details;
    }
    // getters & setters...
}
