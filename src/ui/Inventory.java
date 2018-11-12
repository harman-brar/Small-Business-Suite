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
        BufferedReader br = null;

        try {
            String theURL = "https://www.ugrad.cs.ubc.ca/~cs210/2018w1/welcomemsg.html"; //this can point to any URL
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine()) != null) {

                sb.append(line);
                sb.append(System.lineSeparator());
            }

            System.out.println(sb);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (br != null) {
                br.close();
            }
        }
        Account account = new Account("Echessbee", null);
        Date date = new Date();
        Invoice invoice = new Invoice(date, account, null, 1);
        account.addInvoice(invoice);
        invoice.addObserver(account);
        invoice.payInvoice();

        Application.launch(InventoryCatalogue.class, args);
    }

}
