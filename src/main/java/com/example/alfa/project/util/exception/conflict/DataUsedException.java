package com.example.alfa.project.util.exception.conflict;

public class DataUsedException extends ConflictException {
    private static final String CODE = "409-01";

    public DataUsedException(String fieldCauseError) {
        super(CODE, fieldCauseError);
    }

    @Override
    public String getCode() {
        return CODE;
    }
}
