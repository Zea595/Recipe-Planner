package model;

import static org.junit.jupiter.api.Assertions.*;

import model.foodrelated.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestRecipe {
    Recipe spaghet;

    @BeforeEach
    void runBefore(){
        spaghet = new Recipe("Spaghetti with Meatballs.", "Italian");
    }

    @Test
    void testAddIngredient(){

        assertEquals("Tomato Sauce",spaghet.getIngredients().getListAtIndex(0));
        assertEquals("Spaghetti",spaghet.getIngredients().getListAtIndex(1));
        assertEquals("Meatball",spaghet.getIngredients().getListAtIndex(2));
        assertEquals("Parmesan Cheese",spaghet.getIngredients().getListAtIndex(3));
    }

    @Test
    void testAddAndRemoveStep(){
        spaghet.addStep("Shove the pasta into the pot with water");
        // should display the name of recipe with above instructions.
        System.out.println(spaghet.getSteps());

        spaghet.removeStep(1);
        // should display the name of the recipe and default message for no instructions.
        System.out.println(spaghet.getSteps());
    }
}