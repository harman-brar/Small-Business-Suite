package implementatons;

import category_lists.AggregatesList;
import category_lists.TurfList;
import exceptions.CapacityReachedException;
import exceptions.InvalidQuantityException;
import exceptions.NegativeNumberException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Item;
import model.ListOfItems;
import ui.DisplayInventory;
import ui.DisplaySearchItem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javafx.scene.paint.Color.rgb;

public class InventoryCatalogue extends Application {
    private ListOfItems aggregatesList, turfList;
    private Stage window;
    private Scene scene1, scene2;
    private Item i;
    private boolean append;


    public InventoryCatalogue() {
        aggregatesList = new AggregatesList();
        turfList = new TurfList();
        append = true;
    }

    @Override
    public void start(Stage primaryStage) throws NumberFormatException {
        window = primaryStage;

        // --------------------------- EDIT INVENTORY SCENE 1 -------------------------------- \\


        // Title
        Label label1 = new Label("Edit Inventory");
        label1.setFont(Font.font ("Verdana", 30));
        label1.setTextFill(rgb(255, 140, 0));

        // Load Previous Inventory button
        Button loadInventory = new Button("Load");
        loadInventory.setOnAction(event -> {
            try {
                LoadSave.load(aggregatesList, "aggregateOutput.txt");
                append = false;
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                LoadSave.load(turfList, "turfOutput.txt");
                append = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Options for action dropdown
        ObservableList<String> operations =
                FXCollections.observableArrayList(
                        "Add",
                        "Remove",
                        "Delete",
                        "Search"
                );
        // Action dropdown
        final ComboBox action = new ComboBox(operations);
        action.setMaxWidth(200);
        action.setValue("Action");

        // Name TextField
        TextField name = new TextField();
        name.setMaxWidth(200);
        name.setPromptText("Name of Item");

        // Options for category dropdown
        ObservableList<String> categories =
                FXCollections.observableArrayList(
                        "Aggregates",
                        "Turf"
                );
        // Category dropdown
        final ComboBox category = new ComboBox(categories);
        category.setMaxWidth(200);
        category.setValue("Category");

        // Amount TextField
        TextField amount = new TextField();
        amount.setMaxWidth(200);
        amount.setPromptText("Amount of Item (add, remove)");

        // Do it button
        Button button1 = new Button("Do it");
        button1.setOnAction(e -> {

            if (action.getValue().equals("Add")) {
                if (category.getValue().equals("Aggregates")) {
                    Item a = aggregatesList.createItem(name.getText());
                    a.setCategory("Aggregates");
                    try {
                        a.performAdd(amount.getText());
                    } catch (InvalidQuantityException ie) {
                        System.out.println("Please enter a valid amount");
                    } catch (NumberFormatException nfe) {
                        System.out.println("Please enter a number");
                    }
                } else if (category.getValue().equals("Turf")) {
                    Item t = turfList.createItem(name.getText());
                    t.setCategory("Turf");
                    try {
                        t.performAdd(amount.getText());
                    } catch (InvalidQuantityException ie) {
                        System.out.println("Please enter a valid amount");
                    } catch (NumberFormatException nfe) {
                        System.out.println("Please enter a number");
                    }

                }
            }
            else if (action.getValue().equals("Remove")) {
                if (category.getValue().equals("Aggregates")) {
                    Item a = aggregatesList.createItem(name.getText());

                    try {
                        a.performRemoval(amount.getText());
                    } catch (NegativeNumberException e1) {
                        System.out.println("Please enter a valid amount.");
                    } catch (NumberFormatException nfe) {
                        System.out.println("Please enter a valid number.");
                    }


                } else if (category.getValue().equals("Turf")) {
                    Item t = turfList.createItem(name.getText());
                    try {
                        t.performRemoval(amount.getText());
                    } catch (NegativeNumberException e1) {
                        System.out.println("Please enter a valid amount.");
                    } catch (NumberFormatException nfe) {
                        System.out.println("Please enter a valid number.");
                    }

                }
            }
            else if (action.getValue().equals("Delete")) {
                if (category.getValue().equals("Aggregates")) {
                    Item a = aggregatesList.createItem(name.getText());
                    aggregatesList.deleteItem(name.getText());
                } else if (category.getValue().equals("Turf")) {
                    Item t = turfList.createItem(name.getText());
                    turfList.deleteItem(name.getText());
                }
            } else if (action.getValue().equals("Search")) {
                i = findItem(name.getText());
                DisplaySearchItem.display("Search Results", i.toString());
            }
            else {
                System.out.println("Action not recognized.");
            }

        });

        // See Inventory Button
        Button doneButton = new Button("See Inventory");
        doneButton.setOnAction((ActionEvent event) -> {
            DisplayInventory.display("Inventory", aggregatesList.toString(), turfList.toString());
        });

        //Layout 1 - children laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, loadInventory, action, name, category, amount, button1, doneButton);
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1, 350, 400);

        // --------------------------- WINDOW CONFIGURATION -------------------------------- \\

        window.setOnCloseRequest(e -> closeOperation());

        //Display scene 1 at first
        window.setScene(scene1);
        window.setTitle("Edit Inventory");
        window.show();

    }
    // REQUIRES: item is in one and only one of the category list
    // MODIFIES: this
    // EFFECTS: finds item with name text
    private Item findItem(String text) {
        if (aggregatesList.getItem(text) != null) {
            i = aggregatesList.getItem(text);
        } else if (turfList.getItem(text) != null) {
            i = turfList.getItem(text);
        }
        return i;
    }

    // MODIFIES: this
    // EFFECTS: saves inventory and then closes program window
    private void closeOperation() {
        try {
            LoadSave.save(aggregatesList.toString(), "aggregateOutput.txt", append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            LoadSave.save(turfList.toString(), "turfOutput.txt", append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        window.close();
    }

}
