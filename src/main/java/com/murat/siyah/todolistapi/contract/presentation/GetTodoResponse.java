package com.murat.siyah.todolistapi.contract.presentation;

import com.murat.siyah.todolistapi.domain.todo.Todo;

public class GetTodoResponse {

    private String id;

    private String user;

    private String header;

    private String text;

    private String date;

    public GetTodoResponse(Todo todo) {
        id = todo.getId();
        user = todo.getUser();
        header = todo.getHeader();
        text = todo.getText();
        date = todo.getCreatedDate();
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getHeader() {
        return header;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }
}
