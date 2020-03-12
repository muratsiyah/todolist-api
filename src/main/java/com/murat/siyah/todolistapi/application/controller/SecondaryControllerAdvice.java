package com.murat.siyah.todolistapi.application.controller;

import com.murat.siyah.todolistapi.contract.exception.model.ErrorDto;
import com.murat.siyah.todolistapi.contract.exception.model.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.CompletionException;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class SecondaryControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(SecondaryControllerAdvice.class);

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ErrorDto notExpectedError(Throwable throwable) {
        if (throwable instanceof CompletionException && Objects.nonNull(throwable.getCause())) {
            throwable = throwable.getCause();
        }

        logger.error("Catch an unexpected exception: ", throwable);

        ErrorDto response = new ErrorDto();
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setMessages(Collections.singletonList(new ErrorMessage("500.1", throwable.getMessage())));

        return response;
    }

}
