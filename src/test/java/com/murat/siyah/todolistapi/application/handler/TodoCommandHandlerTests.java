package com.murat.siyah.todolistapi.application.handler;

import com.murat.siyah.todolistapi.contract.presentation.GetTodoResponse;
import com.murat.siyah.todolistapi.domain.todo.Todo;
import com.murat.siyah.todolistapi.domain.todo.command.AddTodoCommand;
import com.murat.siyah.todolistapi.domain.todo.command.DeleteCommand;
import com.murat.siyah.todolistapi.domain.todo.command.UpdateTodoCommand;
import com.murat.siyah.todolistapi.domain.todo.exception.TodoNotFoundException;
import com.murat.siyah.todolistapi.infrastructure.repository.TodoRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TodoCommandHandlerTests {

    @InjectMocks
    private TodoCommandHandler todoCommandHandler;

    @Mock
    private TodoRepository todoRepository;

    @Captor
    private ArgumentCaptor<Todo> todoArgumentCaptor;

    @After
    public void after() {
        verifyNoMoreInteractions(todoRepository);
    }

    @Test
    public void Should_Be_Todo_Created_Successfully() {
        AddTodoCommand command = new AddTodoCommand();
        command.setHeader("testHeader");
        command.setText("testText");
        command.setUser("testUser");

        GetTodoResponse getTodoResponse = todoCommandHandler.addTodo(command);

        Assert.assertNotNull(getTodoResponse);

        verify(todoRepository).save(todoArgumentCaptor.capture());
    }

    @Test(expected = TodoNotFoundException.class)
    public void Should_Catch_TodoNotFoundException_For_Make_Updated() {
        UpdateTodoCommand command = new UpdateTodoCommand();
        command.setHeader("testHeader");
        command.setText("testText");

        when(todoRepository.findTodoByUserAndIdAndIsDeletedIsFalse(command.getUser(), command.getId())).thenReturn(Optional.empty());

        try {
            todoCommandHandler.updateTodo(command);
        } finally {
            verify(todoRepository).findTodoByUserAndIdAndIsDeletedIsFalse(command.getUser(), command.getId());
        }
    }

    @Test
    public void Should_Be_Todo_Updated_Successfully() {
        AddTodoCommand addTodoCommand = new AddTodoCommand();
        addTodoCommand.setHeader("testHeader");
        addTodoCommand.setText("testText");
        addTodoCommand.setUser("testUser");

        Todo todo = new Todo(addTodoCommand);

        UpdateTodoCommand command = new UpdateTodoCommand();
        command.setHeader("testHeader1");
        command.setText("testText1");

        when(todoRepository.findTodoByUserAndIdAndIsDeletedIsFalse(command.getUser(), command.getId())).thenReturn(Optional.of(todo));

        todoCommandHandler.updateTodo(command);

        verify(todoRepository).findTodoByUserAndIdAndIsDeletedIsFalse(command.getUser(), command.getId());
        verify(todoRepository).save(todo);
    }

    @Test(expected = TodoNotFoundException.class)
    public void Should_Catch_TodoNotFoundException_For_Make_Deleted() {
        DeleteCommand command = new DeleteCommand();
        command.setId("123");
        command.setUser("testUser");

        when(todoRepository.findTodoByUserAndIdAndIsDeletedIsFalse(command.getUser(), command.getId())).thenReturn(Optional.empty());

        try {
            todoCommandHandler.deleteTodo(command);
        } finally {
            verify(todoRepository).findTodoByUserAndIdAndIsDeletedIsFalse(command.getUser(), command.getId());
        }
    }

    @Test
    public void Should_Be_Todo_Deleted_Successfully() {
        AddTodoCommand addTodoCommand = new AddTodoCommand();
        addTodoCommand.setHeader("testHeader");
        addTodoCommand.setText("testText");
        addTodoCommand.setUser("testUser");

        Todo todo = new Todo(addTodoCommand);

        DeleteCommand command = new DeleteCommand();
        command.setId(todo.getId());
        command.setUser("testUser");

        when(todoRepository.findTodoByUserAndIdAndIsDeletedIsFalse(command.getUser(), command.getId())).thenReturn(Optional.of(todo));

        todoCommandHandler.deleteTodo(command);

        verify(todoRepository).findTodoByUserAndIdAndIsDeletedIsFalse(command.getUser(), command.getId());
        verify(todoRepository).save(todo);
    }

}