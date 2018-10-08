package tests;

import category_lists.AggregatesList;
import category_lists.TurfList;
import model.Item;
import model.ListOfItems;
import org.junit.jupiter.api.Test;
import product_categories.Aggregate;
import product_categories.Turf;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfItemsTest {

    @Test
    public void getSizeAggregates() {
        ListOfItems aggregatesList = new AggregatesList();
        Item a = new Aggregate();
        Item b = new Aggregate();
        aggregatesList.insertItem(a);
        aggregatesList.insertItem(b);
        assertEquals(2, aggregatesList.getSize());
    }

    @Test
    public void getSizeTurf() {
        ListOfItems turfList = new TurfList();
        Item a = new Turf();
        Item b = new Turf();
        turfList.insertItem(a);
        turfList.insertItem(b);
        assertEquals(2, turfList.getSize());
    }

    @Test
    public void testToStringInsertAggregateList() {
        ListOfItems aggregateList = new AggregatesList();
        Item a = new Aggregate();
        a.setName("Doug");
        a.setAmount("10");
        Item b = new Aggregate();
        b.setName("Bobby");
        b.setAmount("10");
        aggregateList.insertItem(a);
        aggregateList.insertItem(b);
        assertEquals("Doug 10\nBobby 10", aggregateList.toString());
    }

    @Test
    public void testToStringInsertTurfList() {
        ListOfItems turfList = new TurfList();
        Item a = new Turf();
        a.setName("Doug");
        a.setAmount("10");
        Item b = new Turf();
        b.setName("Bobby");
        b.setAmount("10");
        turfList.insertItem(a);
        turfList.insertItem(b);
        assertEquals("Doug 10\nBobby 10", turfList.toString());
    }

}
