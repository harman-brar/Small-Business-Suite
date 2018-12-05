package billing;

import model.Item;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

public class Invoice extends Observable implements Serializable {
    private Date date;
    private Account acc;
    private String address;
    private int invNumber;
    private boolean paid;
    private Set<Item> items;

    // EFFECTS: constructs an invoice with a date, account, site address, and invoice number
    public Invoice(Date date, Account acc, String address, int invNumber) {
        this.date = date;
        this.acc = acc;
        this.address = address;
        this.invNumber = invNumber;
        paid = false;
        this.items = new HashSet<Item>();
        addObserver(acc);
    }

    // MODIFIES: this
    // EFFECTS: adds an item to invoice
    private void addItem(Item item) {
        items.add(item);
    }

    // MODIFIES: this
    // EFFECTS: adds an item to invoice
    private void removeItem(Item item) {
        if (items.contains(item)) {
            items.remove(item);
        }
    }

    // MODIFIES: this
    // EFFECTS: changes invoice status to paid
    public void payInvoice() {
        paid = true;
        setChanged();
        notifyObservers(invNumber);

    }

    // EFFECTS: returns invoice date
    public Date getDate() {
        return date;
    }

    // EFFECTS: returns account on invoice
    public Account getAccount() {
        return acc;
    }

    // EFFECTS: returns address on invoice
    public String getAddress() {
        return address;
    }

    // EFFECTS: returns invoice number
    public int getInvNumber() {
        return invNumber;
    }

    // EFFECTS: returns items on invoice
    public Set<Item> getItems() {
        return items;
    }
}
