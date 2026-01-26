package com.minimarket.catalogservice.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiError {
    String message;
    LocalDateTime timestamp;
    HttpStatus status;
    String path;
    public ApiError(String message, LocalDateTime timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
    public String getMessage() {
        return message;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
