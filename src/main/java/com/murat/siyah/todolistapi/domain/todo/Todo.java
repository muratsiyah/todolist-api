package com.murat.siyah.todolistapi.domain.todo;

import com.murat.siyah.todolistapi.domain.todo.command.AddTodoCommand;
import com.murat.siyah.todolistapi.domain.todo.command.UpdateTodoCommand;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Objects;
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
        isDeleted = false;
    }

    public void update(UpdateTodoCommand command) {
        header = StringUtils.isEmpty(command.getHeader()) ? header : command.getHeader();
        text = StringUtils.isEmpty(command.getText()) ? text : command.getText();
    }

    public void deleted() {
        isDeleted = true;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", header='" + header + '\'' +
                ", text='" + text + '\'' +
                ", createdDate='" + createdDate + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }

}
