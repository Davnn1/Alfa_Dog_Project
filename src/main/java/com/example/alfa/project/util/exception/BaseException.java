package com.example.alfa.project.util.exception;

public abstract class BaseException extends RuntimeException {
    protected String msg;

    public BaseException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public abstract String getCode();
}