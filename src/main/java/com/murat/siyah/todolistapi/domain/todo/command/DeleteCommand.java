package com.murat.siyah.todolistapi.domain.todo.command;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;

public class DeleteCommand {

    @JsonIgnore
    private String id;

    @NotBlank
    private String user;

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

    @Override
    public String toString() {
        return "DeleteCommand{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                '}';
    }

}
