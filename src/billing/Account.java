package billing;

import java.io.Serializable;
import java.util.*;

public class Account implements Observer, Serializable {
    private Set<Invoice> activeInvoices;
    private String companyName;
    private String phoneNumber;
    private String address;
    private CompanyContact contact;
    private Double amountDue;

    // EFFECTS: constructs an account with invoices, a name, and phone number(s)
    public Account(String companyName, String phoneNumber, String address, CompanyContact contact) {
        activeInvoices = new HashSet<>();
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.contact = contact;
        this.amountDue = 0.0;
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: updates accounts outstanding balance
    void updateAmountDue(Double amount) {
        amountDue += amount;
    };

    // MODIFIES: this
    // EFFECTS: associates an invoice with this
    void addInvoice(Invoice inv) {
        activeInvoices.add(inv);
    }

    // MODIFIES: this
    // EFFECTS: removes an invoice from this
    void removeInvoice(int invNumber) {
        for (Invoice i : activeInvoices) {
            if (i.getInvNumber() == invNumber) {
                activeInvoices.remove(i);
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: changes company contact person's information
    void replaceContact(CompanyContact contact) {
        this.contact = contact;
    }

    // EFFECTS: returns invoices on account
    public Set<Invoice> getInvoices() {
        return activeInvoices;
    }

    // EFFECTS: returns name of company
    public String getCompanyName() {
        return companyName;
    }

    // EFFECTS: returns company phone number
    public String getPhone() {
        return phoneNumber;
    }

    // EFFECTS: returns company address
    public String getAddress() {
        return address;
    }

    // EFFECTS: returns company contact
    public CompanyContact getContact() {
        return contact;
    }

    // EFFECTS: returns total amount due by company
    public Double getAmountDue() {
        return amountDue;
    }

    @Override
    public void update(Observable o, Object arg) {
        int invNumber = (int) arg;
        removeInvoice(invNumber);
        System.out.println("Invoice " + invNumber + " paid.");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(companyName, account.companyName) &&
                Objects.equals(phoneNumber, account.phoneNumber);
    }

    @Override
    public int hashCode() {

        return Objects.hash(companyName, phoneNumber);
    }
}
