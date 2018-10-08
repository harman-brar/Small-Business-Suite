package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DisplayInventory {

    public static void display(String title, String aggregates, String turf) {
        Stage window = new Stage();

        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label h1 = new Label();
        h1.setText("Aggregates");
        h1.setFont(Font.font("Verdana", FontWeight.findByName("bold"), 13));
        Label label = new Label();
        label.setText(aggregates);
        label.setFont(Font.font("Verdana", 13));

        Label h2 = new Label();
        h2.setText("Turf");
        h2.setFont(Font.font("Verdana", FontWeight.findByName("bold"), 13));
        Label label2 = new Label();
        label2.setFont(Font.font("Verdana", 13));
        label2.setText(turf);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(h1, label);

        VBox layout2 = new VBox(10);
        layout2.getChildren().addAll(h2, label2);

        HBox wrapper = new HBox(10);
        wrapper.getChildren().addAll(layout, layout2);

        VBox container = new VBox(10);
        container.getChildren().addAll(wrapper, closeButton);
        container.setAlignment(Pos.CENTER);

        //Display window and wait for it to be closed before returning
        Scene scene = new Scene(container);
        window.setScene(scene);
        window.showAndWait();
    }

}
