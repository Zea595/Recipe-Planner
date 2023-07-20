package model;

import model.foodrelated.FoodItem;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFoodItem {
    FoodItem f1;

    @BeforeEach
    public void runBefore() {
        f1 = new FoodItem("Banana", 10, "bunches");
    }

    @Test
    public void testArithmetic() {
        f1.add(20);
        assertEquals(30, f1.getAmount());

        f1.subtract(10);
        assertEquals(20, f1.getAmount());
    }
}
