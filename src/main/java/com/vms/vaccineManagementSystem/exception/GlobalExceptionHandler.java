package com.vms.vaccineManagementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException userNotFoundException, WebRequest webRequest)
    {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_FOUND, userNotFoundException.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintsOverruledException.class)
    public ResponseEntity<?> handleConstraintsOverruledException(ConstraintsOverruledException constraintsOverruledException, WebRequest webRequest)
    {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_ACCEPTABLE, constraintsOverruledException.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }


    @ExceptionHandler(DeleteConstraintsOverruledException.class)
    public ResponseEntity<?> handleDeleteConstraintsOverruledException(DeleteConstraintsOverruledException constraintsOverruledException, WebRequest webRequest)
    {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.NOT_ACCEPTABLE, constraintsOverruledException.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_ACCEPTABLE);
    }
}
