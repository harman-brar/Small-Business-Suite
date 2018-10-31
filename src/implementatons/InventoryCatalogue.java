package implementatons;

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
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static javafx.scene.paint.Color.rgb;

public class InventoryCatalogue extends Application {
    private ListOfItems aggregatesList, turfList;
    private Stage window;
    private Scene scene1, scene2;
    private Item i;
    private boolean append, preloaded;
    private HashMap<String, ListOfItems> catalogue;


    public InventoryCatalogue() {
        catalogue = new HashMap<String, ListOfItems>();
        aggregatesList = new ListOfItems();
        turfList = new ListOfItems();
        append = true;
        preloaded = false;
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

        // Load Previous Inventory button
        Button loadInventory = new Button("Load");
        loadInventory.setOnAction(event -> {
            try {
                if (!preloaded) {
                    LoadSave.load(aggregatesList, "aggregateOutput.txt");
                    catalogue.put("Aggregates", aggregatesList);
                    append = false;
                    showActionPopup((Object) "Load", popup, null, null);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (!preloaded) {
                LoadSave.load(turfList, "turfOutput.txt");
                catalogue.put("Turf", turfList);
                append = false;
                preloaded = true;
                }
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
                addOperation(popup, action, name, category, amount);
            }
            else if (action.getValue().equals("Remove")) {
                removeOperation(name, category, amount);
                showActionPopup(action.getValue(), popup, name, category);
            }
            else if (action.getValue().equals("Delete")) {
                deleteOperation(name, category);
                showActionPopup(action.getValue(), popup, name, category);

            } else if (action.getValue().equals("Search")) {
                try {
                    i = findItem(name.getText());
                    DisplaySearchItem.display("Search Results", i.toString());
                } catch(NullPointerException n) {
                    popup.setTextFill(Color.RED);
                    popup.setText("Item not found.");
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
                        }
                    });
                    new Thread(sleeper).start();
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
        layout1.getChildren().addAll(popup, label1, loadInventory, action, name, category, amount, button1, doneButton);
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1, 350, 400);

        // --------------------------- WINDOW CONFIGURATION -------------------------------- \\

        window.setOnCloseRequest(e -> closeOperation());

        //Display scene 1 at first
        window.setScene(scene1);
        window.setTitle("Edit Inventory");
        window.show();

    }

    // MODIFIES: this,
    // EFFECTS: deletes item from its category list
    private void deleteOperation(TextField name, ComboBox category) {
        if (category.getValue().equals("Aggregates")) {
            Item a = aggregatesList.createItem(name.getText());
            aggregatesList.deleteItem(name.getText());
            catalogue.replace("Aggregates", aggregatesList);
        } else if (category.getValue().equals("Turf")) {
            Item t = turfList.createItem(name.getText());
            turfList.deleteItem(name.getText());
            catalogue.replace("Turf", turfList);
        }
    }

    // MODIFIES: this
    // EFFECTS: performs removal of units from specified item
    private void removeOperation(TextField name, ComboBox category, TextField amount) {
        if (category.getValue().equals("Aggregates")) {
            Item a = aggregatesList.createItem(name.getText());

            try {
                a.performRemoval(amount.getText());
            } catch (NegativeNumberException e1) {
                System.out.println("Please enter a valid amount.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid number.");
            }
            catalogue.replace("Aggregates", aggregatesList);

        } else if (category.getValue().equals("Turf")) {
            Item t = turfList.createItem(name.getText());
            try {
                t.performRemoval(amount.getText());
            } catch (NegativeNumberException e1) {
                System.out.println("Please enter a valid amount.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a valid number.");
            }
            catalogue.replace("Turf", turfList);
        }
    }

    // MODIFIES: this, popup
    // EFFECTS: performs create and/or add on specified item
    private void addOperation(Label popup, ComboBox action, TextField name, ComboBox category, TextField amount) {
        if (category.getValue().equals("Aggregates")) {
            Item a = aggregatesList.createItem(name.getText());
            a.setCategory("Aggregates");
            try {
                a.performAdd(amount.getText());
            } catch (NegativeNumberException ie) {
                System.out.println("Please enter a valid amount");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a number");
            }
            catalogue.replace("Aggregates", aggregatesList);
            showActionPopup(action.getValue(), popup, name, category);

        } else if (category.getValue().equals("Turf")) {
            Item t = turfList.createItem(name.getText());
            t.setCategory("Turf");
            try {
                t.performAdd(amount.getText());
            } catch (NegativeNumberException ie) {
                System.out.println("Please enter a valid amount");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter a number");
            }
            catalogue.replace("Turf", turfList);
            showActionPopup(action.getValue(), popup, name, category);
        }
    }

    // MODIFIES: this, popup
    // EFFECTS: Displays a popup confirming that a user actions was completed
    private void showActionPopup(Object action, Label popup, TextField name, ComboBox category) {
        String act = action.toString();
        if (act.equals("Add")) {
            popup.setText("'" + name.getText() + "'" + " added to " + category.getValue() + " List");
        }
        else if (act.equals("Remove")) {
            popup.setText("Units removed from " + "'" + name.getText() + "'" + " in " + category.getValue() + " List");
        }
        else if (act.equals("Delete")) {
            popup.setText("'" + name.getText() + "'" + " deleted from " + category.getValue() + " List");
        }
        else if (act.equals("Load")) {
            popup.setText("Inventory loaded");
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
            }
        });
        new Thread(sleeper).start();
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
