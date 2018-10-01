package category_lists;

import model.Item;
import model.ListOfItems;
import product_categories.Turf;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TurfList implements ListOfItems {
    List<Turf> turfList;
    int index;

    public TurfList() {
        turfList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds Turf t to turfList and then prints that it has been add
    /*public void insertItem(Turf t) {
        turfList.add(t);
        System.out.println("Added item " + t.getName() + " to the category list for Turf");
    }*/

    // EFFECTS: returns number of items in turfList
    @Override
    public int size() {
        return turfList.size();
    }

    // EFFECTS: returns string statement of turfList
    @Override
    public String show() {
        String listString = "[ Turf ]: " + turfList.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
        return listString;
    }

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

    public boolean contains(String nameOfItem) {
        boolean isContains = false;
        index = 0;
        for (Turf t: turfList) {
            if (t.name.equals(nameOfItem)) {
                isContains = true;
                index = turfList.indexOf(t);
                break;
            } else {
                isContains = false;
            }
        }
        return isContains;
    }

    public Turf createTurf(String nameOfItem) {
        if (!contains(nameOfItem)) {
            Turf itemToAdd = new Turf();
            itemToAdd.name = nameOfItem;
            turfList.add(itemToAdd);
            System.out.println("Item " + nameOfItem + " added to Turf list");

            return itemToAdd;
        } else {
            return turfList.get(index);
        }
    }
}
