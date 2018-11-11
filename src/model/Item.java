package model;

import exceptions.NegativeNumberException;

import java.util.Collection;
import java.util.HashSet;

import static java.lang.Integer.parseInt;

public class Item {
    private String name;
    private int amount;
    private String category;
    private Collection<Manufacturer> producers;

    // EFFECTS: constructs an item
    public Item() {
        name = "";
        amount = 0;
        category= "";
        producers = new HashSet<Manufacturer>();
    }

    // MODIFIES: this, m
    // EFFECTS: adds m to list of manufacturers that produce this
    public void addManufacturer(Manufacturer m) {
        if (!producers.contains(m)) {
            producers.add(m);
            m.addProduct(this);
        }
    }

    // MODIFIES: this, m
    // EFFECTS: removes m from list of manufacturers that produce this
    public void removeManufacturer(Manufacturer m) {
        if (producers.contains(m)) {
            producers.remove(m);
            m.removeProduct(this);
        }
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
        return name + " | " + amount;
    }

    // MODIFIES: this
    // EFFECTS: adds amount to amount of this
    public void performAdd(String amount) throws NegativeNumberException{
        int localAmount = parseInt(amount);
        if (localAmount < 0) {
            throw new NegativeNumberException();
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
