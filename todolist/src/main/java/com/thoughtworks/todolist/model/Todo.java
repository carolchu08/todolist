package com.thoughtworks.todolist.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.ArrayList;
import java.util.List;
@Document
public class Todo {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String text;
    private Boolean done;
    private List <TodoLabel> todoLabels;


    public Todo(String text, Boolean done, List<TodoLabel> todoLabels) {
        this.text = text;
        this.done = done;
        this.todoLabels = todoLabels;
    }

    public Todo(String id, String text, Boolean done, List<TodoLabel> todoLabels) {
        this.id = id;
        this.text = text;
        this.done = done;
        this.todoLabels = todoLabels;
    }

    public Todo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
