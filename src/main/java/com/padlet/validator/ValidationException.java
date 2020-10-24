package com.padlet.validator;

public class ValidationException extends Exception {

    private String errorCode;
    private String message;

    public ValidationException(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
