package com.thoughtworks.todolist.service;

import com.thoughtworks.todolist.model.Todo;
import com.thoughtworks.todolist.repository.TodoRepository;
import com.thoughtworks.todolist.todoNotFoundException.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    @Autowired
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getAll() {
        return this.todoRepository.findAll();
    }

    public Todo getOneTodo(String id) throws TodoNotFoundException {
        return this.todoRepository.findById(id).orElseThrow(TodoNotFoundException::new);
    }

    public Todo createTodo(Todo newTodo) {
        return this.todoRepository.save(newTodo);
    }

    public Todo updateTodo(String id, Todo updateTodo) throws TodoNotFoundException {
        this.getOneTodo(id);
        updateTodo.setId(id);
        return this.todoRepository.save(updateTodo);
    }

    public void deleteTodo(String id) throws TodoNotFoundException {
        this.getOneTodo(id);
        this.todoRepository.deleteById(id);
    }

}
