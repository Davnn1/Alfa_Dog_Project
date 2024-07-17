package com.example.alfa.project.util.exception.request;

public class RequestDataMissingException extends RequestException {
    public RequestDataMissingException(String errorValue, String code) {
        super(errorValue, code.isEmpty() ? "400-02" : code);
    }

    public RequestDataMissingException() {
        this("", "400-02");
    }
}
