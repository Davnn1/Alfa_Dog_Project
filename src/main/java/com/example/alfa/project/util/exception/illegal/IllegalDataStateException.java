package com.example.alfa.project.util.exception.illegal;

public class IllegalDataStateException extends java.lang.IllegalStateException {
    private String message;

    public IllegalDataStateException(String message, String fieldValue) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
