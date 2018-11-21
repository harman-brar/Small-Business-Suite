package ui;

import implementatons.InventoryCatalogue;
import javafx.application.Application;
import payment.Account;
import payment.Invoice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class Inventory {

    public static void main(String args[]) throws IOException {

        Account account = new Account("Echessbee", null);
        Date date = new Date();
        Invoice invoice = new Invoice(date, account, null, 1);
        account.addInvoice(invoice);
        invoice.payInvoice();

        Application.launch(InventoryCatalogue.class, args);
    }

}
