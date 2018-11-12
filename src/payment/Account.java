package payment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Account implements Observer {
    private Set<Invoice> activeInvoices;
    private String companyName;
    private List<String> phoneNumbers;

    // EFFECTS: constructs an account with invoices, a name, and phone number(s)
    public Account(String companyName, ArrayList<String> phoneNumbers) {
        activeInvoices = new HashSet<>();
        this.companyName = companyName;
        this.phoneNumbers = phoneNumbers;
    }

    // MODIFIES: this
    // EFFECTS: associates an invoice with this
    public void addInvoice(Invoice inv) {
        activeInvoices.add(inv);
    }

    // MODIFIES: this
    // EFFECTS: removes an invoice from this
    public void removeInvoice(int invNumber) {
        for (Invoice i : activeInvoices) {
            if (i.getInvNumber() == invNumber) {
                activeInvoices.remove(i);
                break;
            }
        }
    }

    // EFFECTS: returns name of company
    public String getCompanyName() {
        return companyName;
    }

    // EFFECTS: returns company phone number
    public List<String> getPhone() {
        return phoneNumbers;
    }

    @Override
    public void update(int invoiceNo) {
        removeInvoice(invoiceNo);
        System.out.println("Invoice " + invoiceNo + " paid.");
    }
}
