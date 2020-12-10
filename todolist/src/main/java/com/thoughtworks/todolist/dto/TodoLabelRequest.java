package com.thoughtworks.todolist.dto;

public class TodoLabelRequest {
    private String labelName;
    private String color;

    public TodoLabelRequest(String labelName, String color) {
        this.labelName = labelName;
        this.color = color;
    }

    public TodoLabelRequest() {
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
