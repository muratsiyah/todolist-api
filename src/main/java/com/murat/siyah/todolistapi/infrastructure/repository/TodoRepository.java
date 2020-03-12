package com.murat.siyah.todolistapi.infrastructure.repository;

import com.murat.siyah.todolistapi.domain.todo.Todo;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@N1qlPrimaryIndexed
public interface TodoRepository extends CouchbaseRepository<Todo, String> {

    Optional<Todo> findTodoByUserAndIdAndIsDeletedIsFalse(String user, String id);

    Optional<Todo> findTodoIdAndIsDeletedIsFalse(String id);

    Page<Todo> findTodosByUserAndIsDeletedIsFalse(Pageable pageable, String user);

}
