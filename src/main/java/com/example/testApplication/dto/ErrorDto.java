package com.example.testApplication.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorDto {

    private String message;
    private final HttpStatus status;
}
