package com.example.alfa.project.util.exception.request;

import com.example.alfa.project.util.exception.BaseException;

public abstract class RequestException extends BaseException {
    protected String code;
    protected String errorValue;

    public RequestException(String errorValue, String code) {
        super(errorValue);
        this.code = code;
        this.errorValue = errorValue;
    }

    @Override
    public String getCode() {
        return code;
    }

    public String getErrorValue() {
        return errorValue;
    }
}

