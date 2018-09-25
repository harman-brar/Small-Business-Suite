package tests;

import catalogue.Item;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    private static final String name = "Mort";

    @Test
    public void testConstructor() {
        Item item = new Item();
        item.name = "Mort";
        assertEquals(name, item.name);
        assertEquals(0, item.amount);
    }
}
