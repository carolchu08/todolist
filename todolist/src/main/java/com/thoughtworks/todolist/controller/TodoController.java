package com.thoughtworks.todolist.controller;

import com.thoughtworks.todolist.dto.TodoRequest;
import com.thoughtworks.todolist.dto.TodoResponse;
import com.thoughtworks.todolist.mapping.TodoMapper;
import com.thoughtworks.todolist.model.Todo;
import com.thoughtworks.todolist.service.TodoService;
import com.thoughtworks.todolist.todoNotFoundException.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoMapper todoMapper;
    @GetMapping
    public List<TodoResponse> getAll() {
        return todoService.getAll().stream().map(todoMapper::toResponse).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public TodoResponse getOneTodo (@PathVariable String id) throws TodoNotFoundException {
        return todoMapper.toResponse(todoService.getOneTodo(id));

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoResponse createTodo(@RequestBody TodoRequest todoRequest) {
        Todo todo = todoService.createTodo(todoMapper.toEntity(todoRequest));
        return todoMapper.toResponse(todo);
    }
    @PutMapping("/{id}")
    public TodoResponse updateTodo(@PathVariable String id, @RequestBody TodoRequest todoRequest) throws TodoNotFoundException {
        return todoMapper.toResponse(todoService.updateTodo(id, todoMapper.toEntity(todoRequest)));
    }
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable String id) throws TodoNotFoundException {
        todoService.deleteTodo(id);
    }

}
