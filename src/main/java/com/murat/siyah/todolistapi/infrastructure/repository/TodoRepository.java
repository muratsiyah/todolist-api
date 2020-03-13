package com.murat.siyah.todolistapi.infrastructure.repository;

import com.murat.siyah.todolistapi.domain.todo.Todo;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@N1qlPrimaryIndexed
public interface TodoRepository extends CouchbaseRepository<Todo, String> {

    @Query("#{#n1ql.selectEntity} where #{#n1ql.filter} and `user` = $1 and META().id = $2 and `isDeleted` = false")
    Optional<Todo> findTodoByUserAndIdAndIsDeletedIsFalse(String user, String id);

    @Query("#{#n1ql.selectEntity} where #{#n1ql.filter} and META().id = $1 and `isDeleted` = false")
    Optional<Todo> findTodoByIdAndIsDeletedIsFalse(String id);

    @Query("#{#n1ql.selectEntity} where #{#n1ql.filter} and `user` = $1 and `isDeleted` = false")
    List<Todo> findTodosByUserAndIsDeletedIsFalse(String user);

}
