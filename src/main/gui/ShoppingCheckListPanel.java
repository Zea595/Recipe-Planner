package gui;

import model.DynamicList;
import model.foodrelated.FoodItem;

import javax.swing.*;
import java.util.Arrays;

/**
 * This class is responsible for displaying all the stored FoodItems as checkBoxes to the user
 *
 */

public class ShoppingCheckListPanel extends JPanel {
    private DynamicList foodList;
    private DynamicList checkBoxList;

    public ShoppingCheckListPanel() {
        this.foodList = new DynamicList();
        checkBoxList = new DynamicList();
    }

    public void sortFoodList() {
        Arrays.sort(checkBoxList.getList());
    }

    public void addFood(FoodItem food) {
        foodList.addToList(food);
    }

    //EFFECTS: iterates through foodList and creates new checkBoxes in a boxlayout format
    public void generateShoppingList() {
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        for (int i = 0; i < foodList.getListSize(); i++) {
            FoodItem temp = (FoodItem) foodList.getListAtIndex(i);
            String name = temp.getName();
            int amount = temp.getAmount();
            String units = temp.getUnit();

            JCheckBox checkBox = new JCheckBox(name + " : " + amount + " " + units);
            checkBoxList.addToList(checkBox);
            add(checkBox);
        }
    }

}
