package com.vms.vaccineManagementSystem.exception;

import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorDetails {

    private LocalDateTime localDateTime;
    private HttpStatus httpStatus;
    private String message;
    private String details;

    public ErrorDetails( HttpStatus httpStatus, String message, String details) {
        this.localDateTime = LocalDateTime.now();
        this.httpStatus = httpStatus;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
