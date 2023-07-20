package model;

import model.foodrelated.FoodItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Generated shopping list based on each the week's recipe ingredients.
 */
public class ShoppingList {

    //The week associated with this shopping list
    private Week week;
    private DynamicList checkList;
    private DynamicList removeList;

    //For testing
    public ShoppingList() {
        this.week = new Week();
        checkList = new DynamicList();
    }

    // CONSTRUCTOR
    public ShoppingList(Week week) {
        this.week = week;
        checkList = new DynamicList(); // list of FoodItem
        generateShoppingList();
    }

    // EFFECTS: Accesses getter methods from each day of the week and adds all foodList entries
    // to checklist (FoodItem Objects).
    public void generateShoppingList() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < week.getSchedule()[i].recipe.getIngredientListSize(); j++) {
                checkList.addToList(week.getSchedule()[i].getFoodList().getListAtIndex(j));
            }
        }
    }


    //EFFECTS: searches through checklist for an item that matches "item" and removes 'num' amount from it
    // deletes the item if num is greater or equal to the amount and adds it to the list of removed items
    //REQUIRES: num to be greater than 1
    public boolean checkOff(String item, int num) {
        for (int i = 0; i < checkList.getListSize(); i++) {
            FoodItem temp = (FoodItem) checkList.getListAtIndex(i);

            String tempName = temp.getName().toLowerCase();
            item = item.toLowerCase();
            if (tempName.equals(item)) {
                if (num >= temp.getAmount()) {
                    checkList.removeFromList(temp);
                    removeList.addToList(temp);
                } else {
                    temp.subtract(num);
                }
                return true;
            }
        }
        return false;
    }

    //for testing purposes
    public DynamicList getCheckList() {
        return checkList;
    }

    public DynamicList getRemoveList() {
        return removeList;
    }

    // implement sorting
    public void sortCheckList() {
        Arrays.sort(checkList.getList());
    }

    @Override
    public String toString() {
        String line = "";
        for (Object e : checkList.getList()) {
            line += e.toString() + "\n";
        }
        return line;
    }
}
