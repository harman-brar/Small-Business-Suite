package model;

import model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ListOfItems {
    private List<Item> thisList;
    private int index;
    private Item item;

    public ListOfItems() {
        thisList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds Aggregate a to aggregateList then prints that a is added
    public void insertItem(Item i) {
        thisList.add(i);
        System.out.println("Added item " + i.getName() + " to " + i.getCategory() + " list");
    }

    public Item getItem(String nameOfItem) {
        item = null;
        for (Item i: thisList) {
            if (i.getName().equals(nameOfItem)) {
                item = i;
                break;
            }
        }
        return item;
    }

    public int getSize() {
        return thisList.size();
    }

    // EFFECTS: returns string statement of aggregateList
    @Override
    public String toString() {
        String listString = thisList.stream().map(Object::toString)
                .collect(Collectors.joining("\n"));
        return listString;
    }

    // MODIFIES: this
    // EFFECTS: if found, removes item with given name from list
    public void deleteItem(String nameOfItem) {
        if (contains(nameOfItem)) {
            thisList.remove(thisList.get(index));
            System.out.println(nameOfItem + " removed from Aggregates List");
        }
        else {
            //System.out.println("The item you are trying to delete does not exist.");
            throw new NullPointerException();
        }
    }

    // MODIFIES: this
    // EFFECTS: creates new aggregate and adds it to list if it is not already there, otherwise returns index
    //          of the existing aggregate
    public Item createItem(String nameOfItem) {
        if (!contains(nameOfItem)) {
            Item itemToAdd = new Item();
            itemToAdd.setName(nameOfItem);
            insertItem(itemToAdd);

            return itemToAdd;
        } else {
            return thisList.get(index);
        }
    }

    // EFFECTS: returns true if item with given name is already in the list
    public boolean contains(String nameOfItem) {
        boolean isContains = false;
        index = 0;
        for (Item a: thisList) {
            if (a.getName().equals(nameOfItem)) {
                isContains = true;
                index = thisList.indexOf(a);
                break;
            } else {
                isContains = false;
            }
        }
        return isContains;
    }

}
