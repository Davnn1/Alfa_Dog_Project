package com.example.alfa.project.util.exception.access;

public class DataSaveFailedException extends AccessException {
    private static final String CODE = "509-01";

    public DataSaveFailedException(String fieldCauseError) {
        super(CODE, fieldCauseError);
    }

    @Override
    public String getCode() {
        return CODE;
    }
}
