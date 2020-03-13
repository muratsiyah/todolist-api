package com.murat.siyah.todolistapi.domain.todo.command;

import javax.validation.constraints.NotBlank;

public class AddTodoCommand {

    @NotBlank
    private String user;

    @NotBlank
    private String header;

    @NotBlank
    private String text;

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
        return "AddTodoCommand{" +
                "user='" + user + '\'' +
                ", header='" + header + '\'' +
                ", text='" + text + '\'' +
                '}';
    }

}
