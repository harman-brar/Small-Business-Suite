package tests;

import category_lists.AggregatesList;
import category_lists.TurfList;
import model.Item;
import model.ListOfItems;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfItemsTest {

    @Test
    public void getSizeAggregates() {
        ListOfItems aggregatesList = new AggregatesList();
        Item a = new Item();
        Item b = new Item();
        aggregatesList.insertItem(a);
        aggregatesList.insertItem(b);
        assertEquals(2, aggregatesList.getSize());
    }

    @Test
    public void getSizeTurf() {
        ListOfItems turfList = new TurfList();
        Item a = new Item();
        Item b = new Item();
        turfList.insertItem(a);
        turfList.insertItem(b);
        assertEquals(2, turfList.getSize());
    }

    @Test
    public void testToStringInsertAggregateList() {
        ListOfItems aggregateList = new AggregatesList();
        Item a = new Item();
        a.setName("Doug");
        a.setAmount("10");
        Item b = new Item();
        b.setName("Bobby");
        b.setAmount("10");
        aggregateList.insertItem(a);
        aggregateList.insertItem(b);
        assertEquals("Doug 10\nBobby 10", aggregateList.toString());
    }

    @Test
    public void testToStringInsertTurfList() {
        ListOfItems turfList = new TurfList();
        Item a = new Item();
        a.setName("Doug");
        a.setAmount("10");
        Item b = new Item();
        b.setName("Bobby");
        b.setAmount("10");
        turfList.insertItem(a);
        turfList.insertItem(b);
        assertEquals("Doug 10\nBobby 10", turfList.toString());
    }

}
