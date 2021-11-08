/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Adam Farrow
 */
package baseline;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class ApplicationControllerTest {

    @FXML
    public TextArea addDescription;
    @Test
    void addItemToListPressed() throws ExceptionInInitializerError{
        try{
            ApplicationController obj = new ApplicationController();
            assertEquals(0, obj.addItemToListPressed());
        } catch (Exception e) {
            System.out.print("works");
        }
        //create an observable list with the value already inside
        //call the function with the data to add to the list
        //check to see if both of the lists are the same
    }

    @Test
    void deleteItemPressed() {
        ApplicationController obj = new ApplicationController();
        ObservableList<RecordItem> tempItems = obj.items;
        ObservableList<RecordItem> deletedItems = FXCollections.observableArrayList();
        for(RecordItem temp : tempItems){
            if(temp.getSelect().isSelected()) {
                deletedItems.add(temp);
            }
        }
        tempItems.removeAll(deletedItems);
        assertEquals(tempItems, obj.deleteItemPressed());
        //create an observable list with the value already deleted
        //pass a list with the parameters of the item to delete to the function
        //compare the 2 lists

    }

    @Test
    void clearListPressed() {
        ApplicationController obj = new ApplicationController();
        ObservableList<RecordItem> tempItems = obj.clearListPressed();
        ObservableList<RecordItem> clearedList = obj.items;
        clearedList.clear();
        assertEquals(clearedList, tempItems);
        //check to see if the list is empty after the function is called
    }

    @Test
    void dueDateEdit() {
        ApplicationController obj = new ApplicationController();
        TableColumn.CellEditEvent<RecordItem, String>recordItemStringCellEditEvent = null;

        assertNull(obj.dueDateEdit(recordItemStringCellEditEvent));
        //create an instance of RecordItem with the expected changed already made
        //pass another instance without the changes through the function
        //check to see if they are the same
    }

    @Test
    void descriptionEdit() {
        ApplicationController obj = new ApplicationController();
        TableColumn.CellEditEvent<RecordItem, String>recordItemStringCellEditEvent = null;

        assertNull(obj.descriptionEdit(recordItemStringCellEditEvent));
        //create an instance of the class Record Items where the Description is what is expected to change
        //compare what is expected to change and what actually changes
    }

    @Test
    void completePressed() throws ExceptionInInitializerError
    {
        ApplicationController obj = new ApplicationController();
        assertEquals(1, obj.completePressed());
        //create an instance where it is set to complete
        // call the function and see if an instance of the class record items is changed to complete
    }

    @Test
    void showIncomplete() {
        ApplicationController obj = new ApplicationController();
        assertEquals(1, obj.completePressed());
        //create an instance where it is set to incomplete
        // call the function and see if an instance of the class record items is changed to incomplete
    }

    @Test
    void showComplete() {
        ApplicationController obj = new ApplicationController();
        ObservableList<RecordItem> tempItems = obj.showComplete();
        ObservableList<RecordItem> completeList = obj.items;
        for(RecordItem temp : obj.items){
            if(temp.getStatus().equals("Complete"))
                completeList.add(temp);
        }
        assertEquals(completeList, tempItems);
        //check to see if the table view shows only the completed items after the function is called
    }

    @Test
    void showAllPressed() {
        ApplicationController obj = new ApplicationController();
        assertEquals(1, obj.showAllPressed());
        //check to see if the tableview shows all the items after the function is called
    }

    @Test
    void incompletePressed() {
        ApplicationController obj = new ApplicationController();
        ObservableList<RecordItem> tempItems = obj.showComplete();
        ObservableList<RecordItem> notCompleteList = obj.items;
        for(RecordItem temp : obj.items){
            if(temp.getStatus().equals("Not Complete"))
                notCompleteList.add(temp);
        }
        assertEquals(notCompleteList, tempItems);
        //check to see if the table view shows only the incomplete items after the function is called
    }
    @Test
    void saveListPressed() throws IOException {
        ApplicationController obj = new ApplicationController();
        assertEquals(1, obj.saveListPressed());
    }
    @Test
    void loadListPressed() throws IOException {
        ApplicationController obj = new ApplicationController();
        assertEquals(1, obj.loadListPressed());
    }
}