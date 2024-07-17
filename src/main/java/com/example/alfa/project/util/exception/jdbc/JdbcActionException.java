package com.example.alfa.project.util.exception.jdbc;

import com.example.alfa.project.util.exception.BaseException;

public abstract class JdbcActionException extends BaseException {
    protected String code;

    public JdbcActionException(String message, String code) {
        super(message);
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}

