package implementatons;

import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import model.ListOfItems;
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
import ui.DisplayInventory;
import ui.DisplaySearchItem;

import java.io.*;
import java.util.*;

import static javafx.scene.paint.Color.rgb;

public class InventoryCatalogue extends Application {
    private ListOfItems aggregatesList, turfList, paversList;
    private ArrayList<ListOfItems> itemsList;
    private Stage window;
    private Item i;
    private HashMap<String, ListOfItems> catalogue;


    // EFFECTS: constructs inventory catalogue with various loaded item lists
    public InventoryCatalogue() {
        catalogue = new HashMap<String, ListOfItems>();
        aggregatesList = new ListOfItems();
        turfList = new ListOfItems();
        paversList = new ListOfItems();

        itemsList = new ArrayList<ListOfItems>();
        itemsList.add(aggregatesList);
        itemsList.add(turfList);
        itemsList.add(paversList);

        try {
            LoadSave.load(aggregatesList, "aggregateOutput.txt");
            catalogue.put("Aggregates", aggregatesList);
            LoadSave.load(turfList, "turfOutput.txt");
            catalogue.put("Turf", turfList);
            LoadSave.load(paversList, "paverOutput.txt");
            catalogue.put("Pavers", paversList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws NumberFormatException {
        window = primaryStage;

        // --------------------------- EDIT INVENTORY SCENE 1 -------------------------------- \\

        // Popup label
        Label popup = new Label("");
        popup.setFont(Font.font("Verdana", 15));
        popup.setTextFill(Color.GREEN);

        // Title
        Label label1 = new Label("Edit Inventory");
        label1.setFont(Font.font ("Verdana", 30));
        label1.setTextFill(rgb(255, 140, 0));

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
                        "Turf",
                        "Pavers"
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
                addOperation(popup, action, name, category, amount);
            }
            else if (action.getValue().equals("Remove")) {
                removeOperation(action.getValue().toString(), popup, name, category, amount);
            }
            else if (action.getValue().equals("Delete")) {
                deleteOperation(action, popup, name, category);

            } else if (action.getValue().equals("Search")) {
                try {
                    i = findItem(name.getText(), catalogue);
                    DisplaySearchItem.display("Search Results", i.toString());
                } catch(NullPointerException n) {
                    showActionPopup("DNE", popup, null, null);
                }
            }
            else {
                System.out.println("Action not recognized.");
            }

        });

        // See Inventory Button
        Button doneButton = new Button("See Inventory");
        doneButton.setOnAction((ActionEvent event) -> {
            System.out.println(catalogue);
            DisplayInventory.display("Inventory", catalogue);
        });

        //Layout 1 - children laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(popup, label1, action, name, category, amount, button1, doneButton);
        layout1.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(layout1, 350, 400);

        // --------------------------- WINDOW CONFIGURATION -------------------------------- \\

        window.setOnCloseRequest(e -> closeOperation());

        //Display scene 1 at first
        window.setScene(scene1);
        window.setTitle("Edit Inventory");
        window.show();

    }

    // MODIFIES: this,
    // EFFECTS: deletes item from its category list
    private void deleteOperation(ComboBox action, Label popup, TextField name, ComboBox category) {
        ListOfItems mappedList = catalogue.get(category.getValue().toString());
        try {
            mappedList.deleteItem(name.getText());
            showActionPopup("Delete", popup, name, category.getValue().toString());
        } catch (NullPointerException e) {
            showActionPopup("DNE", popup, null, null);
        }
    }

    // MODIFIES: this
    // EFFECTS: performs removal of units from specified item
    private void removeOperation(String action, Label popup, TextField name, ComboBox category, TextField amount) {
        try {
            ListOfItems mappedList = catalogue.get(category.getValue().toString());
            i = mappedList.createItem(name.getText());
            if (i.getAmount() - Integer.parseInt(amount.getText()) < 0) {
                throw new NumberFormatException();
            } else {
                i.performRemoval(amount.getText());
                catalogue.replace(i.getCategory(), mappedList);
                showActionPopup(action, popup, name, category.getValue().toString());
            }

        } catch (NegativeNumberException | NumberFormatException e1) {
                showActionPopup("Error", popup, null, null);
        }
    }

    // MODIFIES: this, popup
    // EFFECTS: performs create and/or add on specified item
    private void addOperation(Label popup, ComboBox action, TextField name, ComboBox category, TextField amount) {
        try {
            ListOfItems mappedList = catalogue.get(category.getValue().toString());
            i = mappedList.createItem(name.getText());
            i.performAdd(amount.getText());
            showActionPopup(action.getValue().toString(), popup, name, category.getValue().toString());
            catalogue.replace(i.getCategory(), mappedList);
        } catch (NegativeNumberException ie) {
            System.out.println("Please enter a valid amount");
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a number");
        }
    }

    // MODIFIES: this, popup
    // EFFECTS: Displays a popup confirming that a user actions was completed
    private void showActionPopup(String action, Label popup, TextField name, String category) {
        if (action.equals("Add")) {
            popup.setText("'" + name.getText() + "'" + " added to " + category + " List");
        }
        else if (action.equals("Remove")) {
            popup.setText("Units removed from " + "'" + name.getText() + "'" + " in " + category + " List");
        }
        else if (action.equals("Delete")) {
            popup.setText("'" + name.getText() + "'" + " deleted from " + category + " List");
        }
        else if (action.equals("Error")) {
            popup.setTextFill(Color.RED);
            popup.setText("Not enough units of item to remove.");
        }
        else if (action.equals("DNE")) {
            popup.setTextFill(Color.RED);
            popup.setText("Item does not exist.");
        }
        else {
            popup.setTextFill(Color.RED);
            popup.setText("An error occurred.");
        }
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };

        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                popup.setText("");
                popup.setTextFill(Color.GREEN);
            }
        });
        new Thread(sleeper).start();
    }

    // REQUIRES: item is in one and only one of the category list
    // MODIFIES: this
    // EFFECTS: finds item with name name
    private Item findItem(String name, HashMap<String, ListOfItems> c) {
        for (ListOfItems l : itemsList) {
            if (l.contains(name)) {
                i = l.getItem(name);
                break;
            }
        }
        return i;
    }

    // MODIFIES: this
    // EFFECTS: saves inventory and then closes program window
    private void closeOperation() {
        try {
            LoadSave.save(aggregatesList.toString(), "aggregateOutput.txt");
            LoadSave.save(turfList.toString(), "turfOutput.txt");
            LoadSave.save(paversList.toString(), "paverOutput.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        window.close();
    }

}
