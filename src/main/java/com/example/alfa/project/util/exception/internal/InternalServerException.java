package com.example.alfa.project.util.exception.internal;

import com.example.alfa.project.util.exception.BaseException;

public abstract class InternalServerException extends BaseException {
    protected String code;
    protected String causeError;

    public InternalServerException(String code, String causeError) {
        super(causeError);
        this.code = code;
        this.causeError = causeError;
    }

    @Override
    public String getCode() {
        return code;
    }

    public String getCauseError() {
        return causeError;
    }
}

