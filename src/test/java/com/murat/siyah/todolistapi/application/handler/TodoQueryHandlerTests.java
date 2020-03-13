package com.murat.siyah.todolistapi.application.handler;

import com.murat.siyah.todolistapi.contract.presentation.GetTodoResponse;
import com.murat.siyah.todolistapi.contract.presentation.GetTodolistResponse;
import com.murat.siyah.todolistapi.domain.todo.Todo;
import com.murat.siyah.todolistapi.domain.todo.command.AddTodoCommand;
import com.murat.siyah.todolistapi.domain.todo.exception.TodoNotFoundException;
import com.murat.siyah.todolistapi.domain.todo.query.GetTodoByIdQuery;
import com.murat.siyah.todolistapi.domain.todo.query.GetTodosQuery;
import com.murat.siyah.todolistapi.infrastructure.repository.TodoRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TodoQueryHandlerTests {

    @InjectMocks
    private TodoQueryHandler todoQueryHandler;

    @Mock
    private TodoRepository todoRepository;

    @After
    public void after() {
        verifyNoMoreInteractions(todoRepository);
    }

    @Test(expected = TodoNotFoundException.class)
    public void Should_Catch_TodoNotFoundException_For_GetById() {
        GetTodoByIdQuery query = new GetTodoByIdQuery("123");

        when(todoRepository.findTodoByIdAndIsDeletedIsFalse(query.getId())).thenReturn(Optional.empty());

        try {
            todoQueryHandler.getTodoById(query);
        } finally {
            verify(todoRepository).findTodoByIdAndIsDeletedIsFalse(query.getId());
        }
    }

    @Test
    public void Should_Be_GetTodoById_Successfully() {
        AddTodoCommand command = new AddTodoCommand();
        command.setHeader("testHeader");
        command.setText("testText");
        command.setUser("testUser");

        Todo todo = new Todo(command);

        GetTodoByIdQuery query = new GetTodoByIdQuery("123");

        when(todoRepository.findTodoByIdAndIsDeletedIsFalse(query.getId())).thenReturn(Optional.of(todo));

        GetTodoResponse getTodoResponse = todoQueryHandler.getTodoById(query);

        verify(todoRepository).findTodoByIdAndIsDeletedIsFalse(query.getId());

        Assert.assertNotNull(getTodoResponse);
    }

    @Test
    public void Should_Be_GetTodos_Successfully() {
        AddTodoCommand command = new AddTodoCommand();
        command.setHeader("testHeader");
        command.setText("testText");
        command.setUser("testUser");

        Todo todo = new Todo(command);

        GetTodosQuery query = new GetTodosQuery("testUser");

        when(todoRepository.findTodosByUserAndIsDeletedIsFalse(query.getUser())).thenReturn(Collections.singletonList(todo));

        GetTodolistResponse getTodolistResponse = todoQueryHandler.getTodos(query);

        verify(todoRepository).findTodosByUserAndIsDeletedIsFalse(query.getUser());

        Assert.assertNotNull(getTodolistResponse);
        Assert.assertEquals(getTodolistResponse.getTodolist().size(), 1);
    }

    @Test
    public void Should_Be_GetTodos_Successfully_For_NotFoundTodo() {
        GetTodosQuery query = new GetTodosQuery("testUser");

        when(todoRepository.findTodosByUserAndIsDeletedIsFalse(query.getUser())).thenReturn(Collections.emptyList());

        GetTodolistResponse getTodolistResponse = todoQueryHandler.getTodos(query);

        verify(todoRepository).findTodosByUserAndIsDeletedIsFalse(query.getUser());

        Assert.assertNotNull(getTodolistResponse);
        Assert.assertEquals(getTodolistResponse.getTodolist().size(), 0);
    }


}