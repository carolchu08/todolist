package com.thoughtworks.todolist.todoNotFoundException;

public class TodoLabelNotFoundException extends Exception{
    public TodoLabelNotFoundException() {
        super("Todo item label is not found");
    }
}
