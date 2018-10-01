package product_categories;

import model.Item;

import static java.lang.Integer.parseInt;

public class Turf implements Item {
    public String name;
    private int amount;

    // EFFECTS: constructs an Aggregate with no name and amount 0
    public Turf() {
        name = "";
        amount = 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name + " " + amount;
    }

    @Override
    public void performAdd(String amount) {
        int localAmount = parseInt(amount);
        this.amount += localAmount;
        System.out.println(localAmount + " units added to " + this.name);
    }

    @Override
    public void performRemoval(String amount) {
        int localAmount = parseInt(amount);
        this.amount -= localAmount;
        System.out.println(localAmount + " units removed from " + this.name);
    }
}
