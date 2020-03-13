package com.murat.siyah.todolistapi.domain.todo.command;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;

public class UpdateTodoCommand {

    @JsonIgnore
    private String id;

    @NotBlank
    private String user;

    private String header;

    private String text;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "UpdateTodoCommand{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", header='" + header + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

}
