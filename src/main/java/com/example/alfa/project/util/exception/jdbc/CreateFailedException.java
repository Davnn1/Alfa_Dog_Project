package com.example.alfa.project.util.exception.jdbc;

public class CreateFailedException extends JdbcActionException {
    public CreateFailedException(String message) {
        super(message, "409-03-02");
    }
}

