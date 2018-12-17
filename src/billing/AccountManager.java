package billing;

import implementatons.inventory_model.LoadSave;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
    private Label amountDueLabel;
    @FXML
    private VBox vBox;
    @FXML
    private VBox invBox;

    // EFFECTS: inits fxml components
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
        assert amountDueLabel != null : "fx:id=\"amountDueLabel\" was not injected: check your FXML file 'accountManager.fxml'.";
        assert invBox != null : "fx:id=\"invBox\" was not injected: check your FXML file 'accountManager.fxml'.";

        refreshAccounts();
    }

    // EFFECTS: constructs account manager
    public AccountManager() {
        try {
            accounts = LoadSave.loadAccounts();
        } catch (EOFException e) {
            accounts = new ArrayList<Account>();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates and adds account to list
    @FXML
    private void addAccount() {
        CompanyContact contact = new CompanyContact(contName.getText(), contPhone.getText());
        Account account = new Account(compName.getText(), compPhone.getText(), compAdd.getText(), contact);
        if (!accounts.contains(account)) {
            accounts.add(account);
            addBtnForAcc(account);
        } else {
            removeAccount(account);
            editAccount(account);
            accounts.add(account);
            System.out.println("Changes will take place on window close.");
        }
        try {
            LoadSave.saveAccounts(accounts);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // MODIFIES: this
    // EFFECTS: removes account from list
    private void removeAccount(Account account) {
        accounts.remove(account);
    }

    // MODIFIES: this
    // EFFECTS: updates account information
    private void editAccount(Account account) {
        account.setPhoneNumber(compPhone.getText());
        account.setAddress(compAdd.getText());
        account.setContact(contName.getText(), contPhone.getText());
    }

    // MODIFIES: this
    // EFFECTS: creates button for given account and gives it an action
    private void addBtnForAcc(Account account) {
        Button btn = new Button(account.getCompanyName());
        btn.setPrefSize(200, 35);
        scrollPane.setFitToHeight(true);
        vBox.setPrefHeight(accountsPane.getPrefHeight());
        vBox.setSpacing(10.0);
        vBox.getChildren().add(btn);
        btn.setOnAction(event -> {
                amountDueLabel.setText("Amount due: " + account.getAmountDue().toString());
                compName.setText(account.getCompanyName());
                compAdd.setText(account.getAddress());
                compPhone.setText(account.getPhone());
                contName.setText(account.getContact().getName());
                contPhone.setText(account.getContact().getPhone());
                addAccBtn.setText("Edit");
                addAccBtn.setTextFill(Color.RED);
                addAccBtn.setOnAction(event1 -> {
                    addAccount();
                    addAccBtn.setText("Do It");
                    addAccBtn.setTextFill(Color.BLACK);
                });
                for (Invoice i : account.getInvoices()) {
                    setInvoiceBtnAction(i);
                }
        });
    }

    // MODIFIES: this
    // EFFECTS: creates button for each invoice and gives it an action
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

    // MODIFIES: this
    // EFFECTS: re-renders account buttons
    private void refreshAccounts() {
        for(Account account : accounts) {
            addBtnForAcc(account);
        }
    }

    // EFFECTS: returns accounts
    public List<Account> getAccounts() {
        return accounts;
    }


}
