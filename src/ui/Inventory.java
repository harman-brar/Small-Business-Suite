package ui;

import implementatons.inventory_model.InventoryCatalogue;
import javafx.application.Application;
import billing.Account;
import billing.Invoice;

import java.io.IOException;
import java.util.Date;

public class Inventory {

    public static void main(String args[]) throws IOException {

        /*Account account = new Account("Echessbee", null);
        Date date = new Date();
        Invoice invoice = new Invoice(date, account, null, 1);
        account.addInvoice(invoice);
        invoice.payInvoice();*/

        Application.launch(InventoryCatalogue.class, args);
    }

}
