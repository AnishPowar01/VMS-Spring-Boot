package com.vms.vaccineManagementSystem.exception;

public class DeleteConstraintsOverruledException extends RuntimeException{

    public DeleteConstraintsOverruledException() {
    }

    public DeleteConstraintsOverruledException(String message) {
        super(message);
    }

    public DeleteConstraintsOverruledException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteConstraintsOverruledException(Throwable cause) {
        super(cause);
    }

    public DeleteConstraintsOverruledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
