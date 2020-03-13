package com.murat.siyah.todolistapi.contract.presentation;

import com.murat.siyah.todolistapi.domain.todo.Todo;
import com.murat.siyah.todolistapi.domain.todo.command.AddTodoCommand;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class GetTodolistResponseTests {

    @Test
    public void Should_Be_GetTodolistModel_Created_Successfully_With_Constructor() {
        AddTodoCommand addTodoCommand = new AddTodoCommand();
        addTodoCommand.setHeader("testHeader");
        addTodoCommand.setText("testText");
        addTodoCommand.setUser("testUser");

        Todo todo = new Todo(addTodoCommand);

        GetTodolistModel getTodolistModel = new GetTodolistModel(todo);

        GetTodolistResponse getTodolistResponse = new GetTodolistResponse(todo.getUser(), Collections.singletonList(getTodolistModel));

        Assert.assertEquals(getTodolistResponse.getTodolist().get(0).getId(), todo.getId());
        Assert.assertEquals(getTodolistResponse.getTodolist().get(0).getHeader(), todo.getHeader());
        Assert.assertEquals(getTodolistResponse.getTodolist().get(0).getText(), todo.getText());
        Assert.assertEquals(getTodolistResponse.getUser(), "testUser");
    }

}