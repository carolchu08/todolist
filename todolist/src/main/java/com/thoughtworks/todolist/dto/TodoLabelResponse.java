package com.thoughtworks.todolist.dto;

public class TodoLabelResponse {
    private String labelID;
    private String labelName;
    private String color;

    public TodoLabelResponse() {
    }

    public TodoLabelResponse(String labelID, String labelName, String color) {
        this.labelID = labelID;
        this.labelName = labelName;
        this.color = color;
    }

    public String getLabelID() {
        return labelID;
    }

    public void setLabelID(String labelID) {
        this.labelID = labelID;
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
