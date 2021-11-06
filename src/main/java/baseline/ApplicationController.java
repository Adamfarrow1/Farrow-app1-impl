/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Adam Farrow
 */
package baseline;

import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class ApplicationController {
        //defines FXML Buttons, Tables, TableColumns, TextField, TextArea
        //define observable list as a class variable
        public void addItemToListPressed(MouseEvent mouseEvent) {
            //when add item to list button is pressed add the values inside the description box to a String
            //check to see if the string is 1 - 256 characters long if it does continue to check for the date else to nothing
            //if the date equals to null then put nothing into the table else format the date to the proper format and put it into the table
            //add the items to the observable list
            //show the items in the table
            //clear the text field and the date picker
        }

    public void deleteItemPressed(MouseEvent mouseEvent) {
        //create an observable lists of deleteditems
        //iterate through the array and add all the selected items to the
    }


    public void clearListPressed(MouseEvent mouseEvent) {
            //clear the items and them update what's inside the table
    }

    public void dueDateEdit(TableColumn.CellEditEvent<RecordItem, String>recordItemStringCellEditEvent) {
            //create a formatter instance to see if the format is correct
            //if the format is correct then add the data to the list and update the table
            //if the format is incorrect refresh to table to see the previous data
    }

    public void descriptionEdit(TableColumn.CellEditEvent<RecordItem, String> recordItemStringCellEditEvent) {
            //set the data changed to the list and add it to the table
    }

    public void completePressed(MouseEvent mouseEvent) {
            //iterate through the list and check if it is selected
        //if it is selected changed the status to complete
    }



    public void showIncomplete(MouseEvent mouseEvent) {
            //create an observable list of incomplete items
        //iterate through the list and if the item status is not complete add it to the list
        //refresh to list and add the incomplete items list to the table
    }


    public void showComplete(MouseEvent mouseEvent) {
            //create an observable list of completed items
        //iterate through the list if the status is complete then add it to the completed items list
        //refresh the list and add it to the table
    }

    public void showAllPressed(MouseEvent mouseEvent) {
            //refresh the list and add every item from the items list
    }

    public void incompletePressed(MouseEvent mouseEvent) {
            //iterate through the list if the item is selected then set the status to not complete
    }

    public void initialize(){
            //initialize the columns to be added to and edited
    }



}
