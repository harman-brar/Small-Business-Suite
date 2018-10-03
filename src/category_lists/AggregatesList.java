package category_lists;

import model.Item;
import model.ListOfItems;
import product_categories.Aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AggregatesList implements ListOfItems {
    private List<Item> aggregateList;
    private int index;

    public AggregatesList() {
        aggregateList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds Aggregate a to aggregateList then prints that a is added
    @Override
    public void insertItem(Item i) {
        aggregateList.add(i);
        System.out.println("Added item " + i.getName() + " to Aggregates list");
    }


    // EFFECTS: returns string statement of aggregateList
    @Override
    public String toString() {
        String listString = aggregateList.stream().map(Object::toString)
                .collect(Collectors.joining("\n"));
        return listString;
    }

    // MODIFIES: this
    // EFFECTS: if found, removes item with given name from list
    @Override
    public void deleteItem(String nameOfItem) {
        if (contains(nameOfItem)) {
            aggregateList.remove(aggregateList.get(index));
            System.out.println(nameOfItem + " removed from Aggregates List");
        }
        else {
            System.out.println("The item you are trying to delete does not exist.");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates new aggregate and adds it to list if it is not already there, otherwise returns index
    //          of the existing aggregate
    @Override
    public Item createItem(String nameOfItem) {
        if (!contains(nameOfItem)) {
            Item itemToAdd = new Aggregate();
            itemToAdd.setName(nameOfItem);
            insertItem(itemToAdd);

            return itemToAdd;
        } else {
            return aggregateList.get(index);
        }
    }

    // EFFECTS: returns true if item with given name is already in the list
    public boolean contains(String nameOfItem) {
        boolean isContains = false;
        index = 0;
        for (Item a: aggregateList) {
            if (a.getName().equals(nameOfItem)) {
                isContains = true;
                index = aggregateList.indexOf(a);
                break;
            } else {
                isContains = false;
            }
        }
        return isContains;
    }
}
