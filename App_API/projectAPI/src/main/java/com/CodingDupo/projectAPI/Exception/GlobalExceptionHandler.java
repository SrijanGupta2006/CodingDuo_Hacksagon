package com.CodingDupo.projectAPI.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.CodingDupo.projectAPI.DTO.ErrorResponse;
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value=ResponseToException.class)
    public ResponseEntity<?> handleException(ResponseToException e){
        String s[] = e.getMessage().split(":");
        ErrorResponse response = new ErrorResponse(Integer.parseInt(s[0]), s[1]);
        return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatusCode()));
    }
}
