package com.example.alfa.project.util.exception.conflict;

public class DataNotFoundException extends ConflictException {
    private static final String CODE = "409-02";

    public DataNotFoundException(String fieldCauseError) {
        super(CODE, fieldCauseError);
    }

    @Override
    public String getCode() {
        return CODE;
    }
}
