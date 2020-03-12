package com.murat.siyah.todolistapi.application.controller;

import com.murat.siyah.todolistapi.application.handler.TodoCommandHandler;
import com.murat.siyah.todolistapi.application.handler.TodoQueryHandler;
import com.murat.siyah.todolistapi.contract.presentation.GetTodoResponse;
import com.murat.siyah.todolistapi.contract.presentation.GetTodolistResponse;
import com.murat.siyah.todolistapi.domain.todo.command.AddTodoCommand;
import com.murat.siyah.todolistapi.domain.todo.command.DeleteCommand;
import com.murat.siyah.todolistapi.domain.todo.command.UpdateTodoCommand;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("todo")
public class TodoController {

    private final TodoCommandHandler todoCommandHandler;

    private final TodoQueryHandler todoQueryHandler;

    public TodoController(TodoCommandHandler todoCommandHandler, TodoQueryHandler todoQueryHandler) {
        this.todoCommandHandler = todoCommandHandler;
        this.todoQueryHandler = todoQueryHandler;
    }

    @GetMapping("{id}")
    public GetTodoResponse getById(@PathVariable String id) {

        return todoQueryHandler.getTodoById();
    }

    @GetMapping
    public GetTodolistResponse get() {

        return todoQueryHandler.getTodos();
    }

    @PostMapping
    public void add(@RequestBody AddTodoCommand command) {
        todoCommandHandler.addTodo(command);
    }

    @PutMapping("{id}")
    public void update(@RequestBody UpdateTodoCommand command, @PathVariable String id) {
        command.setId(id);

        todoCommandHandler.updateTodo(command);
    }

    @DeleteMapping("{id}")
    public void delete(@RequestBody DeleteCommand command, @PathVariable String id) {
        command.setId(id);

        todoCommandHandler.deleteTodo(command);
    }

}
