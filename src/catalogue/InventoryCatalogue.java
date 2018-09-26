package catalogue;

import java.util.ArrayList;
import java.util.Scanner;

// Represents a catalogue of items in inventory, maintained by the user
public class InventoryCatalogue {

    private Scanner scanner = new Scanner(System.in);
    private ArrayList<Item> itemsList = new ArrayList<>();
    private String name;

    // EFFECTS: constructs Inventory Catalogue and establishes an interaction loop that allows user to add
    //          remove and delete items, as well as print out the catalogue of items with 'done'
    public InventoryCatalogue() {
        label:
        while (true) {

            System.out.println("Please select action (add, remove, delete, done).");
            String action = scanner.nextLine();
            System.out.println("You entered action: " + action);

            switch (action) {
                case "add":
                    addOperation();
                    break;

                case "remove":
                    removeOperation();
                    break;

                case "delete":
                    deleteOperation();
                    break;

                case "done":
                    break label;
            }
        }
        System.out.println("Inventory: " + itemsList.toString());
    }


    // MODIFIES: this
    // EFFECTS: Handles user input if action is 'delete', finds item with given name, and deletes
    // the item from catalogue
    private void deleteOperation() {
        System.out.println("Enter the name of the item that you would like to apply this action to.");
        name = scanner.nextLine();
        for (Item item : itemsList) {
            if (item.name.equals(name)) {
                itemsList.remove(item);
                System.out.println("Item: " + name + " deleted from catalogue");
                break;
            } else {
                System.out.println("This item cannot be deleted as it does not exist.");
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Handles user input if action is 'remove', finds item with given name, and removes entered amount
    // from item quantity
    private void removeOperation() {
        System.out.println("Enter the name of the item that you would like to apply this action to.");
        name = scanner.nextLine();
        for (Item item : itemsList) {
            if (item.name.equals(name)) {
                String stringAmount = getStringAmount();
                performRemoval(stringAmount, item);
                break;
            } else {
                System.out.println("There is currently no stock of this item.");
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Handles user input if action is 'add', finds item with given name, and adds entered amount
    // to item quantity
    private void addOperation() {
        System.out.println("Enter the name of the item that you would like to apply this action to.");
        name = scanner.nextLine();
        Item i;
        if (itemsList.size() == 0) {
            i = createItem(name);
            String stringAmount = getStringAmount();
            performAdd(stringAmount, i);
        } else {
            for (Item item : itemsList) {
                if (item.name.equals(name)) {
                    String stringAmount = getStringAmount();
                    performAdd(stringAmount, item);
                    break;
                } else {
                    i = createItem(name);
                    String stringAmount = getStringAmount();
                    performAdd(stringAmount, i);
                    break;
                }
            }
        }
    }

    // EFFECTS: handles user value input for amount
    private String getStringAmount() {
        System.out.println("How many units would you like to add?");
        return scanner.nextLine();
    }

    // REQUIRES: Item exists, stringAmount >= 0
    // MODIFIES: this
    // EFFECTS: removes quantity units from existing amount of item
    public void performRemoval(String stringAmount, Item item) {
        int localAmount = Integer.parseInt(stringAmount);
        item.amount -= localAmount;
        System.out.println(localAmount + " units removed from stock of " + item.name);
    }

    // REQUIRES: Item exists, stringAmount >= 0
    // MODIFIES: this
    // EFFECTS: adds quantity units to existing amount of item
    public void performAdd(String stringAmount, Item item) {
        int localAmount = Integer.parseInt(stringAmount);
        item.amount += localAmount;
        System.out.println(localAmount + " units added to stock of " + item.name);
    }

    // MODIFIES: this
    // EFFECTS: creates an item with the given name and adds it to item catalogue
    public Item createItem(String nameOfItem) {
        Item itemToAdd = new Item();
        itemToAdd.name = nameOfItem;
        insertItem(itemToAdd);

        return itemToAdd;
    }

    // REQUIRES: Item must not be null
    // MODIFIES: this
    // EFFECTS: Inserts item into inventory catalogue
    public void insertItem(Item itemToAdd) {
        itemsList.add(itemToAdd);
        System.out.println("Item: " + itemToAdd.name + " created in inventory");
    }

}
