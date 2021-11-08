/*
 *  UCF COP3330 Fall 2021 Application Assignment 1 Solution
 *  Copyright 2021 Adam Farrow
 */
package baseline;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class ApplicationController {
    //defines FXML Buttons, Tables, TableColumns, TextField, TextArea

    @FXML
    private Text saveText;
    @FXML
    private Text loadText;
    @FXML
    private Text itemCounterText;
    @FXML
    private Text descriptionStatus;
    @FXML
    private Text dueDateStatus;
    @FXML
    private DatePicker date;
    @FXML
    private TableColumn<RecordItem, String> tableDescription;
    @FXML
    private TableColumn<RecordItem, String> tableDueDate;
    @FXML
    private TableColumn<RecordItem, String> tableStatus;
    @FXML
    private TableColumn<RecordItem, String> tableSelect;
    @FXML
    private TableView<RecordItem> table;
    @FXML
    private TextField saveName;
    @FXML
    private TextField savePath;
    @FXML
    private TextField loadPath;
    @FXML
    private TextArea addDescription;
    @FXML

    //define observable list as a class variable
    public ObservableList<RecordItem> items = FXCollections.observableArrayList();
    private static final String NOT_COMPLETE = "Not Complete";
    private int counter = 0;
    public int addItemToListPressed() {
        //when add item to list button is pressed add the values inside the description box to a String
        int flag;
        if(counter < 100) {
            itemCounterText.setText(" ");

            counter++;

            String temp = addDescription.getText();
            String formattedValue;
            flag = 0;
            //check to see if the string is 1 - 256 characters long if it does continue to check for the date else to nothing
            if (temp.length() > 0 && temp.length() <= 256) {
                //if the date equals to null then put nothing into the table else format the date to the proper format and put it into the table
                if (date.getValue() == null)
                    formattedValue = " ";
                else {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
                    formattedValue = (date.getValue()).format(formatter);
                    LocalDate dt = date.getValue();
                    if (dt.compareTo(LocalDate.now()) < 0) {
                        flag = 1;
                        itemCounterText.setText("Must be a valid date");
                    }
                }
                //add the items to the observable list
                //show the items in the table
                //clear the text field and the date picker
                if (flag == 0) {
                    items.add(new RecordItem(addDescription.getText(), formattedValue, NOT_COMPLETE));
                    table.setItems(items);
                    table.getColumns().get(0).setVisible(false);
                    table.getColumns().get(0).setVisible(true);
                    date.setValue(null);
                    addDescription.clear();
                }
            }
            else{
                itemCounterText.setText("Input must be between 1 and 256");
                return 1;
            }
        }
        else{
            itemCounterText.setText("You Have exceeded the 100 item limit!");
            return 1;
        }
        return 0;
    }

    public ObservableList<RecordItem> deleteItemPressed() {
        ObservableList<RecordItem> deletedItems = FXCollections.observableArrayList();
        //create an observable lists of deleteditems
        //iterate through the array and add all the selected items to the
        for(RecordItem temp : items){
            if(temp.getSelect().isSelected()) {
                deletedItems.add(temp);
                counter--;
            }
        }
        items.removeAll(deletedItems);
        return items;
    }


    public ObservableList<RecordItem> clearListPressed() {
        //clear the items and them update what's inside the table
        counter = 0;
        items.clear();
        if(table != null) {
            table.getColumns().get(0).setVisible(false);
            table.getColumns().get(0).setVisible(true);
        }
        return items;
    }

    public String dueDateEdit(TableColumn.CellEditEvent<RecordItem, String>recordItemStringCellEditEvent) {
        int flag = 0;
        DateTimeFormatter pattern;
        LocalDate datetime = null;
        if(recordItemStringCellEditEvent == null)
            return null;
        if (recordItemStringCellEditEvent.getNewValue().isEmpty()) {
                flag = 1;
            }
        StringBuilder tempData = new StringBuilder();
        tempData.append(recordItemStringCellEditEvent);

        for(int i = 0; i  < tempData.length(); i++){
            if(tempData.charAt(i) != '-')
                continue;
            if(Character.isLetter(tempData.charAt(i)))
                flag = 1;
        }
        if(flag == 0) {
            try {
                 pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                 datetime = LocalDate.parse(recordItemStringCellEditEvent.getNewValue(), pattern);
            } catch (DateTimeParseException e) {
                table.getColumns().get(0).setVisible(false);
                table.getColumns().get(0).setVisible(true);
                flag = 1;
            }
           if (datetime != null && datetime.compareTo(LocalDate.now()) < 0) {
                flag = 1;
            }
        }

        if (flag == 0) {
            RecordItem item = table.getSelectionModel().getSelectedItem();
            item.setDueDate(recordItemStringCellEditEvent.getNewValue());
            dueDateStatus.setText(" ");
            return item.getDueDate();
        }
        else {
            dueDateStatus.setText("Please enter numbers in the format yyyy-mm-dd and don't choose a date that has already passed");
            table.getColumns().get(0).setVisible(false);
            table.getColumns().get(0).setVisible(true);
        }

        return null;
    }

    public String descriptionEdit(TableColumn.CellEditEvent<RecordItem, String> recordItemStringCellEditEvent) {
        //set the data changed to the list and add it to the table
        if(recordItemStringCellEditEvent == null)
            return null;
        int flag = 0;
        if(recordItemStringCellEditEvent.getNewValue().isEmpty())
            flag = 1;
        if((recordItemStringCellEditEvent.getNewValue().length() > 0 && recordItemStringCellEditEvent.getNewValue().length() <= 256) && flag == 0)  {
            RecordItem item = table.getSelectionModel().getSelectedItem();
            item.setDescription(recordItemStringCellEditEvent.getNewValue());
            descriptionStatus.setText(" ");
            return recordItemStringCellEditEvent.getNewValue();
        }
        else{
            descriptionStatus.setText("Description must be between 1 and 256 characters long");
            table.getColumns().get(0).setVisible(false);
            table.getColumns().get(0).setVisible(true);
        }
        return null;
    }

    public int completePressed() {
        //iterate through the list and check if it is selected
        //if it is selected changed the status to complete
        if(table == null)
            return 1;
        int flag = 1;
        for(RecordItem temp : items){
            if(temp.getSelect().isSelected()) {
                temp.setStatus("Complete");
                flag = 0;
            }
        }
        table.getColumns().get(0).setVisible(false);
        table.getColumns().get(0).setVisible(true);
        if(flag == 0)
            return 0;
        else
            return 1;
    }



    public int showIncomplete() {
        if(table == null)
            return 1;
        int flag = 1;
        //create an observable list of incomplete items
        ObservableList<RecordItem> incompleteItems = FXCollections.observableArrayList();
        //iterate through the list and if the item status is not complete add it to the list
        for(RecordItem temp : items){
            if(temp.getStatus().equals(NOT_COMPLETE)) {
                incompleteItems.add(temp);
                flag = 0;
            }
        }
        //refresh to list and add the incomplete items list to the table
        table.getColumns().get(0).setVisible(false);
        table.getColumns().get(0).setVisible(true);
        table.setItems(incompleteItems);
        if(flag == 0)
            return 0;
        else
            return 1;
    }


    public  ObservableList<RecordItem> showComplete() {
        //create an observable list of completed items
        ObservableList<RecordItem> completedItems = FXCollections.observableArrayList();
        //iterate through the list if the status is complete then add it to the completed items list
        for(RecordItem temp : items){
            if(temp.getStatus().equals("Complete"))
                completedItems.add(temp);
        }
        //refresh the list and add it to the table
        if(table != null) {
            table.getColumns().get(0).setVisible(false);
            table.getColumns().get(0).setVisible(true);
            table.setItems(completedItems);
        }
        return completedItems;
    }

    public int showAllPressed() {
        //refresh the list and add every item from the items list
        if(table != null) {
            table.getColumns().get(0).setVisible(false);
            table.getColumns().get(0).setVisible(true);
            table.setItems(items);
            return 0;
        }
        return 1;
    }

    public void incompletePressed() {
        //iterate through the list if the item is selected then set the status to not complete
        for(RecordItem temp : items){
            if(temp.getSelect().isSelected()) {
                temp.setStatus(NOT_COMPLETE);
            }
        }
        table.getColumns().get(0).setVisible(false);
        table.getColumns().get(0).setVisible(true);

    }


    public int saveListPressed() throws IOException {
        if(savePath == null)
            return 1;
        String path = savePath.getText();
        String name = "\\" + saveName.getText() + ".txt";
        File file = new File(path);
        if(Files.exists(Path.of(path + name))) {
            saveText.setText("File already exists");
            return 1;
        }
            if (!file.exists()) {
                saveText.setText("Please enter a valid path.");
                return 1;
            } else {
            saveText.setText(" ");
            Files.createFile(Path.of(path + name));
            try (FileWriter fw = new FileWriter(path + name)) {
                for (RecordItem o : table.getItems()) {
                    fw.write(tableStatus.getCellData(o) + " " + tableDueDate.getCellData(o) + " " + tableDescription.getCellData(o));
                    fw.write("\n");
                }
            saveName.clear();
            savePath.clear();
            clearListPressed();
            }
        }
        return 0;
    }


    public int loadListPressed() throws IOException {
        if(loadPath == null){
            return 1;
        }
        String path = loadPath.getText();
        File file = new File(path + ".txt");
        items.clear();
        table.setItems(items);
        if(!file.exists()) {
            loadText.setText("Re-enter a valid path");
            return 1;
        }
            loadText.setText(" ");
            try (FileReader fr = new FileReader(path + ".txt")) {
                counter = 0;
                int i;
                int number = 0;
                int space = 0;
                StringBuilder status = new StringBuilder();
                StringBuilder dueDate = new StringBuilder();
                StringBuilder description = new StringBuilder();
                while ((i = fr.read()) != -1) {
                    if ((char) i == ' ') {
                        space++;
                    }
                    if (Character.isDigit((char) i) || (char) i == '-') {
                        number = 1;
                    }
                    if (number == 1 && Character.isLetter((char) i) || space == 3 || space == 4) {
                        number = 2;
                    }
                    if ((char) i == '\n') {
                        if (status.charAt(0) == '\n' || status.charAt(0) == '\r')
                            status.deleteCharAt(0);
                        number = 0;
                        items.add(new RecordItem(description.toString(), dueDate.toString(), status.toString()));
                        description.delete(0, description.length());
                        dueDate.delete(0, dueDate.length());
                        status.delete(0, status.length());
                        space = 0;
                        counter++;
                    }
                    if (number == 0)
                        status.append((char) i);
                    if (number == 1)
                        dueDate.append((char) i);
                    if (number == 2)
                        description.append((char) i);
                }
                table.setItems(items);
                loadPath.clear();
            }
        return 0;
    }

    public void initialize(){
        //initialize the columns to be added to and edited
        tableDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        tableDueDate.setCellValueFactory(new PropertyValueFactory<>("DueDate"));
        tableStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));
        tableSelect.setCellValueFactory(new PropertyValueFactory<>("Select"));

        tableDescription.setCellFactory(TextFieldTableCell.forTableColumn());
        tableDueDate.setCellFactory(TextFieldTableCell.forTableColumn());
    }


}
