package com.minimarket.catalogservice.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.Instant;
import java.util.Map;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
    @Schema(example = "Error message")
    String message;
    private Integer status;
    @Schema(example = "2026-01-31T12:09:08.999Z")
    private Instant timestamp;
    private Map <String, String > fieldErrors;


    public ApiError(String message, Instant timestamp, Map <String, String > fieldErrors) {
        this.message = message;
        this.timestamp = timestamp;
        this.fieldErrors = fieldErrors;
    }
    public ApiError(String message, Integer status, Instant timestamp) {
        this.message = message;
        this.timestamp = timestamp;
        this.status = status;
    }

}
