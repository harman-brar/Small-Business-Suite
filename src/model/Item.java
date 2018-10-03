package model;

public interface Item {

    // EFFECTS: returns name of item
    String getName();

    // EFFECTS: returns amount of item
    int getAmount();

    // EFFECTS: sets item name as given name
    void setName(String name);

    void setAmount(String amount);

    // EFFECTS: specifies the order in which to return name and current amount of item as a string
    String toString();

    // MODIFIES: this
    // EFFECTS: adds amount to internal amount of item
    void performAdd(String amount);

    // REQUIRES: amount <= amount of units of item
    // MODIFIES: this
    // EFFECTS: removes amount from internal amount of item
    void performRemoval(String amount);

}
