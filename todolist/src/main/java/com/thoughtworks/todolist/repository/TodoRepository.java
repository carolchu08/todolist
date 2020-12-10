package com.thoughtworks.todolist.repository;

import com.thoughtworks.todolist.model.Todo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoRepository extends MongoRepository<Todo, String> {
}