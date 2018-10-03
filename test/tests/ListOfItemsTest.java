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

    @Test
    public void testCreateAggregate() {

    }

    @Test
    public void testDeleteAggregate() {

    }

    @Test
    public void testCreateTurf() {

    }

    @Test
    public void testDeleteTurf() {

    }
}
