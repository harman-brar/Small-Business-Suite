package implementatons.inventory_model;

import implementatons.tab_model.*;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import model.ListOfItems;
import exceptions.NegativeNumberException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Item;

import javax.swing.*;
import java.io.*;
import java.util.*;

import static javafx.scene.paint.Color.rgb;

public class InventoryCatalogue extends Application {
    private static Stage window;
    private Item i;
    private HashMap<String, ListOfItems> catalogue;

    @FXML
    private Label msgBar;
    @FXML
    private ComboBox<String> actionCombo;
    @FXML
    private ComboBox<String> categoryCombo;
    @FXML
    private TextField nameBar;
    @FXML
    private TextField amountBar;
    @FXML
    private Button goButton;
    @FXML
    private Button accButton;
    @FXML
    private Button printButton;
    @FXML
    private Button newInvButton;
    @FXML
    private Tab plantTab;
    @FXML
    private Tab smTab;
    @FXML
    private Tab rwsTab;
    @FXML
    private Tab blocksTab;
    @FXML
    private Tab turfTab;
    @FXML
    private Tab paversTab;
    @FXML
    private Tab packagedTab;
    @FXML
    private Tab aggregatesTab;
    @FXML
    private Tab toolsTab;
    @FXML
    private Tab tpTab;

    @FXML
    private void initialize() {
        assert msgBar != null : "fx:id=\"msgBar\" was not injected: check your FXML file 'inventoryTracker.fxml'.";
        assert actionCombo != null : "fx:id=\"actionCombo\" was not injected: check your FXML file 'inventoryTracker.fxml'.";
        assert categoryCombo != null : "fx:id=\"categoryCombo\" was not injected: check your FXML file 'inventoryTracker.fxml'.";
        assert nameBar != null : "fx:id=\"nameBar\" was not injected: check your FXML file 'inventoryTracker.fxml'.";
        assert amountBar != null : "fx:id=\"amountBar\" was not injected: check your FXML file 'inventoryTracker.fxml'.";
        assert goButton != null : "fx:id=\"goButton\" was not injected: check your FXML file 'inventoryTracker.fxml'.";
        assert accButton != null : "fx:id=\"accButton\" was not injected: check your FXML file 'inventoryTracker.fxml'.";
        assert printButton != null : "fx:id=\"printButton\" was not injected: check your FXML file 'inventoryTracker.fxml'.";
        assert newInvButton != null : "fx:id=\"newInvButton\" was not injected: check your FXML file 'inventoryTracker.fxml'.";

        assert plantTab != null : "fx:id=\"plantTab\" was not injected: check your FXML file 'inventoryTracker.fxml'.";
        assert smTab != null : "fx:id=\"smTab\" was not injected: check your FXML file 'inventoryTracker.fxml'.";
        assert rwsTab != null : "fx:id=\"rwsTab\" was not injected: check your FXML file 'inventoryTracker.fxml'.";
        assert blocksTab != null : "fx:id=\"blocksTab\" was not injected: check your FXML file 'inventoryTracker.fxml'.";
        assert turfTab != null : "fx:id=\"turfTab\" was not injected: check your FXML file 'inventoryTracker.fxml'.";
        assert paversTab != null : "fx:id=\"paversTab\" was not injected: check your FXML file 'inventoryTracker.fxml'.";
        assert packagedTab != null : "fx:id=\"packagedTab\" was not injected: check your FXML file 'inventoryTracker.fxml'.";
        assert aggregatesTab != null : "fx:id=\"aggregatesTab\" was not injected: check your FXML file 'inventoryTracker.fxml'.";
        assert toolsTab != null : "fx:id=\"toolsTab\" was not injected: check your FXML file 'inventoryTracker.fxml'.";
        assert tpTab != null : "fx:id=\"tpTab\" was not injected: check your FXML file 'inventoryTracker.fxml'.";

        setTabControllers();
    }

    private void setTabControllers() {
        setTabContent(plantTab, new plantTabController(this));
        setTabContent(smTab, new smTabController(this));
        setTabContent(rwsTab, new rwsTabController(this));
        setTabContent(blocksTab, new blockTabController(this));
        setTabContent(turfTab, new turfTabController(this));
        setTabContent(paversTab, new paverTabController(this));
        setTabContent(packagedTab, new packagedTabController(this));
        setTabContent(aggregatesTab, new aggregateTabController(this));
        setTabContent(toolsTab, new toolTabController(this));
        setTabContent(tpTab, new tpTabController(this));
    }

