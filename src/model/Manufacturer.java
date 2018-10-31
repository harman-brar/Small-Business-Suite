package model;

import java.util.Collection;
import java.util.HashSet;

public class Manufacturer {
    private String name;
    private Collection<Item> products;

    // EFFECTS: constructs a manufacturer
    public Manufacturer(String name) {
        this.name = name;
        products = new HashSet<Item>();
    }

    // MODIFIES: this, i
    // EFFECTS: adds an item to the list of products sold by this
    public void addProduct(Item i) {
        if (!products.contains(i)) {
            products.add(i);
            i.addManufacturer(this);
        }

    }

    // MODIFIES: this, i
    // EFFECTS: removes an item from the list of products sold by this
    public void removeProduct(Item i) {
        if (products.contains(i)) {
            products.remove(i);
            i.removeManufacturer(this);
        }
    }
}
