package tests;

import catalogue.Item;
import catalogue.InventoryCatalogue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryCatalogueTest {
    private ArrayList<Item> itemsList = new ArrayList<>();
    private Item item = new Item();
    private InventoryCatalogue cat;

    @BeforeEach
    void setUp() {
        cat = new InventoryCatalogue();
        itemsList = new ArrayList<>();
        item = new Item();
    }

    @Test
    void testInsertItem() {
        assertEquals(0, itemsList.size());
        cat.insertItem(item);
        assertEquals(1, itemsList.size());
    }

    @Test
    void testCreateItem() {
        assertEquals(0, itemsList.size());
        cat.createItem("Bob");
        assertEquals(1, itemsList.size());
        assertEquals("Bob", itemsList.get(0).name);
    }

    @Test
    void testPerformAddAmountNotZero() {
        assertEquals(0, item.amount);
        cat.performAdd(String.valueOf(10), item);
        assertEquals(10, item.amount);
    }
    @Test
    void testPerformAddAmountZero() {
        assertEquals(0, item.amount);
        cat.performAdd(String.valueOf(0), item);
        assertEquals(0, item.amount); // add 10 in interaction
    }

    @Test
    void testPerformRemovalAmountNotZero() {
        item.amount = 10;
        assertEquals(10, item.amount);
        cat.performRemoval(String.valueOf(10), item);
        assertEquals(0, item.amount); // add 10 in interaction
    }

    @Test
    void testPerformRemovalAmountZero() {
        item.amount = 10;
        assertEquals(10, item.amount);
        cat.performRemoval(String.valueOf(0), item);
        assertEquals(10, item.amount); // add 10 in interaction
    }
}
