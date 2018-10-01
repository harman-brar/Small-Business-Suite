package model;


public interface ListOfItems {

    // EFFECTS: returns size of list
    int size();

    // EFFECTS: returns string statement of list
    String show();

    void deleteItem(String nameOfItem);

}
