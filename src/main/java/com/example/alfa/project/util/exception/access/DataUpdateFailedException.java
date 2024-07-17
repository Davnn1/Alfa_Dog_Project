package com.example.alfa.project.util.exception.access;

public class DataUpdateFailedException extends AccessException {
    private static final String CODE = "509-02";

    public DataUpdateFailedException(String fieldCauseError) {
        super(CODE, fieldCauseError);
    }

    @Override
    public String getCode() {
        return CODE;
    }
}
