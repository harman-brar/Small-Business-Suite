package implementatons;

import category_lists.AggregatesList;
import category_lists.TurfList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import product_categories.Aggregate;
import product_categories.Turf;
import ui.DisplayInventory;

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


    public InventoryCatalogue() {
        aggregatesList = new AggregatesList();
        turfList = new TurfList();
    }

    @Override
    public void start(Stage primaryStage) {
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
                loadAggregates();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                loadTurf();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Options for action dropdown
        ObservableList<String> operations =
                FXCollections.observableArrayList(
                        "Add",
                        "Remove",
                        "Delete"
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
                    a.performAdd(amount.getText());
                } else if (category.getValue().equals("Turf")) {
                    Item t = turfList.createItem(name.getText());
                    t.performAdd(amount.getText());
                }
            }
            else if (action.getValue().equals("Remove")) {
                if (category.getValue().equals("Aggregates")) {
                    Item a = aggregatesList.createItem(name.getText());
                    a.performRemoval(amount.getText());
                } else if (category.getValue().equals("Turf")) {
                    Item t = turfList.createItem(name.getText());
                    t.performRemoval(amount.getText());
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
            }
            else {
                System.out.println("Action not recognized.");
            }

        });

        // See Inventory Button
        Button doneButton = new Button("See Inventory");
        doneButton.setOnAction((ActionEvent event) -> {
            DisplayInventory.display("Inventory", "[ Aggregates ]: " + aggregatesList.toString(), "[ Turf ]: " + turfList.toString());
        });

        //Layout 1 - children laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, loadInventory, action, name, category, amount, button1, doneButton);
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1, 350, 400);


        // --------------------------- SCENE 2 -------------------------------- \\

        // Label & Button 2
        Label label2 = new Label("This is scene 2");
        Button button2 = new Button("Back to scene 1");
        button2.setOnAction(e -> window.setScene(scene1));

        //Layout 2
        VBox layout2 = new VBox(20);
        layout2.getChildren().addAll(label2, button2);
        layout2.setAlignment(Pos.CENTER);
        scene2 = new Scene(layout2, 350, 400);

        // --------------------------- WINDOW CONFIGURATION -------------------------------- \\

        window.setOnCloseRequest(e -> closeOperation());

        //Display scene 1 at first
        window.setScene(scene1);
        window.setTitle("Edit Inventory");
        window.show();

    }

    private void closeOperation() {
        try {
            saveAggregates();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            saveTurf();
        } catch (IOException e) {
            e.printStackTrace();
        }
        window.close();
    }

    private void saveAggregates() throws IOException {
        List<String> lines = new ArrayList<>();
        //PrintWriter writer = new PrintWriter("aggregateOutput.txt","UTF-8");
        PrintWriter writer = new PrintWriter(new FileOutputStream("aggregateOutput.txt",false));
        lines.add(aggregatesList.toString());
        for (String line : lines){
            writer.println(line);
        }
        writer.close();
        System.out.println("Aggregate entries saved.");


    }

    private void saveTurf() throws IOException {
        List<String> lines = new ArrayList<>();
        //PrintWriter writer = new PrintWriter("turfOutput.txt","UTF-8");
        PrintWriter writer = new PrintWriter(new FileWriter("turfOutput.txt",false));
        lines.add(turfList.toString());
        for (String line : lines){
            writer.println(line);
        }
        writer.close();
        System.out.println("Turf entries saved.");
    }

    private void loadAggregates() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("aggregateOutput.txt"));
        for (String line : lines){
            if (!line.isEmpty()) {
                ArrayList<String> partsOfLine = splitOnSpace(line);
                Item l = new Aggregate();
                l.setName(partsOfLine.get(0));
                l.setAmount(partsOfLine.get(1));
                aggregatesList.insertItem(l);
                System.out.print("Name: " + partsOfLine.get(0) + " ");
                System.out.println("Amount: " + partsOfLine.get(1));
            }
        }
    }

    private void loadTurf() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("turfOutput.txt"));
        for (String line : lines){
            if (!line.isEmpty()) {
                ArrayList<String> partsOfLine = splitOnSpace(line);
                Item l = new Turf();
                l.setName(partsOfLine.get(0));
                l.setAmount(partsOfLine.get(1));
                turfList.insertItem(l);
                System.out.print("Name: " + partsOfLine.get(0) + " ");
                System.out.println("Amount: " + partsOfLine.get(1));
            }
        }
    }

    public static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

}
