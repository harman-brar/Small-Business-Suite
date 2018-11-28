package implementatons.tab_model;

import implementatons.inventory_model.InventoryCatalogue;
import implementatons.inventory_model.LoadSave;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Item;
import model.ListOfItems;

import java.util.List;

public class toolTabController implements TabController {
    private ListOfItems l;
    private InventoryCatalogue inventoryCatalogue;

    @FXML
    private Label titleLabel;
    @FXML
    private Label itemsLabel;
    @FXML
    private VBox vbox;
    @FXML
    private ScrollPane scrolly;
    @FXML
    private AnchorPane anchor;

    @FXML
    private void initialize() {
        assert titleLabel != null : "fx:id=\"titleLabel\" was not injected: check your FXML file 'tabContent.fxml'.";
        assert itemsLabel != null : "fx:id=\"itemsLabel\" was not injected: check your FXML file 'tabContent.fxml'.";
        assert vbox != null : "fx:id=\"vboxLabel\" was not injected: check your FXML file 'tabContent.fxml'.";
        assert scrolly != null : "fx:id=\"scrolly\" was not injected: check your FXML file 'tabContent.fxml'.";
        assert anchor != null : "fx:id=\"anchor\" was not injected: check your FXML file 'tabContent.fxml'.";


        titleLabel.setText("Tools");

        for(int i=0; i<=l.getSize()-1; i++) {
            Item item = l.get(i);
            Button btn = new Button(item.toString());
            btn.setPrefSize(200, 35);
            scrolly.setFitToHeight(true);
            vbox.setPrefHeight(anchor.getPrefHeight());
            vbox.getChildren().add(btn);
            btn.setOnAction(event -> {
                List<String> partsOfLine = LoadSave.splitOnPipe(btn.getText());
                System.out.println(partsOfLine);
                inventoryCatalogue.setNameBar(partsOfLine.get(0));
                inventoryCatalogue.setAmountBar(partsOfLine.get(1));
                inventoryCatalogue.setCategoryCombo("Tools");
            });
        }

    }

    public toolTabController(InventoryCatalogue inventoryCatalogue) {
        this.inventoryCatalogue = inventoryCatalogue;
        l = inventoryCatalogue.getCatalogue().get("Tools");
    }
}
