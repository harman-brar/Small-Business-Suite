package implementatons;

import category_lists.AggregatesList;
import category_lists.TurfList;
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
import product_categories.Aggregate;
import product_categories.Turf;
import ui.DisplayInventory;

import static javafx.scene.paint.Color.rgb;

public class InventoryCatalogue extends Application {
    AggregatesList aggregatesList;
    TurfList turfList;
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
                    Aggregate a = aggregatesList.createAggregate(name.getText());
                    a.performAdd(amount.getText());
                } else if (category.getValue().equals("Turf")) {
                    Turf t = turfList.createTurf(name.getText());
                    t.performAdd(amount.getText());
                }
            }
            else if (action.getValue().equals("Remove")) {
                if (category.getValue().equals("Aggregates")) {
                    Aggregate a = aggregatesList.createAggregate(name.getText());
                    a.performRemoval(amount.getText());
                } else if (category.getValue().equals("Turf")) {
                    Turf t = turfList.createTurf(name.getText());
                    t.performRemoval(amount.getText());
                }
            }
            else if (action.getValue().equals("Delete")) {
                if (category.getValue().equals("Aggregates")) {
                    Aggregate a = aggregatesList.createAggregate(name.getText());
                    aggregatesList.deleteItem(name.getText());
                } else if (category.getValue().equals("Turf")) {
                    Turf t = turfList.createTurf(name.getText());
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
            DisplayInventory.display("Inventory", aggregatesList.show(), turfList.show());
        });

        //Layout 1 - children laid out in vertical column
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, action, name, category, amount, button1, doneButton);
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
        System.out.println("File Save Operation");
        window.close();
    }
}
