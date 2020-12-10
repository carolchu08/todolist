package com.thoughtworks.todolist.controller;

import com.thoughtworks.todolist.model.Todo;
import com.thoughtworks.todolist.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
public class TodoController {
    @Autowired
    private TodoService todoService;
    @GetMapping
    public List<Todo> getAll() {
        return todoService.getAll();
    }
}
