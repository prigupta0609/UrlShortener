package com.padlet.common;

// Enum to store all the error codes
public enum Error {

    INVALID_URL_FORMAT("E01", "Input url format is invalid"),
    URL_NOT_FOUND("E02", "Request url not found");

    private String errorCode;
    private String message;

    Error(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
