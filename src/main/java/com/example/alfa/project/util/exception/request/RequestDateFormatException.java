package com.example.alfa.project.util.exception.request;

public class RequestDateFormatException extends RequestException {
    public RequestDateFormatException(String errorValue, String code) {
        super(errorValue, code.isEmpty() ? "400-01" : code);
    }

    public RequestDateFormatException() {
        this("", "400-01");
    }
}
