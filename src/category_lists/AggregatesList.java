package category_lists;

import model.Item;
import model.ListOfItems;
import product_categories.Aggregate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AggregatesList implements ListOfItems {
    List<Aggregate> aggregateList;
    int index;

    public AggregatesList() {
        aggregateList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds Aggregate a to aggregateList then it is printed that it is added
    public void insertItem(Aggregate a) {
        aggregateList.add(a);
        System.out.println("Added item " + a.getName() + " to the category list for Aggregates");
    }

    // EFFECTS: returns size of aggregateList
    @Override
    public int size() {
        return aggregateList.size();
    }

    // EFFECTS: returns string statement of aggregateList
    @Override
    public String show() {
        String listString = "[ Aggregates ]: " + aggregateList.stream().map(Object::toString)
                .collect(Collectors.joining(", "));
        return listString;
    }

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

    public boolean contains(String nameOfItem) {
        boolean isContains = false;
        index = 0;
        for (Aggregate a: aggregateList) {
            if (a.name.equals(nameOfItem)) {
                isContains = true;
                index = aggregateList.indexOf(a);
                break;
            } else {
                isContains = false;
            }
        }
        return isContains;
    }

    public Aggregate createAggregate(String nameOfItem) {
        if (!contains(nameOfItem)) {
            Aggregate itemToAdd = new Aggregate();
            itemToAdd.name = nameOfItem;
            aggregateList.add(itemToAdd);
            System.out.println("Item " + nameOfItem + " added to Aggregates list");

            return itemToAdd;
        } else {
            return aggregateList.get(index);
        }
    }
}
