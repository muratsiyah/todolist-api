package com.murat.siyah.todolistapi.domain.todo.exception;

import com.murat.siyah.todolistapi.contract.exception.TodolistApiException;

public class TodoNotFoundException extends TodolistApiException {

    public TodoNotFoundException() {
        super("Todo not found.");
    }

}
