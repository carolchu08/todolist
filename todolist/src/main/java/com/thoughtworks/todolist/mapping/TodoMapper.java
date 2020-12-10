package com.thoughtworks.todolist.mapping;

import com.thoughtworks.todolist.dto.TodoRequest;

import com.thoughtworks.todolist.dto.TodoResponse;
import com.thoughtworks.todolist.model.Todo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {
    public Todo toEntity(TodoRequest todoRequest) {
        Todo todo = new Todo();

        BeanUtils.copyProperties(todoRequest,todo);
        return todo;
    }

    public TodoResponse toResponse(Todo todo) {
        TodoResponse todoResponse = new TodoResponse();
        BeanUtils.copyProperties(todo, todoResponse);
        return todoResponse;
    }
}
