package model;


public interface ListOfItems {

    // EFFECTS: returns size of list
    int getSize();

    // EFFECTS: returns string statement of list
    String toString();

    // MODIFIES: THIS
    // EFFECTS: remove item with given name from list
    void deleteItem(String nameOfItem);

    // MODIFIES: THIS
    // EFFECTS: creates item and inserts into list if it does not already exist in list, returns item
    Item createItem(String nameOfItem);

    // MODIFIES: THIS
    // EFFECTS:
    void insertItem(Item i);

    // EFFECTS: returns item with name that matches nameOfItem
    Item getItem(String nameOfItem);


}
