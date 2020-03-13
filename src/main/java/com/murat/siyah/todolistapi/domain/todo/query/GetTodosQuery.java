package com.murat.siyah.todolistapi.domain.todo.query;

public class GetTodosQuery {

    private String user;

    public GetTodosQuery(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "GetTodosQuery{" +
                "user='" + user + '\'' +
                '}';
    }

}
