package model;

import model.foodrelated.DeclareConstants;
import model.foodrelated.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDay {
    Day day1;
    Day day2;

    @BeforeEach
    void runBefore() {
        day1 = new Day();
    }

    @Test
    void testChangeRecipe() {

        assertEquals("Blank recipe", day1.getRecipeName());

        Recipe r = DeclareConstants.RecipesDefault[1]; //Ramen
        day1.changeRecipe(r);

        assertEquals("Ramen", day1.getRecipeName());
    }

    @Test
    void testChangeTheme() {
        assertEquals("n/a", day1.getTheme());

        day1.changeTheme(DeclareConstants.ThemesDefault[4]); //JAPANESE

        assertEquals("JAPANESE", day1.getTheme());
    }

    @Test
    void testGetIngredients(){
        day1.changeRecipe(DeclareConstants.RecipesDefault[4]);

        for(int i = 0; i < DeclareConstants.ingredientBurgerWithFries.length; i++){
            DeclareConstants.RecipesDefault[4].addIngredient("Buns", 10, "Loaves");
        }
        //System.out.println(day1.getIngredients());
    }
}
