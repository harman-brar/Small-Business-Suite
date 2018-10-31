package tests.modelTest;

import model.ListOfItems;
import model.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListOfItemsTest {

    @Test
    public void getSize() {
        model.ListOfItems aggregatesList = new ListOfItems();
        Item a = new Item();
        Item b = new Item();
        aggregatesList.insertItem(a);
        aggregatesList.insertItem(b);
        assertEquals(2, aggregatesList.getSize());
    }


    @Test
    public void testToStringInsertList() {
        model.ListOfItems aggregateList = new ListOfItems();
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

}
