package com.vms.vaccineManagementSystem.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleException(UserNotFoundException userNotFoundException){
        ErrorDetails errorDetails=new ErrorDetails(HttpStatus.NOT_FOUND.value(),userNotFoundException.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleException(Exception e){
        ErrorDetails errorDetails=new ErrorDetails(HttpStatus.BAD_REQUEST.value(),e.getMessage(),System.currentTimeMillis());
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

}

