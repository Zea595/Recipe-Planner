package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDynamicList {
    DynamicList test;

    @BeforeEach
    void runBefore(){
        test = new DynamicList();
    }

    @Test
    void testGetListSize(){
        test.addToList("Foo");
        test.addToList("Bar");
        test.addToList("Jun");
        test.addToList("Car");

        assertEquals(4, test.getListSize());
    }

    @Test
    void testGetListAtIndex(){
        test.addToList("Foo");
        test.addToList("Bar");
        test.addToList("Jun");
        test.addToList("Car");

        assertEquals("Foo", test.getListAtIndex(0));
        assertEquals("Bar", test.getListAtIndex(1));
        assertEquals("Jun", test.getListAtIndex(2));
        assertEquals("Car", test.getListAtIndex(3));
    }

    @Test
    void testAddToList(){
        test.addToList("Foo");
        test.addToList("Bar");

        assertEquals("Foo", test.getListAtIndex(0));
        assertEquals("Bar", test.getListAtIndex(1));
        assertEquals(2, test.getListSize());
    }

    @Test
    void testRemoveFromList(){
        test.addToList("Foo");
        test.addToList("Bar");

        test.removeFromList("Foo");
        assertEquals(1, test.getListSize());
        assertEquals("Bar", test.getListAtIndex(0));
    }

    @Test
    void testRemoveIndex(){
        test.addToList("Foo");
        test.addToList("Bar");
        test.addToList("Jun");
        test.addToList("Car");

        test.removeIndex(2);
        assertEquals(3, test.getListSize());
        assertEquals("Car", test.getListAtIndex(2));

        test.removeIndex(0);
        assertEquals(2, test.getListSize());
        assertEquals("Bar", test.getListAtIndex(0));
        assertEquals("Car", test.getListAtIndex(1));
    }

    @Test
    void testPrintList(){
        test.addToList("Foo");
        test.addToList("Bar");
        test.addToList("Jun");
        test.addToList("Car");

        test.printList();
    }

}
