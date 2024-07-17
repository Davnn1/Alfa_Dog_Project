package com.example.alfa.project.util.exception.conflict;

public class NotMatchQueryException extends ConflictException {
    public NotMatchQueryException(String fieldCauseError) {
        super("500-05-01", fieldCauseError);
    }
}
