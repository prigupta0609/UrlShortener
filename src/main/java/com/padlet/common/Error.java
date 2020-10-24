package com.padlet.common;

public enum Error {

    INVALID_URL_FORMAT("E01", "Input url format is invalid");

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
