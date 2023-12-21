package com.example.replication.controller.exceptionHandler;

import com.example.replication.util.entity.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> TodoException(Exception ex, WebRequest request) {
        log.error("error" + request.getContextPath() + "-" + request.getSessionId() + "-" + ex.getMessage());
        return Response.error(HttpStatus.BAD_REQUEST, "Something wrong, try Again ");
    }
}