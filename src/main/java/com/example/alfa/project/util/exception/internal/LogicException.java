package com.example.alfa.project.util.exception.internal;

public class LogicException extends InternalServerException {
    public LogicException(String code, String causeError) {
        super(code.isEmpty() ? "-" : code, causeError);
    }
}
