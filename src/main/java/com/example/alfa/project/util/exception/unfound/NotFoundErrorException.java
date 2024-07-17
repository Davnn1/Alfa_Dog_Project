package com.example.alfa.project.util.exception.unfound;

import com.example.alfa.project.util.exception.BaseException;

public abstract class NotFoundErrorException extends BaseException {
    protected String code;

    public NotFoundErrorException(String code) {
        super(null); // Assuming the BaseException's msg is not used here
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
