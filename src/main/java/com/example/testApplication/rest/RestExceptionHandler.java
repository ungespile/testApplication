package com.example.testApplication.rest;

import com.example.testApplication.dto.ErrorDto;
import com.example.testApplication.exception.CardIsNotActiveException;
import com.example.testApplication.exception.SubZeroBalanceException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CardIsNotActiveException.class)
    protected ResponseEntity<Object> handleCardIsNotActive(
            CardIsNotActiveException cardEx) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.I_AM_A_TEAPOT);
        errorDto.setMessage(cardEx.getMessage());
        return buildResponseEntity(errorDto);
    }

    @ExceptionHandler(SubZeroBalanceException.class)
    protected ResponseEntity<Object> handleCardIsNotActive(
            SubZeroBalanceException balanceEx) {
        ErrorDto errorDto = new ErrorDto(HttpStatus.BAD_REQUEST);
        errorDto.setMessage(balanceEx.getMessage());
        return buildResponseEntity(errorDto);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorDto errorDto) {
        return new ResponseEntity<>(errorDto, errorDto.getStatus());
    }


}
