package com.minimarket.catalogservice.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Map;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    String message;
    private Instant timestamp;
    private Map <String, String > fieldErrors;
    HttpStatus httpStatus;

    public ApiError(String message, Instant timestamp, Map <String, String > fieldErrors) {
        this.message = message;
        this.timestamp = timestamp;
        this.fieldErrors = fieldErrors;
    }
    public ApiError(String message, HttpStatus httpStatus, Instant timestamp) {
        this.message = message;
        this.timestamp = timestamp;
        this.httpStatus = httpStatus;
    }

}
