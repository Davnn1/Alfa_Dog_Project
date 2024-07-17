package com.example.alfa.project.dto.base;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WrapperResponse<T> {
    @JsonProperty("output_schema")
    private T outputSchema;

    @JsonProperty("error_schema")
    private ErrorSchema errorSchema;

    public WrapperResponse() {
    }

    public WrapperResponse(T outputSchema, ErrorSchema errorSchema) {
        this.outputSchema = outputSchema;
        this.errorSchema = errorSchema;
    }

    public static <T> WrapperResponse<T> generateSuccess(T data) {
        return new WrapperResponse<>(
                data,
                new ErrorSchema("200", Map.of("OK", "OK"))
        );
    }

    public static <T> WrapperResponse<T> generateNotFound(T data) {
        return new WrapperResponse<>(
                data,
                new ErrorSchema("404", Map.of("Not Found", "Not Found"))
        );
    }

    public T getOutputSchema() {
        return outputSchema;
    }

    public void setOutputSchema(T outputSchema) {
        this.outputSchema = outputSchema;
    }

    public ErrorSchema getErrorSchema() {
        return errorSchema;
    }

    public void setErrorSchema(ErrorSchema errorSchema) {
        this.errorSchema = errorSchema;
    }
}