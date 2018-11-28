package implementatons.inventory_model;

import implementatons.tab_model.TabController;
import implementatons.tab_model.aggregateTabController;
import implementatons.tab_model.paverTabController;
import implementatons.tab_model.turfTabController;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import model.ListOfItems;
import exceptions.NegativeNumberException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Item;
import ui.DisplayInventory;

import java.io.*;
import java.util.*;

import static javafx.scene.paint.Color.rgb;

public class InventoryCatalogue extends Application {
    private ListOfItems aggregatesList, turfList, paversList;
    private ArrayList<ListOfItems> itemsList;
    public static Stage window;
    public static Scene scene1;
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
    private TabPane tabPane;
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
        setTabContent(aggregatesTab, new aggregateTabController(this));
        setTabContent(turfTab, new turfTabController(this));
        setTabContent(paversTab, new paverTabController(this));
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

    // EFFECTS: constructs inventory_model catalogue with various loaded item lists
    public InventoryCatalogue() {
        /*aggregatesList = new ListOfItems();
        turfList = new ListOfItems();
        paversList = new ListOfItems();

        itemsList = new ArrayList<ListOfItems>();
        itemsList.add(aggregatesList);
        itemsList.add(turfList);
        itemsList.add(paversList);*/

        try {
            catalogue = LoadSave.load();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception  {
        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("../../ui/resources/inventoryTracker.fxml"));
        primaryStage.setTitle("Inventory Tracker");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    @FXML
    private void goButtonHit() {

        String action = actionCombo.getValue();
        String category = categoryCombo.getValue();
        System.out.println(action);

        if (action.equals("Add")) {
            addOperation(nameBar.getText(), category, amountBar.getText());
        }
        else if (action.equals("Remove")) {
            removeOperation(nameBar.getText(), category, amountBar.getText());
        }
        else if (action.equals("Delete")) {
            deleteOperation(nameBar.getText(), category);

        /*} else if (action.equals("Search")) {
            try {
                i = findItem(name.getText(), catalogue);
                DisplaySearchItem.display("Search Results", i.toString());
            } catch(NullPointerException n) {
                showActionPopup("DNE", popup, null, null);
            }*/
        }
        else {
            System.out.println("Action not recognized.");
        }

        setTabControllers();
        System.out.println("Test passed");
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
            System.out.println("Item DNE");
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
        } catch (NegativeNumberException ie) {
            System.out.println("Please enter a valid amount");
        } catch (NumberFormatException nfe) {
            System.out.println("Please enter a number");
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
        else if (action.equals("Error")) {
            msgBar.setTextFill(Color.RED);
            msgBar.setText("Not enough units of item to remove.");
        }
        else if (action.equals("DNE")) {
            msgBar.setTextFill(Color.RED);
            msgBar.setText("Item does not exist.");
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
    // EFFECTS: saves inventory_model and then closes program window
    private void saveOperation(HashMap<String, ListOfItems> catalogue) {
        try {
            LoadSave.save(catalogue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, ListOfItems> getCatalogue() {
        return catalogue;
    }

    public void setNameBar(String s) {
        nameBar.setText(s);
    }

    public void setAmountBar(String amount) {
        amountBar.setText(amount);
    }

    public void setCategoryCombo(String category) {
        categoryCombo.setValue(category);
    }

}
