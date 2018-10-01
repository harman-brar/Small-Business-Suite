package model;

public interface Item {

    // EFFECTS: returns name and current amount of item as a string
    String getName();

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
