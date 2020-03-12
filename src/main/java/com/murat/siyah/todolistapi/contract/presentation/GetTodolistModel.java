package com.murat.siyah.todolistapi.contract.presentation;

import com.murat.siyah.todolistapi.domain.todo.Todo;

public class GetTodolistModel {

    private String id;

    private String header;

    private String text;

    public GetTodolistModel(Todo todo) {
        id = todo.getId();
        header = todo.getHeader();
        text = todo.getText();
    }

    public String getId() {
        return id;
    }

    public String getHeader() {
        return header;
    }

    public String getText() {
        return text;
    }

}
