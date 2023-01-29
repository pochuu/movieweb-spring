package com.pochu.movieweb.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ApiException {
    private  String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timstamp;

}
