package com.murat.siyah.todolistapi.infrastructure.repository;

import com.murat.siyah.todolistapi.domain.todo.Todo;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
@N1qlPrimaryIndexed
public interface TodoRepository extends CouchbaseRepository<Todo, String> {
}
