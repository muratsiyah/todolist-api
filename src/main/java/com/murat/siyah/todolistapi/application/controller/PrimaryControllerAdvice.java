package com.murat.siyah.todolistapi.application.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.murat.siyah.todolistapi.contract.exception.TodolistApiException;
import com.murat.siyah.todolistapi.contract.exception.model.ErrorDto;
import com.murat.siyah.todolistapi.contract.exception.model.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.stream.Collectors;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PrimaryControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(PrimaryControllerAdvice.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TodolistApiException.class)
    public ErrorDto apiException(TodolistApiException ex) {
        ErrorDto response = new ErrorDto();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessages(Collections.singletonList(new ErrorMessage("400.1", ex.getMessage())));

        logger.error("Catch a {} response: {}", ex, response);

        return response;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDto methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorDto response = new ErrorDto();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessages(ex.getBindingResult().getFieldErrors().stream()
                .map(it -> new ErrorMessage("400.2", String.format("%s: %s", it.getField(), it.getDefaultMessage())))
                .collect(Collectors.toList()));

        logger.error("Catch a MethodArgumentNotValidException response: {}", response);

        return response;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JsonParseException.class)
    public ErrorDto jsonParseException(JsonParseException ex) {
        ErrorDto response = new ErrorDto();
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setMessages(Collections.singletonList(new ErrorMessage("400.3", ex.getOriginalMessage())));

        logger.error("Catch a JsonParseException response: {}", response);

        return response;
    }

}
