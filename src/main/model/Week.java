package model;

import model.foodrelated.DeclareConstants;
import model.foodrelated.Recipe;
import model.persistance.Writable;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;


/**
 * Generates a list of 7 "Day" objects using DynamicList
 * Names the days via local machine date.
 * Has methods to randomly add themes to each day.
 */
public class Week implements Writable {
    private Day[] schedule;

    public Week() {
        schedule = new Day[7];
    }

    //MODIFIES day
    public void generateRandomWeekSchedule() {
        Calendar date = Calendar.getInstance();

        for (int i = 0; i < 7; i++) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE-yyyy-MM-dd");
            String d = dateFormat.format(date.getTime());
            String t = generateTheme();
            Recipe r = assignRecipe(t);

            schedule[i] = new Day(d, t, r);

            date.add(Calendar.DAY_OF_MONTH, 1);
        }
    }


    public String generateTheme() {
        int index = new Random().nextInt(DeclareConstants.ThemesDefault.length);
        return DeclareConstants.ThemesDefault[index];
    }

    public Recipe assignRecipe(String theme) {
        for (Recipe e : DeclareConstants.RecipesDefault) {
            if (e.getTheme() == theme) {
                return e;
            }
        }
        // returns blank recipe;
        return new Recipe();
    }

    //REQUIRES d to be 0-6
    public void changeDayRecipe(int d, Recipe r) {
        schedule[d].changeRecipe(r);
    }

    //REQUIRES d to be 0-6
    public void changeDayTheme(int d, String t) {
        schedule[d].changeTheme(t);
    }

    public Day[] getSchedule() {
        return schedule;
    }

    // Strings up the days of the week to be printed on the console. Used for testing and debugging.
    public String toString() {
        String string = "";
        for (int i = 0; i < schedule.length; i++) {
            if (schedule[i] == null) {
                string += "Day not planned." + "\n";
            } else {
                string += schedule[i].toString() + "\n";
            }
        }
        return string;
    }

    public Day getDayAtIndex(int num) {
        return schedule[num];
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("week", scheduleToJson());
        return json;
    }

    private JSONArray scheduleToJson() {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 7; i++) {
            jsonArray.put(schedule[i].toJson());
        }
        return jsonArray;
    }
}
