package tests;

import model.Item;
import org.junit.jupiter.api.Test;
import product_categories.Aggregate;
import product_categories.Turf;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    @Test
    public void testGetSetNameAggregate() {
        Item a = new Aggregate();
        a.setName("Bob");
        assertEquals("Bob", a.getName());
    }

    @Test
    public void testGetSetNameTurf() {
        Item a = new Turf();
        a.setName("Bob");
        assertEquals("Bob", a.getName());
    }

    @Test
    public void testGetSetAmountAggregate() {
        Item a = new Aggregate();
        a.setAmount("2");
        assertEquals(2, a.getAmount());
    }

    @Test
    public void testGetSetAmountTurf() {
        Item a = new Turf();
        a.setAmount("2");
        assertEquals(2, a.getAmount());
    }

    @Test
    public void testToStringAggregate() {
        Item a = new Aggregate();
        a.setName("Doug");
        a.setAmount("10");
        assertEquals("Doug 10", a.toString());
    }

    @Test
    public void testToStringTurf() {
        Item a = new Turf();
        a.setName("Doug");
        a.setAmount("10");
        assertEquals("Doug 10", a.toString());
    }

    @Test
    public void performAddAggregate() {
        Item a = new Aggregate();
        a.performAdd("10");
        assertEquals(10, a.getAmount());
    }

    @Test
    public void performAddTurf() {
        Item a = new Turf();
        a.performAdd("10");
        assertEquals(10, a.getAmount());
    }

    @Test
    public void performRemovalAggregate() {
        Item a = new Aggregate();
        a.performAdd("10");
        assertEquals(10, a.getAmount());
        a.performRemoval("8");
        assertEquals(2, a.getAmount());
    }

    @Test
    public void performRemovalTurf() {
        Item a = new Turf();
        a.performAdd("10");
        assertEquals(10, a.getAmount());
        a.performRemoval("8");
        assertEquals(2, a.getAmount());
    }
}
