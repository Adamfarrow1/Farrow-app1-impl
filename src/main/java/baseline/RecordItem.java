/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Adam Farrow
 */
package baseline;

import javafx.scene.control.CheckBox;

public class RecordItem {
    private String description;
    private String dueDate;
    private String status;
    private CheckBox select;
    //defines instance variables
    public RecordItem(String description, String dueDate, String complete) {
        // sets instance variables to the method arguments
        this.description = description;
        this.dueDate = dueDate;
        this.status = complete;
        this.select = new CheckBox();
    }

    public CheckBox getSelect(){
        //returns the check both
        return select;
    }
    public void setSelect(CheckBox select){
        //sets the select box
        this.select = select;
    }

    public String getStatus(){
        //returns complete
        return status;
    }
    public void setStatus(String status) {
        //sets complete
        this.status = status;
    }

    public void setDescription(String description){
        //sets the description
        this.description = description;
    }
    public void setDueDate(String dueDate){
        //sets the due date
        this.dueDate = dueDate;
    }

    public String getDescription() {
        //returns the description
        return description;
    }
    public String getDueDate(){
        //returns the due date
        return dueDate;
    }
}

