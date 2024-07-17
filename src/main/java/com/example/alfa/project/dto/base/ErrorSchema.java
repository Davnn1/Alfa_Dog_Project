package com.example.alfa.project.dto.base;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class ErrorSchema {

    @JsonProperty("error_code")
    private String code;

    @JsonProperty("error_message")
    private Map<String, String> message;

    public ErrorSchema(String code, Map<String, String> message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, String> getMessage() {
        return message;
    }

    public void setMessage(Map<String, String> message) {
        this.message = message;
    }
}