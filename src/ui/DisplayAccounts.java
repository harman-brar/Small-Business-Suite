package ui;

import implementatons.inventory_model.InventoryCatalogue;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;


public class DisplayAccounts {

    public static Scene display() {
        VBox vbox = new VBox(10);
        Button button = new Button("Go back");
        button.setOnAction((ActionEvent event) -> {
            InventoryCatalogue.window.setScene(InventoryCatalogue.scene1);
        });
        vbox.getChildren().addAll(button);
        Scene scene = new Scene(vbox);

        return scene;
    }

}
