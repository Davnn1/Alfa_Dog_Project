package com.example.alfa.project.util.exception.unfound;

public class ResourceNotFoundException extends NotFoundErrorException {
    private static final String DEFAULT_CODE = "AP-20-001";

    public ResourceNotFoundException() {
        this(DEFAULT_CODE);
    }

    public ResourceNotFoundException(String code) {
        super(code);
    }
}
