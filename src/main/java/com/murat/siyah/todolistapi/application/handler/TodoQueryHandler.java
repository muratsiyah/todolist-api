package com.murat.siyah.todolistapi.application.handler;

import com.murat.siyah.todolistapi.contract.presentation.GetTodoResponse;
import com.murat.siyah.todolistapi.contract.presentation.GetTodolistModel;
import com.murat.siyah.todolistapi.contract.presentation.GetTodolistResponse;
import com.murat.siyah.todolistapi.domain.todo.Todo;
import com.murat.siyah.todolistapi.domain.todo.exception.TodoNotFoundException;
import com.murat.siyah.todolistapi.domain.todo.query.GetTodoByIdQuery;
import com.murat.siyah.todolistapi.domain.todo.query.GetTodosQuery;
import com.murat.siyah.todolistapi.infrastructure.repository.TodoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TodoQueryHandler {

    private final TodoRepository todoRepository;

    public TodoQueryHandler(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public GetTodoResponse getTodoById(GetTodoByIdQuery query) {
        Todo todo = todoRepository.findTodoIdAndIsDeletedIsFalse(query.getId())
                .orElseThrow(TodoNotFoundException::new);

        return new GetTodoResponse(todo);
    }

    public GetTodolistResponse getTodos(GetTodosQuery query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize(), Sort.Direction.DESC, "createdDate");

        List<GetTodolistModel> todolistModels = todoRepository.findTodosByUserAndIsDeletedIsFalse(pageable, query.getUser())
                .getContent()
                .stream()
                .map(GetTodolistModel::new)
                .collect(Collectors.toList());

        return new GetTodolistResponse(query.getUser(), todolistModels);
    }
}
