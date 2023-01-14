package com.example.replication.util.entity;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

    private HttpStatus httpStatus;
    private String userMessage;
    private String code;
    private String[] prms;

    public AppException(String userMessage) {
        super(userMessage);
        this.httpStatus = HttpStatus.BAD_REQUEST;
        this.userMessage = userMessage;
    }

    public AppException(HttpStatus httpStatus, String userMessage) {
        super(userMessage);
        this.httpStatus = httpStatus;
        this.userMessage = userMessage;
    }

    public AppException(HttpStatus httpStatus, String userMessage, String code, String... prms) {
        super(userMessage);
        this.httpStatus = httpStatus;
        this.userMessage = userMessage;
        this.code = code;
        this.prms = prms;
    }

    public AppException(HttpStatus httpStatus, String userMessage, Throwable e) {
        super(userMessage, e);
        this.httpStatus = httpStatus;
        this.userMessage = userMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getCode() {
        return code;
    }

    public String[] getPrms() {
        return prms;
    }
}