package tests;

import exceptions.CapacityReachedException;
import exceptions.NegativeNumberException;
import model.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    @Test
    public void testGetSetName() {
        Item a = new Item();
        a.setName("Bob");
        assertEquals("Bob", a.getName());
    }

    @Test
    public void testGetSetAmount() {
        Item a = new Item();
        a.setAmount("2");
        assertEquals(2, a.getAmount());
    }


    @Test
    public void testToString() {
        Item a = new Item();
        a.setName("Doug");
        a.setAmount("10");
        assertEquals("Doug 10", a.toString());
    }


    @Test
    public void performAdd() {
        Item a = new Item();
        try {
            a.performAdd("10");
        } catch (NegativeNumberException | CapacityReachedException e) {
            e.printStackTrace();
        }
        assertEquals(10, a.getAmount());
    }


    @Test
    public void performRemoval() {
        Item a = new Item();
        try {
            a.performAdd("10");
        } catch (NegativeNumberException | CapacityReachedException e) {
            e.printStackTrace();
        }
        assertEquals(10, a.getAmount());
        try {
            a.performRemoval("8");
        } catch (NegativeNumberException e) {
            System.out.println("Please enter a valid number.");
        }
        assertEquals(2, a.getAmount());
    }
}
