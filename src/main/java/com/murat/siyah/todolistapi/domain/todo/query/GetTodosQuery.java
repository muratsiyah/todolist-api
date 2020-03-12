package com.murat.siyah.todolistapi.domain.todo.query;

public class GetTodosQuery {

    private int page;

    private int size;

    private String user;

    public GetTodosQuery(int page, int size, String user) {
        this.page = page;
        this.size = size;
        this.user = user;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "GetTodosQuery{" +
                "page=" + page +
                ", size=" + size +
                ", user='" + user + '\'' +
                '}';
    }

}
