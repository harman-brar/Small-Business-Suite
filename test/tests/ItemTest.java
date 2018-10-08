package tests;

import model.Item;
import org.junit.jupiter.api.Test;
import product_categories.Aggregate;
import product_categories.Turf;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    @Test
    public void testGetSetName() {
        Item a = new Aggregate();
        a.setName("Bob");
        assertEquals("Bob", a.getName());
    }

    @Test
    public void testGetSetAmount() {
        Item a = new Aggregate();
        a.setAmount("2");
        assertEquals(2, a.getAmount());
    }


    @Test
    public void testToString() {
        Item a = new Aggregate();
        a.setName("Doug");
        a.setAmount("10");
        assertEquals("Doug 10", a.toString());
    }


    @Test
    public void performAdd() {
        Item a = new Aggregate();
        a.performAdd("10");
        assertEquals(10, a.getAmount());
    }


    @Test
    public void performRemoval() {
        Item a = new Turf();
        a.performAdd("10");
        assertEquals(10, a.getAmount());
        a.performRemoval("8");
        assertEquals(2, a.getAmount());
    }
}
