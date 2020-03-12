package com.murat.siyah.todolistapi.domain.todo;

import com.murat.siyah.todolistapi.domain.todo.command.AddTodoCommand;
import com.murat.siyah.todolistapi.domain.todo.command.UpdateTodoCommand;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document
public class Todo {

    @Id
    private String id;

    private String user;

    private String header;

    private String text;

    private String createdDate;

    private boolean isDeleted;

    public Todo() {

    }

    public Todo(AddTodoCommand command) {
        id = UUID.randomUUID().toString();
        user = command.getUser();
        header = command.getHeader();
        text = command.getText();
        createdDate = LocalDateTime.now().toString();
        isDeleted = true;
    }

    public void update(UpdateTodoCommand command) {
        header = command.getHeader();
        text = command.getText();
    }

    public void deleted() {
        isDeleted = false;
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

    public String getCreatedDate() {
        return createdDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

}
