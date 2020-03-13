package com.murat.siyah.todolistapi.domain.todo;

import com.murat.siyah.todolistapi.domain.todo.command.AddTodoCommand;
import com.murat.siyah.todolistapi.domain.todo.command.UpdateTodoCommand;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
class TodoTests {

    @Test
    public void Should_Be_Todo_Created_Function_Successfully() {
        AddTodoCommand command = new AddTodoCommand();
        command.setHeader("testHeader");
        command.setText("testText");
        command.setUser("testUser");

        Todo todo = new Todo(command);

        Assert.assertNotNull(todo.getId());
        Assert.assertEquals(todo.getUser(), "testUser");
        Assert.assertEquals(todo.getHeader(), "testHeader");
        Assert.assertEquals(todo.getText(), "testText");
        Assert.assertNotNull(todo.getCreatedDate());
        Assert.assertFalse(todo.isDeleted());
    }

    @Test
    public void Should_Be_Todo_Updated_Function_Successfully() {
        AddTodoCommand addTodoCommand = new AddTodoCommand();
        addTodoCommand.setHeader("testHeader");
        addTodoCommand.setText("testText");
        addTodoCommand.setUser("testUser");

        Todo todo = new Todo(addTodoCommand);

        UpdateTodoCommand command = new UpdateTodoCommand();
        command.setHeader("testHeader1");
        command.setText("testText1");

        todo.update(command);

        Assert.assertEquals(todo.getHeader(), "testHeader1");
        Assert.assertEquals(todo.getText(), "testText1");
    }

    @Test
    public void Should_Be_Todo_Updated_Function_Successfully_With_Empty_Model() {
        AddTodoCommand addTodoCommand = new AddTodoCommand();
        addTodoCommand.setHeader("testHeader");
        addTodoCommand.setText("testText");
        addTodoCommand.setUser("testUser");

        Todo todo = new Todo(addTodoCommand);

        UpdateTodoCommand command = new UpdateTodoCommand();

        todo.update(command);

        Assert.assertEquals(todo.getHeader(), "testHeader");
        Assert.assertEquals(todo.getText(), "testText");
    }


    @Test
    public void Should_Be_Todo_Deleted_Function_Successfully() {
        AddTodoCommand addTodoCommand = new AddTodoCommand();
        addTodoCommand.setHeader("testHeader");
        addTodoCommand.setText("testText");
        addTodoCommand.setUser("testUser");

        Todo todo = new Todo(addTodoCommand);

        todo.deleted();

        Assert.assertTrue(todo.isDeleted());
    }

    @Test
    public void Should_Be_Same_Values_Todos_Not_Equals_Control() {
        AddTodoCommand addTodoCommand = new AddTodoCommand();
        addTodoCommand.setHeader("testHeader");
        addTodoCommand.setText("testText");
        addTodoCommand.setUser("testUser");

        Todo todo1 = new Todo(addTodoCommand);
        Todo todo2 = new Todo(addTodoCommand);

        Assert.assertNotEquals(todo1, todo2);
    }
}