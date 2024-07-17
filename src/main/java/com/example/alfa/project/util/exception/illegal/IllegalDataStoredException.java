package com.example.alfa.project.util.exception.illegal;

public class IllegalDataStoredException extends java.lang.IllegalStateException {
    private String fieldName;
    private String message;

    public IllegalDataStoredException(String fieldName, String fieldValue) {
        super();
        this.fieldName = fieldName;
        this.message = "Found illegal data stored: " + (fieldName != null ? "field name: " + fieldName : "") + (fieldValue != null ? " has illegal value: " + fieldValue : "");
    }

    @Override
    public String getMessage() {
        return message;
    }
}
