package com.thoughtworks.todolist.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
public class TodoLabel {
    @MongoId(FieldType.OBJECT_ID)
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
