package com.thoughtworks.todolist.repository;

import com.thoughtworks.todolist.model.TodoLabel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TodoLabelRepository extends MongoRepository<TodoLabel, String> {
}
