package com.thoughtworks.todolist.mapping;

import com.thoughtworks.todolist.dto.TodoLabelRequest;
import com.thoughtworks.todolist.dto.TodoLabelResponse;
import com.thoughtworks.todolist.model.TodoLabel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class TodoLabelMapper {
    public TodoLabel toEntity(TodoLabelRequest todoLabelRequest) {
        TodoLabel todoLabel = new TodoLabel();

        BeanUtils.copyProperties(todoLabelRequest,todoLabel);
        return todoLabel;
    }

    public TodoLabelResponse toResponse(TodoLabel todoLabel) {
        TodoLabelResponse todoLabelResponse = new TodoLabelResponse();
        BeanUtils.copyProperties(todoLabel, todoLabelResponse);
        return todoLabelResponse;
    }
}
