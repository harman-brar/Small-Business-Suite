package tests.exceptionsTest;

import exceptions.NegativeNumberException;
import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class ExceptionsTest {
    Item a;

    @BeforeEach
    void setUp() {
        a = new Item();
        a.setAmount("100");
    }

    @Test
    void testNumberNegative() {
        try {
            a.performAdd("-5");
            fail("Did not throw exception");
        } catch (NegativeNumberException e) {
            System.out.println("Caught exception");
        }

    }

    @Test
    void testNumberNotNegative() {
        try {
            a.performAdd("5");
            System.out.println("pass");
        } catch (NegativeNumberException e) {
            fail("Unexpected exception");
        }
    }

    @Test
    void testNumberIsNumber() {
        try {
            a.performAdd("5");
            System.out.println("pass");
        } catch (NumberFormatException e) {
            fail("Unexpected exception");
        } catch (NegativeNumberException e) {
            fail("Unexpected exception");
        }

    }

    @Test
    void testNumberIsNotNumber() {
        try {
            a.performAdd("hello");
            fail("Not a number");
        } catch (NumberFormatException e) {
            System.out.println("pass");
        } catch (NegativeNumberException e) {
            fail("Unexpected exception");
        }
    }

}
