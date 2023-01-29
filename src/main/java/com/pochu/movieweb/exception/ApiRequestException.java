package com.pochu.movieweb.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.ZonedDateTime;

@Data
public class ApiRequestException extends RuntimeException {
    private HttpStatus statusCode;
    public ApiRequestException(String message, HttpStatus statusCode)
    {
        super(message);
        this.statusCode = statusCode;
    }
    public ApiRequestException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
