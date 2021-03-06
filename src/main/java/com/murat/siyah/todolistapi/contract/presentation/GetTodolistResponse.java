package com.murat.siyah.todolistapi.contract.presentation;

import java.util.List;

public class GetTodolistResponse {

    private String user;

    private List<GetTodolistModel> todolist;

    public GetTodolistResponse(String user, List<GetTodolistModel> todolist) {
        this.user = user;
        this.todolist = todolist;
    }

    public String getUser() {
        return user;
    }


    public List<GetTodolistModel> getTodolist() {
        return todolist;
    }

}
