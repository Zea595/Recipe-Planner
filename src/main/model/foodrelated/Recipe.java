package model.foodrelated;

import model.DynamicList;
import model.persistance.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Recipe class contains 2 strings and 2 "DynamicList".
 * Name is the recipe name.
 * Theme is the recipe's cultural origin (e.g. Italian, French, Thai...)
 * 1st list is a "DynamicList" of strings that show the steps for cooking the recipe.
 * 2nd list is a "DynamicList" of ingredients needed for the recipe.
 */
public class Recipe implements Writable {

    private String name;
    private String theme;
    private DynamicList steps;
    private DynamicList ingredients;
    private String date;

    // DEFAULT CONSTRUCTOR
    public Recipe() {
        this.name = "Blank recipe";
        this.theme = "No theme";
        steps = new DynamicList();
        ingredients = new DynamicList();
    }

    // CONSTRUCTOR
    public Recipe(String name, String theme) {
        this.name = name;
        this.theme = theme;
        steps = new DynamicList();
        ingredients = new DynamicList();
        this.date = "";
    }

    // CONSTRUCTOR FOR JSON
    public Recipe(String name, String theme, String date) {
        this.name = name;
        this.theme = theme;
        steps = new DynamicList();
        ingredients = new DynamicList();
        this.date = date;
    }

    public void changeDate(String d) {
        date = d;
    }

    // MODIFIES steps
    // adds a new line of instructions to the recipe
    public void addStep(String instruction) {
        steps.addToList(instruction);
    }

    //MODIFIES steps
    //REQUIRES x to be < steps.length and +1 of array index
    // removes entries at given index
    public void removeStep(int x) {
        steps.removeIndex(x - 1);
    }

    // MODIFIES ingredients
    // adds a new entry to list of ingredients
    public void addIngredient(String ingredient, int amount, String unit) {
        ingredient.toLowerCase();

        FoodItem f = new FoodItem(ingredient, amount, unit);
        ingredients.addToList(f);
    }

    // returns a numbered list in string format of the recipe's instructions.
    // returns a string saying theres no instructions if list is default length.
    public String getSteps() {
        String temp = "";
        temp += "Recipe: " + name + "\n";

        if (steps.getListSize() == 0) {
            return temp + "Looks like there are no instructions for this recipe yet." + "\n";
        } else {
            for (int i = 0; i < steps.getListSize(); i++) {
                if (steps.getListAtIndex(i) == null) {
                    i++;
                } else {
                    temp += (i + 1) + ": " + steps.getListAtIndex(i) + "." + "\n";
                }
            }
            return temp;
        }
    }


    // getters
    public DynamicList getIngredients() {
        return ingredients;
    }

    public int getStepSize() {
        return steps.getListSize();
    }

    public int getIngredientListSize() {
        return ingredients.getListSize();
    }

    public String getTheme() {
        return theme;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String toString() {
        return "|| Name: " + name + " Theme: " + theme + " ||";
    }

    //Save and Load
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("recipeName", name);
        json.put("recipeTheme", theme);
        json.put("ingredients" + name, ingredientsToJson());
        return json;
    }

    public JSONArray ingredientsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < ingredients.getListSize(); i++) {
            FoodItem f = (FoodItem) ingredients.getListAtIndex(i);
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }


}
