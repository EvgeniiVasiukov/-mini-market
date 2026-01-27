package com.minimarket.catalogservice.exception;

import lombok.Getter;

import java.time.Instant;
import java.util.Map;

@Getter
public class ApiError {
    String message;
    private Instant timestamp;
    private Map <String, String > fieldErrors;

    public ApiError(String message, Instant timestamp, Map <String, String > fieldErrors) {
        this.message = message;
        this.timestamp = timestamp;
        this.fieldErrors = fieldErrors;
    }
    public ApiError(String message, Instant timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

}
