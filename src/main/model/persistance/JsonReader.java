package model.persistance;

import model.Day;
import model.Week;
import model.foodrelated.FoodItem;
import model.foodrelated.Recipe;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Referenced from jsonReader provided in the phase 2 documentation
 */

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Week read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseWeek(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses week from JSON object and returns it
    private Week parseWeek(JSONObject jsonObject) {
        JSONArray oldWeek = jsonObject.getJSONArray("week");
        Week w = new Week();
        int i = 0;

        for (Object json : oldWeek) {
            JSONObject nextItem = (JSONObject) json;
            Day d = new Day(nextItem.getString("dayDate"), nextItem.getString("dayTheme"));
            w.getSchedule()[i] = addRecipeToDay(d, nextItem);
            i++;
        }
        return w;
    }

    // MODIFIES: Recipe
    // EFFECTS: parses ingredients from JSON object and adds them to recipe
    private void addIngredientsToRecipe(Recipe r, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("ingredients" + r.getName());
        for (Object json : jsonArray) {
            JSONObject nextItem = (JSONObject) json;
            FoodItem i = new FoodItem(nextItem.getString("foodName"),
                    nextItem.getInt("amount"), nextItem.getString("unit"));
            r.getIngredients().addToList(i);
        }
    }

    // MODIFIES: Day
    // EFFECTS: parses recipes from JSON object and adds it to day
    private Day addRecipeToDay(Day d, JSONObject jsonObject) {
        JSONObject dayObject = jsonObject.getJSONObject(d.getDate());

        Recipe recipe = new Recipe(dayObject.getString("recipeName"), dayObject.getString("recipeTheme"), d.getDate());
        addIngredientsToRecipe(recipe, dayObject);
        d.changeRecipe(recipe);

        return d;
    }
}
