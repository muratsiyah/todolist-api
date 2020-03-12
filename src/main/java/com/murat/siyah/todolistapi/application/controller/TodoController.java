package com.murat.siyah.todolistapi.application.controller;

import com.murat.siyah.todolistapi.application.handler.TodoCommandHandler;
import com.murat.siyah.todolistapi.application.handler.TodoQueryHandler;
import com.murat.siyah.todolistapi.contract.presentation.GetTodoResponse;
import com.murat.siyah.todolistapi.contract.presentation.GetTodolistResponse;
import com.murat.siyah.todolistapi.domain.todo.command.AddTodoCommand;
import com.murat.siyah.todolistapi.domain.todo.command.DeleteCommand;
import com.murat.siyah.todolistapi.domain.todo.command.UpdateTodoCommand;
import com.murat.siyah.todolistapi.domain.todo.query.GetTodoByIdQuery;
import com.murat.siyah.todolistapi.domain.todo.query.GetTodosQuery;
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

    @GetMapping
    public GetTodolistResponse get(@RequestParam(defaultValue = "0", required = false) int page,
                                   @RequestParam(defaultValue = "100", required = false) int size,
                                   @RequestParam(required = false) String user) {
        GetTodosQuery query = new GetTodosQuery(page, size, user);

        return todoQueryHandler.getTodos(query);
    }

    @GetMapping("{id}")
    public GetTodoResponse getById(@PathVariable String id) {
        GetTodoByIdQuery query = new GetTodoByIdQuery(id);

        return todoQueryHandler.getTodoById(query);
    }

    @PostMapping
    public GetTodoResponse add(@RequestBody AddTodoCommand command) {
        return todoCommandHandler.addTodo(command);
    }

    @PutMapping("{id}")
    public GetTodoResponse update(@RequestBody UpdateTodoCommand command, @PathVariable String id) {
        command.setId(id);

        return todoCommandHandler.updateTodo(command);
    }

    @DeleteMapping("{id}")
    public void delete(@RequestBody DeleteCommand command, @PathVariable String id) {
        command.setId(id);

        todoCommandHandler.deleteTodo(command);
    }

}
