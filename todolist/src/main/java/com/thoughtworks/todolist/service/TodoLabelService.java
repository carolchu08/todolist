package com.thoughtworks.todolist.service;

import com.thoughtworks.todolist.model.Todo;
import com.thoughtworks.todolist.model.TodoLabel;
import com.thoughtworks.todolist.repository.TodoLabelRepository;
import com.thoughtworks.todolist.todoNotFoundException.TodoLabelNotFoundException;
import com.thoughtworks.todolist.todoNotFoundException.TodoNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoLabelService {
    @Autowired
    private final TodoLabelRepository todoLabelRepository;

    public TodoLabelService(TodoLabelRepository todoLabelRepository) {
        this.todoLabelRepository = todoLabelRepository;
    }
    public List<TodoLabel> getAll() {
        return this.todoLabelRepository.findAll();
    }
    public TodoLabel getOneTodoLabel(String id) throws TodoLabelNotFoundException {
        return this.todoLabelRepository.findById(id).orElseThrow(TodoLabelNotFoundException::new);
    }
    public TodoLabel createTodoLabel(TodoLabel newTodoLabel) {
        return this.todoLabelRepository.save(newTodoLabel);
    }
    public void deleteTodoLabel(String id) throws TodoLabelNotFoundException {
        this.getOneTodoLabel(id);
        this.todoLabelRepository.deleteById(id);
    }
    public TodoLabel updateTodoLabel(String id, TodoLabel updateTodoLabel) throws TodoLabelNotFoundException {
        this.getOneTodoLabel(id);
        updateTodoLabel.setLabelID(id);
        return this.todoLabelRepository.save(updateTodoLabel);
    }

}
