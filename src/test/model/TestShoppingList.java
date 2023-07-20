package model;

import static org.junit.jupiter.api.Assertions.*;

import model.foodrelated.FoodItem;
import model.persistance.JsonReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class TestShoppingList {
    ShoppingList shop1;

    @BeforeEach
    public void runBefore() {
        shop1 = new ShoppingList();
        shop1.getCheckList().addToList(new FoodItem("Bread", 5, "Loaves"));
        shop1.getCheckList().addToList(new FoodItem("Cheese", 2, "Blocks"));
        shop1.getCheckList().addToList(new FoodItem("Apple", 20, "Bushels"));
    }

    @Test
    public void testCheckOff() {
        shop1.checkOff("Bread", 3);
        FoodItem f = (FoodItem) shop1.getCheckList().getListAtIndex(0);
        assertEquals(2, f.getAmount());

        shop1.checkOff("Cheese", 2);
        assertEquals(2, shop1.getCheckList().getListSize());

        shop1.checkOff("Apple", 30);
        assertEquals(1, shop1.getCheckList().getListSize() );
    }

    @Test
    public void testGenerateShoppingList() {
        JsonReader jsonReader = new JsonReader("./data/standardWeekCopy.json");
        Week week = new Week();
        try {
            week = jsonReader.read();

            shop1 = new ShoppingList(week);
            System.out.println(shop1.toString());

        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }

    @Test
    public void testToString() {
        assertEquals("Bread : 5 Loaves" + "\n"
                + "Cheese : 2 Blocks" + "\n"
                + "Apple : 20 Bushels" + "\n", shop1.toString());
    }

    @Test
    public void testSortShoppingList() {
        System.out.println("======================");
        System.out.println(shop1.toString());
        shop1.sortCheckList();
        System.out.println("======================");
        System.out.println(shop1.toString());

    }

}
