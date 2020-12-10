package com.thoughtworks.todolist.model;

public class TodoLabel {
    private String labelID;
    private String labelName;

    public TodoLabel(String labelName) {
        this.labelName = labelName;
    }

    public TodoLabel() {
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
}