    private void setTabContent(Tab tab, TabController controller) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../ui/resources/tabContent.fxml"));
            loader.setController(controller);
            tab.setContent(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: constructs inventory catalogue with various loaded item lists
    public InventoryCatalogue() {
        try {
            catalogue = LoadSave.load();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Inventory UI
    @Override
    public void start(Stage primaryStage) throws Exception  {
        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("../../ui/resources/inventoryTracker.fxml"));
        primaryStage.setTitle("Inventory Tracker");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    // MODIFIES: this
    // EFFECTS: carries out action specified by user
    @FXML
    private void goButtonHit() {
        String action = actionCombo.getValue();
        String category = categoryCombo.getValue();

        if (action.equals("Add")) {
            addOperation(nameBar.getText(), category, amountBar.getText());
        }
        else if (action.equals("Remove")) {
            removeOperation(nameBar.getText(), category, amountBar.getText());
        }
        else if (action.equals("Delete")) {
            deleteOperation(nameBar.getText(), category);
        }
        else {
            showActionPopup("NoAction", null, null);
        }
        setTabControllers();
    }

    // EFFECTS: launches new invoice builder window
    @FXML
    private void newInvoice() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/resources/createInvoice.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("New Invoice (1)");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void seeAccounts() {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../ui/resources/accountManager.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.DECORATED);
            stage.setTitle("Accounts Manager");
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MODIFIES: this,
    // EFFECTS: deletes item from its category list
    private void deleteOperation(String name, String category) {
        ListOfItems mappedList = catalogue.get(category);
        try {
            mappedList.deleteItem(name);
            showActionPopup("Delete", name, category);
        } catch (NullPointerException e) {
            showActionPopup("DNE",null, null);
        }
        saveOperation(catalogue);
    }

    // MODIFIES: this
    // EFFECTS: performs removal of units from specified item
    private void removeOperation(String name, String category, String amount) {
        try {
            ListOfItems mappedList = catalogue.get(category);
            i = mappedList.createItem(name, category);
            if (i.getAmount() - Integer.parseInt(amount) < 0) {
                throw new NumberFormatException();
            } else {
                i.performRemoval(amount);
                catalogue.replace(i.getCategory(), mappedList);
                showActionPopup(actionCombo.getValue(), name, category);
            }

        } catch (NegativeNumberException | NumberFormatException e1) {
            showActionPopup("Error",null, null);
        }
        saveOperation(catalogue);
    }

    // MODIFIES: this, popup
    // EFFECTS: performs create and/or add on specified item
    private void addOperation(String name, String category, String amount) {
        try {
            ListOfItems mappedList = catalogue.get(category);
            i = mappedList.createItem(name, category);
            i.performAdd(amount);
            showActionPopup(actionCombo.getValue(), name, category);
            catalogue.replace(i.getCategory(), mappedList);
        } catch (NegativeNumberException | NumberFormatException ie) {
            showActionPopup("Number Error", null, null);
        }
        saveOperation(catalogue);
    }

    // MODIFIES: this, popup
    // EFFECTS: Displays a popup confirming that a user actions was completed
    private void showActionPopup(String action, String name, String category) {
        msgBar.setTextFill(Color.GREEN);
        if (action.equals("Add")) {
            msgBar.setText("'" + name + "'" + " added to " + category + " List");
        }
        else if (action.equals("Remove")) {
            msgBar.setText("Units removed from " + "'" + name + "'" + " in " + category + " List");
        }
        else if (action.equals("Delete")) {
            msgBar.setText("'" + name + "'" + " deleted from " + category + " List");
        }
        else if (action.equals("Number Error")) {
            msgBar.setText("Please check entered amount.");
        }
        else if (action.equals("Error")) {
            msgBar.setTextFill(Color.RED);
            msgBar.setText("Not enough units of item to remove.");
        }
        else if (action.equals("DNE")) {
            msgBar.setTextFill(Color.RED);
            msgBar.setText("Item does not exist.");
        }
        else if (action.equals("NoAction")) {
            msgBar.setTextFill(Color.RED);
            msgBar.setText("Please pick an action.");
        }
        else {
            msgBar.setTextFill(Color.RED);
            msgBar.setText("An error occurred.");
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
                msgBar.setText("");
                msgBar.setTextFill(Color.GREEN);
            }
        });
        new Thread(sleeper).start();
    }

    // MODIFIES: this
    // EFFECTS: saves inventory_model and then closes program window
    private void saveOperation(HashMap<String, ListOfItems> catalogue) {
        try {
            LoadSave.save(catalogue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: returns catalogue of lists of items
    public HashMap<String, ListOfItems> getCatalogue() {
        return catalogue;
    }

    // MODIFIES: this
    // EFFECTS: changes text in nameBar
    public void setNameBar(String s) {
        nameBar.setText(s);
    }

    // MODIFIES: this
    // EFFECTS: changes text in amountBar
    public void setAmountBar(String amount) {
        amountBar.setText(amount);
    }

    // MODIFIES: this
    // EFFECTS: changes selected text of categoryCombo
    public void setCategoryCombo(String category) {
        categoryCombo.setValue(category);
    }

}
