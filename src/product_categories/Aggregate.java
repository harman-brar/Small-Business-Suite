package product_categories;

import model.Item;

import java.io.Serializable;

import static java.lang.Integer.parseInt;

public class Aggregate implements Item {
    public String name;
    private int amount;

    // EFFECTS: constructs an Aggregate with no name and amount 0
    public Aggregate() {
        name = "";
        amount = 0;
    }

    // EFFECTS: returns name of this
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getAmount() {
        return this.amount;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: converts amount to int and sets it as the item's amount
    @Override
    public void setAmount(String amount) {
        this.amount = Integer.parseInt(amount);
    }

    // EFFECTS: returns name and amount of this
    @Override
    public String toString() {
        return name + " " + amount;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: adds amount to amount of this
    @Override
    public void performAdd(String amount) {
        int localAmount = parseInt(amount);
        this.amount += localAmount;
        System.out.println(localAmount + " units added to " + this.name);
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: removes amount from amount of this
    @Override
    public void performRemoval(String amount) {
        int localAmount = parseInt(amount);
        this.amount -= localAmount;
        System.out.println(localAmount + " units removed from " + this.name);
    }
}
