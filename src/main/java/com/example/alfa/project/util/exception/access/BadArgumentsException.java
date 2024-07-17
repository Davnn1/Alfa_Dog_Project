package com.example.alfa.project.util.exception.access;

public class BadArgumentsException extends AccessException {
    private static final String CODE = "400-02";

    public BadArgumentsException(String fieldCauseError) {
        super(CODE, fieldCauseError);
    }

    @Override
    public String getCode() {
        return CODE;
    }
}
