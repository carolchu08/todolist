package com.thoughtworks.todolist.todoNotFoundException;

public class TodoNotFoundException extends Exception{
    public TodoNotFoundException() {
        super("Todo item is not found");
    }
}
