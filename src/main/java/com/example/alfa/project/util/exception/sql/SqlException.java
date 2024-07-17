package com.example.alfa.project.util.exception.sql;

import com.example.alfa.project.util.exception.BaseException;

import java.sql.SQLException;

public abstract class SqlException extends BaseException {
    protected String code;
    protected SQLException sqlException;

    public SqlException(String code, SQLException sqlException) {
        super(sqlException == null ? "" : sqlException.getMessage());
        this.code = code;
        this.sqlException = sqlException;
    }

    public String getValueCauseError() {
        return sqlException != null ? sqlException.getLocalizedMessage().split("\"")[1] : "";
    }

    public abstract SqlException getSpecificException();

    @Override
    public String getCode() {
        return code;
    }
}