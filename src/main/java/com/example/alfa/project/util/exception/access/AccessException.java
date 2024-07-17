package com.example.alfa.project.util.exception.access;

import com.example.alfa.project.util.exception.BaseException;

public abstract class AccessException extends BaseException {
    private String fieldCauseError;

    public AccessException(String code, String fieldCauseError) {
        super(fieldCauseError);
        this.fieldCauseError = fieldCauseError;
    }

    @Override
    public String getCode() {
        return null; // Implement logic to return the code
    }

    public String getFieldCauseError() {
        return fieldCauseError;
    }
}

