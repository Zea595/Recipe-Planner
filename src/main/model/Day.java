package model;

import model.foodrelated.Recipe;
import model.persistance.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * This object class represents each day of the week in the planner.
 * Each day has:
 * -A name
 * -A theme
 * -An associated recipe object
 */
public class Day implements Writable {
    String date;
    String theme;
    Recipe recipe;

    //DEFAULT CONSTRUCTOR
    public Day() {
        this.date = "Not Assigned";
        this.theme = "n/a";
        recipe = new Recipe();
    }

    //CONSTRUCTOR FOR JSON
    public Day(String date, String theme) {
        this.date = date;
        this.theme = theme;
    }

    //CONSTRUCTOR
    public Day(String date, String theme, Recipe r) {
        this.date = date;
        this.theme = theme;
        recipe = r;
        recipe.changeDate(date);
    }

    //MODIFIES: recipe
    public void changeRecipe(Recipe r) {
        this.theme = r.getTheme();
        recipe = r;
        r.changeDate(date);
    }

    //MODIFIES: theme
    public void changeTheme(String t) {
        theme = t;
    }

    //EFFECTS: accesses and returns the recipe object's name field
    public String getRecipeName() {
        return recipe.getName();
    }

    //EFFECTS: Accesses the recipe's 'DynamicList' of ingredients and
    // returns them as a printable string.
    public DynamicList getFoodList() {
        return recipe.getIngredients();
    }

    public String getDate() {
        return date;
    }

    public String getTheme() {
        return theme;
    }

    // toString method of the Day's credentials
    public String toString() {
        String dayRecipes = "";

        return "Day{"
                + "date='" + date + '\''
                + ", theme='" + theme + '\''
                + ", recipe='" + recipe.getName() + '\''
                + '}';
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("dayDate", date);
        json.put("dayTheme", theme);
        json.put(date, recipe.toJson());

        return json;
    }

}
