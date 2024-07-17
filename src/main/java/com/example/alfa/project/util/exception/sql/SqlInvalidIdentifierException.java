package com.example.alfa.project.util.exception.sql;

import java.sql.SQLException;

public class SqlInvalidIdentifierException extends SqlException {
    public SqlInvalidIdentifierException(String code, SQLException sqlException) {
        super(code, sqlException);
    }

    @Override
    public SqlException getSpecificException() {
        // Implementation can vary based on specific logic
        return this;
    }
}
