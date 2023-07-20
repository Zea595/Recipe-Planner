package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestWeek {
    Week week1;
    Week week2;
    ShoppingList shop1;

    @BeforeEach
    void runBefore(){
        week1 = new Week();
        week2 = new Week();
        shop1 = new ShoppingList(week1);
    }

    @Test
    void testGenerateRandomWeek(){
        week1.generateRandomWeekSchedule();

        System.out.println(week1.toString());
    }

    @Test
    void testShoppingList(){
        week1.generateRandomWeekSchedule();
        //System.out.println(week1.toString());

        shop1.generateShoppingList();
        //System.out.println(shop1);
    }




}
