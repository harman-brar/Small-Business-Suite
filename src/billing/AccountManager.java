package billing;

import implementatons.inventory_model.LoadSave;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Item;

import java.io.EOFException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccountManager implements Serializable {
    private List<Account> accounts;
    boolean saved = false;

    @FXML
    private TextField compName;
    @FXML
    private TextField compAdd;
    @FXML
    private TextField compPhone;
    @FXML
    private TextField contName;
    @FXML
    private TextField contPhone;
    @FXML
    private Button addAccBtn;
    @FXML
    private AnchorPane accountsPane;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox vBox;
    @FXML
    private Label compNameLabel;
    @FXML
    private Label compAddLabel;
    @FXML
    private Label compPhoneLabel;
    @FXML
    private Label contNameLabel;
    @FXML
    private Label contPhoneLabel;
    @FXML
    private Label amountDueLabel;
    @FXML
    private VBox invBox;

    @FXML
    private void initialize() {
        assert compName != null : "fx:id=\"compName\" was not injected: check your FXML file 'accountManager.fxml'.";
        assert compAdd != null : "fx:id=\"compAdd\" was not injected: check your FXML file 'accountManager.fxml'.";
        assert compPhone != null : "fx:id=\"compPhone\" was not injected: check your FXML file 'accountManager.fxml'.";
        assert contName != null : "fx:id=\"contName\" was not injected: check your FXML file 'accountManager.fxml'.";
        assert contPhone != null : "fx:id=\"contPhone\" was not injected: check your FXML file 'accountManager.fxml'.";
        assert addAccBtn != null : "fx:id=\"addAccBtn\" was not injected: check your FXML file 'accountManager.fxml'.";
        assert accountsPane != null : "fx:id=\"accountsPane\" was not injected: check your FXML file 'accountManager.fxml'.";
        assert scrollPane != null : "fx:id=\"scrollPane\" was not injected: check your FXML file 'accountManager.fxml'.";
        assert compNameLabel != null : "fx:id=\"compNameLabel\" was not injected: check your FXML file 'accountManager.fxml'.";
        assert compAddLabel != null : "fx:id=\"compAddLabel\" was not injected: check your FXML file 'accountManager.fxml'.";
        assert compPhoneLabel != null : "fx:id=\"compPhoneLabel\" was not injected: check your FXML file 'accountManager.fxml'.";
        assert contNameLabel != null : "fx:id=\"contNameLabel\" was not injected: check your FXML file 'accountManager.fxml'.";
        assert contPhoneLabel != null : "fx:id=\"contPhoneLabel\" was not injected: check your FXML file 'accountManager.fxml'.";
        assert amountDueLabel != null : "fx:id=\"amountDueLabel\" was not injected: check your FXML file 'accountManager.fxml'.";
        assert invBox != null : "fx:id=\"invBox\" was not injected: check your FXML file 'accountManager.fxml'.";

        refreshAccounts();
    }

    public AccountManager() {
        try {
            accounts = LoadSave.loadAccounts();
        } catch (EOFException e) {
            accounts = new ArrayList<Account>();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @FXML
    private void addAccount() {
        CompanyContact contact = new CompanyContact(contName.getText(), contPhone.getText());
        Account account = new Account(compName.getText(), compPhone.getText(), compAdd.getText(), contact);
        if (!accounts.contains(account)) {
            accounts.add(account);
            addBtnForAcc(account);
            try {
                LoadSave.saveAccounts(accounts);
                this.saved = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Account Already Exists");
        }

    }

    private void removeAccount(Account acc) {
        if (accounts.contains(acc)) {
            accounts.remove(acc);
        }
    }

    private void addBtnForAcc(Account account) {
        Button btn = new Button(account.getCompanyName());
        btn.setPrefSize(200, 35);
        scrollPane.setFitToHeight(true);
        vBox.setPrefHeight(accountsPane.getPrefHeight());
        vBox.setSpacing(10.0);
        vBox.getChildren().add(btn);
        btn.setOnAction(event -> {
                compNameLabel.setText("Name: " + account.getCompanyName());
                compAddLabel.setText("Address: " + account.getAddress());
                compPhoneLabel.setText("Phone: " + account.getPhone());
                contNameLabel.setText("Contact Name: " + account.getContact().getName());
                contPhoneLabel.setText("Contact Phone: " + account.getContact().getPhone());
                for (Invoice i : account.getInvoices()) {
                    setInvoiceBtnAction(i);
                }
        });
    }

    private void setInvoiceBtnAction(Invoice invoice) {
        Button btn = new Button("Invoice: " + String.valueOf(invoice.getInvNumber()));
        btn.setPrefSize(200, 35);
        invBox.setPrefHeight(accountsPane.getPrefHeight());
        invBox.setSpacing(10.0);
        invBox.getChildren().add(btn);
        btn.setOnAction(event -> {
            System.out.println("Invoice tapped.");
        });
    }

    private void refreshAccounts() {
        for(Account account : accounts) {
            /*Button btn = new Button(account.getCompanyName());
            btn.setPrefSize(200, 35);
            scrollPane.setFitToHeight(true);
            vBox.setPrefHeight(accountsPane.getPrefHeight());
            vBox.setSpacing(10.0);
            vBox.getChildren().add(btn);
            btn.setOnAction(event -> {
                /*List<String> partsOfLine = LoadSave.splitOnPipe(btn.getText());
                System.out.println(partsOfLine);
                inventoryCatalogue.setNameBar(partsOfLine.get(0));
                inventoryCatalogue.setAmountBar(partsOfLine.get(1));
                inventoryCatalogue.setCategoryCombo("Aggregates");
            });*/
        addBtnForAcc(account);
        }
    }

    public List<Account> getAccounts() {
        return accounts;
    }


}
