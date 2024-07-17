package com.example.alfa.project.controller;

import com.example.alfa.project.dto.base.ErrorSchema;
import com.example.alfa.project.dto.base.WrapperResponse;
import com.example.alfa.project.util.BaseUtil;
import com.example.alfa.project.util.exception.access.AccessException;
import com.example.alfa.project.util.exception.conflict.ConflictException;
import com.example.alfa.project.util.exception.request.RequestException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionController {

    private final MessageSource msgSource;

    @Autowired
    public GlobalExceptionController(MessageSource msgSource) {
        this.msgSource = msgSource;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public WrapperResponse<Void> returnInternalServerError(HttpServletResponse response, Exception exception) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        String errorCode;
        if (exception instanceof SQLSyntaxErrorException) {
            errorCode = "500";
        } else if (exception instanceof SQLException) {
            errorCode = "500-04";
        } else {
            errorCode = "500";
        }
        Map<String, String> errorMsg = BaseUtil.getErrorMsg(msgSource, errorCode, null);
        ErrorSchema errorSchema = new ErrorSchema(errorCode + " " + exception.getMessage(), errorMsg);
        exception.printStackTrace();
        return new WrapperResponse<>(null, errorSchema);
    }

    @ExceptionHandler(RequestException.class)
    @ResponseBody
    public WrapperResponse<Void> returnBadRequest(HttpServletResponse response, RequestException exception) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        Map<String, String> errorMsg = BaseUtil.getErrorMsg(msgSource, exception.getCode(), null);
        ErrorSchema errorSchema = new ErrorSchema(exception.getCode(), errorMsg);
        return new WrapperResponse<>(null, errorSchema);
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public HttpEntity<String> exceptionHandler(IOException e) {
        if (StringUtils.containsIgnoreCase(ExceptionUtils.getRootCauseMessage(e), "Broken pipe")) {
            return null; //socket is closed, cannot return any response
        } else {
            return new HttpEntity<>(e.getMessage());
        }
    }

    @ExceptionHandler(ConflictException.class)
    @ResponseBody
    public ResponseEntity<WrapperResponse<Void>> conflictException(HttpServletResponse response, ConflictException e) {
        response.setStatus(HttpStatus.CONFLICT.value());
        e.printStackTrace();
        Map<String, String> errorMsg = BaseUtil.getErrorMsg(msgSource, e.getCode(), null);
        Map<String, String> localizedMessages = new HashMap<>();
        localizedMessages.put("english", errorMsg.get("english"));
        localizedMessages.put("indonesian", errorMsg.get("indonesian"));
        ErrorSchema errorSchema = new ErrorSchema(e.getCode(), localizedMessages);
        return new ResponseEntity<>(new WrapperResponse<>(null, errorSchema), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(AccessException.class)
    @ResponseBody
    public ResponseEntity<WrapperResponse<Void>> accessException(HttpServletResponse response, AccessException e) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        e.printStackTrace();
        Map<String, String> errorMsg = BaseUtil.getErrorMsg(msgSource, e.getCode(), null);
        Map<String, String> localizedMessages = new HashMap<>();
        localizedMessages.put("english", errorMsg.get("english"));
        localizedMessages.put("indonesian", errorMsg.get("indonesian"));
        ErrorSchema errorSchema = new ErrorSchema(e.getCode(), localizedMessages);
        return new ResponseEntity<>(new WrapperResponse<>(null, errorSchema), HttpStatus.FORBIDDEN);
    }
}
