package com.example.alfa.project.util.exception.sql;

import java.sql.SQLException;

public class SqlSyntaxException extends SqlException {
    public SqlSyntaxException(String code, SQLException sqlException) {
        super(code, sqlException);
    }

    @Override
    public SqlException getSpecificException() {
        // Implementation can vary based on specific logic
        return this;
    }
}
