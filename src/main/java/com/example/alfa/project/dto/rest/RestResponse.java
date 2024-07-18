package com.example.alfa.project.dto.rest;

public class RestResponse<T> {
    private T message;
    private String status;

    // Getters and setters
    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
