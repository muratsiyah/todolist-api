package com.murat.siyah.todolistapi.domain.todo.query;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class GetTodoByIdQuery {

    @JsonIgnore
    private String id;

    public GetTodoByIdQuery(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GetTodoByIdQuery{" +
                "id='" + id + '\'' +
                '}';
    }

}
