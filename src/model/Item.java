package model;

import static java.lang.Integer.parseInt;

public abstract class Item {
    private String name;
    private int amount;

    // EFFECTS: constructs an Aggregate with no name and amount 0
    public Item() {
        name = "";
        amount = 0;
    }

    // EFFECTS: returns name of item
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns amount of item
    public int getAmount() {
        return this.amount;
    }

    // MODIFIES: this
    // EFFECTS: sets item name as given name
    public void setName(String name) {
        this.name = name;
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: sets amount as given amount
    public void setAmount(String amount) {
        this.amount = Integer.parseInt(amount);
    }

    // EFFECTS: specifies the order in which to return name and current amount of item as a string
    public String toString() {
        return name + " " + amount;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: adds amount to amount of this
    public void performAdd(String amount) {
        int localAmount = parseInt(amount);
        this.amount += localAmount;
        System.out.println(localAmount + " units added to " + this.name);
    }


    // REQUIRES: amount > 0, amount <= existing amount of item units
    // MODIFIES: this
    // EFFECTS: removes amount from amount of this
    public void performRemoval(String amount) {
        int localAmount = parseInt(amount);
        this.amount -= localAmount;
        System.out.println(localAmount + " units removed from " + this.name);
    }

}
