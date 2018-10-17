package model;

import exceptions.CapacityReachedException;
import exceptions.InvalidQuantityException;
import exceptions.NegativeNumberException;

import static java.lang.Integer.parseInt;

public class Item {
    private String name;
    private int amount;
    private String category;

    // EFFECTS: constructs an Aggregate with no name and amount 0
    public Item() {
        name = "";
        amount = 0;
        category= "";
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

    // EFFECTS: returns category of item
    public String getCategory() {
        return this.category;
    }

    // MODIFIES: this
    // EFFECTS: sets item category to category
    public void setCategory(String category) {
        this.category = category;
    }

    // EFFECTS: specifies the order in which to return name and current amount of item as a string
    public String toString() {
        return name + " " + amount;
    }

    // MODIFIES: this
    // EFFECTS: adds amount to amount of this
    public void performAdd(String amount) throws NegativeNumberException, CapacityReachedException {
        int localAmount = parseInt(amount);
        if (localAmount < 0) {
            throw new NegativeNumberException();
        }
        if (localAmount > 100) {
            throw new CapacityReachedException();
        }
        this.amount += localAmount;
        System.out.println(localAmount + " units added to " + this.name);
    }


    // REQUIRES: amount > 0, amount <= existing amount of item units
    // MODIFIES: this
    // EFFECTS: removes amount from amount of this
    public void performRemoval(String amount) throws NegativeNumberException {
        int localAmount = parseInt(amount);
        if (localAmount < 0) {
            throw new NegativeNumberException();
        }
        this.amount -= localAmount;
        System.out.println(localAmount + " units removed from " + this.name);
    }

}
