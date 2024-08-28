package com.vms.vaccineManagementSystem.exception;

public class ConstraintsOverruledException  extends RuntimeException {

    public ConstraintsOverruledException() {
    }

    public ConstraintsOverruledException(String message) {
        super(message);
    }

    public ConstraintsOverruledException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConstraintsOverruledException(Throwable cause) {
        super(cause);
    }

    public ConstraintsOverruledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
