package com.murat.siyah.todolistapi.contract.presentation;

import com.murat.siyah.todolistapi.domain.todo.Todo;
import com.murat.siyah.todolistapi.domain.todo.command.AddTodoCommand;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class GetTodoResponseTests {

    @Test
    public void Should_Be_GetTodolistModel_Created_Successfully_With_Constructor() {
        AddTodoCommand addTodoCommand = new AddTodoCommand();
        addTodoCommand.setHeader("testHeader");
        addTodoCommand.setText("testText");
        addTodoCommand.setUser("testUser");

        Todo todo = new Todo(addTodoCommand);

        GetTodoResponse getTodolistModel = new GetTodoResponse(todo);

        Assert.assertEquals(getTodolistModel.getId(), todo.getId());
        Assert.assertEquals(getTodolistModel.getUser(), todo.getUser());
        Assert.assertEquals(getTodolistModel.getHeader(), todo.getHeader());
        Assert.assertEquals(getTodolistModel.getText(), todo.getText());
        Assert.assertEquals(getTodolistModel.getCreatedDate(), todo.getCreatedDate());
    }


}