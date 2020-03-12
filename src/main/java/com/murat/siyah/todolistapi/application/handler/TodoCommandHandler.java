package com.murat.siyah.todolistapi.application.handler;

import com.murat.siyah.todolistapi.contract.presentation.GetTodoResponse;
import com.murat.siyah.todolistapi.domain.todo.Todo;
import com.murat.siyah.todolistapi.domain.todo.command.AddTodoCommand;
import com.murat.siyah.todolistapi.domain.todo.command.DeleteCommand;
import com.murat.siyah.todolistapi.domain.todo.command.UpdateTodoCommand;
import com.murat.siyah.todolistapi.domain.todo.exception.TodoNotFoundException;
import com.murat.siyah.todolistapi.infrastructure.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TodoCommandHandler {

    private final TodoRepository todoRepository;

    private final Logger logger = LoggerFactory.getLogger(TodoCommandHandler.class);

    public TodoCommandHandler(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public GetTodoResponse addTodo(AddTodoCommand command) {
        Todo todo = new Todo(command);

        logger.info("New Todo | Todo: {}", todo);

        todoRepository.save(todo);

        return new GetTodoResponse(todo);
    }

    public GetTodoResponse updateTodo(UpdateTodoCommand command) {
        Todo todo = todoRepository.findTodoByUserAndIdAndIsDeletedIsFalse(command.getUser(), command.getId())
                .orElseThrow(TodoNotFoundException::new);

        todo.update(command);

        logger.info("Todo update | Todo: {}", todo);

        todoRepository.save(todo);

        return new GetTodoResponse(todo);
    }

    public void deleteTodo(DeleteCommand command) {
        Todo todo = todoRepository.findTodoByUserAndIdAndIsDeletedIsFalse(command.getUser(), command.getId())
                .orElseThrow(TodoNotFoundException::new);

        todo.deleted();

        logger.info("Todo delete | Todo: {}", todo);

        todoRepository.save(todo);
    }

}
