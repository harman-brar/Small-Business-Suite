package category_lists;

import model.Item;
import model.ListOfItems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TurfList implements ListOfItems {
    private List<Item> turfList;
    private int index;
    private Item item;

    // EFFECTS: constructs new turfList
    public TurfList() {
        turfList = new ArrayList<>();
    }


    @Override
    public int getSize() {
        return turfList.size();
    }

    // EFFECTS: returns string statement of turfList
    @Override
    public String toString() {
        String listString = turfList.stream().map(Object::toString)
                .collect(Collectors.joining("\n"));
        return listString;
    }

    // MODIFIES: this
    // EFFECTS: if found, removes item with given name from list
    @Override
    public void deleteItem(String nameOfItem) {
        if (contains(nameOfItem)) {
            turfList.remove(turfList.get(index));
            System.out.println(nameOfItem + " removed from Turf List");
        }
        else {
            System.out.println("The item you are trying to delete does not exist.");
        }
    }

    @Override
    public Item createItem(String nameOfItem) {
        if (!contains(nameOfItem)) {
            Item itemToAdd = new Item();
            itemToAdd.setName(nameOfItem);
            insertItem(itemToAdd);

            return itemToAdd;
        } else {
            return turfList.get(index);
        }
    }

    @Override
    public void insertItem(Item i) {
        turfList.add(i);
        System.out.println("Added item " + i.getName() + " to Turf list");
    }

    @Override
    public Item getItem(String nameOfItem) {
        item = null;
        for (Item i: turfList) {
            if (i.getName().equals(nameOfItem)) {
                item = i;
                break;
            }
        }
        return item;
    }

    // EFFECTS: returns true if item with given name is already in the list
    public boolean contains(String nameOfItem) {
        boolean isContains = false;
        index = 0;
        for (Item t: turfList) {
            if (t.getName().equals(nameOfItem)) {
                isContains = true;
                index = turfList.indexOf(t);
                break;
            } else {
                isContains = false;
            }
        }
        return isContains;
    }
}
