package model.foodrelated;

import org.json.JSONObject;

/**
 * Basic object class representing ingredient items.
 * Each fooditem is an individual ingredient in recipe class list
 * <p>
 * name represents the name of the ingredient
 * unit represents the unit of measurement the ingredient is quantified in
 * amount is the integer representation of unit
 */

public class FoodItem implements Comparable<FoodItem> {
    private String name;
    private String unit;
    private int amount;

    public FoodItem(String n, int a, String u) {
        name = n;
        amount = a;
        unit = u;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    //MODIFIES: amount
    //REQUIRES: a to be a positive integer
    public void add(int a) {
        amount += a;
    }

    //MODIFIES: amount
    //REQUIRES: a to be a positive integer
    public void subtract(int a) {
        amount -= a;
    }

    public String toString() {
        return name + " : " + amount + " " + unit;
    }

    @Override
    public int compareTo(FoodItem o) {
        return toString().compareTo(o.toString());
    }

    //EFFECTS: returns this object as a jsonObject
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("foodName", name);
        json.put("amount", amount);
        json.put("unit", unit);
        return json;
    }


}
