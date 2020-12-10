package com.thoughtworks.todolist.dto;

import com.thoughtworks.todolist.model.TodoLabel;

import java.util.List;

public class TodoRequest {
    private String text;
    private Boolean done;
    private List<TodoLabel> todoLabels;

    public TodoRequest(String text, Boolean done, List<TodoLabel> todoLabels) {
        this.text = text;
        this.done = done;
        this.todoLabels = todoLabels;
    }

    public TodoRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public List<TodoLabel> getTodoLabels() {
        return todoLabels;
    }

    public void setTodoLabels(List<TodoLabel> todoLabels) {
        this.todoLabels = todoLabels;
    }
}
