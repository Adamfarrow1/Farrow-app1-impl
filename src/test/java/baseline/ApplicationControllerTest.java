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
        //check to see if the data changed is the same as what's expected
    }

    @Test
    void descriptionEdit() {
        //check to see if the data changed is the same as what's expected
    }

    @Test
    void completePressed() {
        //check to see if the item is set to complete after the function is called
    }

    @Test
    void showIncomplete() {
        //check to see if the item is set to incomplete after the function is called
    }

    @Test
    void showComplete() {
        //check to see if the table view shows only the completed items
    }

    @Test
    void showAllPressed() {
        //check to see if the tableview shows all the items
    }

    @Test
    void incompletePressed() {
        //check to see if the table view shows only the incomplete items
    }
}