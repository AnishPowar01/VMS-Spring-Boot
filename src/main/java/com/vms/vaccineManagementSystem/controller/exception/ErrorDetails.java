package com.vms.vaccineManagementSystem.controller.exception;

import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorDetails {
    private long localDateTime;
    private int httpStatus;
    private String message;

    public ErrorDetails(int httpStatus, String message, long localDateTime) {
        this.localDateTime = localDateTime;
        this.httpStatus = httpStatus;
        this.message = message;
//        this.details = details;
    }

    public long getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(long localDateTime) {
        this.localDateTime = localDateTime;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public String getDetails() {
//        return details;
//    }
//
//    public void setDetails(String details) {
//        this.details = details;
//    }

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "localDateTime=" + localDateTime +
                ", httpStatus=" + httpStatus +
                ", message='" + message + '\''  +
                '}';
    }
}
