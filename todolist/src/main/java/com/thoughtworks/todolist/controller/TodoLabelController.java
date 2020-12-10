package com.thoughtworks.todolist.controller;

import com.thoughtworks.todolist.dto.TodoLabelRequest;
import com.thoughtworks.todolist.dto.TodoLabelResponse;
import com.thoughtworks.todolist.dto.TodoResponse;
import com.thoughtworks.todolist.mapping.TodoLabelMapper;
import com.thoughtworks.todolist.model.TodoLabel;
import com.thoughtworks.todolist.service.TodoLabelService;
import com.thoughtworks.todolist.todoNotFoundException.TodoLabelNotFoundException;
import com.thoughtworks.todolist.todoNotFoundException.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/todoLabel")
public class TodoLabelController {
    @Autowired
    TodoLabelService todoLabelService;
    @Autowired
    TodoLabelMapper todoLabelMapper;
    @GetMapping
    public List<TodoLabelResponse> getAll() {
        return todoLabelService.getAll().stream().map(todoLabelMapper::toResponse).collect(Collectors.toList());
    }
    @GetMapping("/{labelID}")
    public TodoLabelResponse getOneTodoLabel (@PathVariable String labelID) throws TodoLabelNotFoundException {
        return todoLabelMapper.toResponse(todoLabelService.getOneTodoLabel(labelID));

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoLabelResponse createTodoLabel(@RequestBody TodoLabelRequest todoLabelRequest) {
        TodoLabel todoLabel = todoLabelService.createTodoLabel(todoLabelMapper.toEntity(todoLabelRequest));
        return todoLabelMapper.toResponse(todoLabel);
    }
    @PutMapping("/{labelID}")
    public TodoLabelResponse updateTodoLabel(@PathVariable String labelID, @RequestBody TodoLabelRequest todoLabelRequest) throws TodoLabelNotFoundException {
        return todoLabelMapper.toResponse(todoLabelService.updateTodoLabel(labelID, todoLabelMapper.toEntity(todoLabelRequest)));
    }
    @DeleteMapping("/{labelID}")
    public void deleteTodoLabel(@PathVariable String labelID) throws TodoLabelNotFoundException {
        todoLabelService.deleteTodoLabel(labelID);
    }
}
