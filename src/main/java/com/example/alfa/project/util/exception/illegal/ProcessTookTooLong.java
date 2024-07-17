package com.example.alfa.project.util.exception.illegal;

public class ProcessTookTooLong extends IllegalStateException {
    private String message;

    public ProcessTookTooLong(String message) {
        super("AP-50-004", null);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
