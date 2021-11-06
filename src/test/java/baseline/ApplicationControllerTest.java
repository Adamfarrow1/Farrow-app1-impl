/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Adam Farrow
 */
package baseline;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationControllerTest {

    @Test
    void addItemToListPressed() {
        //create an observable list with the value already inside
        //call the function with the data to add to the list
        //check to see if both of the lists are the same
    }

    @Test
    void deleteItemPressed() {
        //create an observable list with the value already deleted
        //pass a list with the parameters of the item to delete to the function
        //compare the 2 lists

    }

    @Test
    void clearListPressed() {
        //check to see if the list is empty after the function is called
    }

    @Test
    void dueDateEdit() {
        //create an instance of RecordItem with the expected changed already made
        //pass another instance without the changes through the function
        //check to see if they are the same
    }

    @Test
    void descriptionEdit() {
        //create an instance of the class Record Items where the Description is what is expected to change
        //compare what is expected to change and what actually changes
    }

    @Test
    void completePressed() {
        //create an instance where it is set to complete
        // call the function and see if an instance of the class record items is changed to complete
    }

    @Test
    void showIncomplete() {
        //create an instance where it is set to incomplete
        // call the function and see if an instance of the class record items is changed to incomplete
    }

    @Test
    void showComplete() {
        //check to see if the table view shows only the completed items after the function is called
    }

    @Test
    void showAllPressed() {
        //check to see if the tableview shows all the items after the function is called
    }

    @Test
    void incompletePressed() {
        //check to see if the table view shows only the incomplete items after the function is called
    }
}