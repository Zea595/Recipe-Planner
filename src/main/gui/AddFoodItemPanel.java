package gui;

import model.foodrelated.FoodItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class generates the GUI for the user to input a new FoodItem
 * This class has 3 textAreas for inputting the name, amount and unit of the FoodItem
 *
 * The constructor requires a ShoppingCheckListPanel object so it can directly modify that
 * object's list of FoodItems after parsing it from user input
 *
 * gives user a prompt after successfully adding the item
 */

public class AddFoodItemPanel extends JPanel {
    private FoodItem food;
    private ShoppingCheckListPanel shopPanel;

    public AddFoodItemPanel(ShoppingCheckListPanel shopPanel) {
        this.shopPanel = shopPanel;
        setLayout(new FlowLayout(FlowLayout.CENTER,10,10));

        //JBUTTONS
        JTextArea foodName = new JTextArea(1,10);
        foodName.setText("Enter the food's name here.");
        JTextArea foodAmount = new JTextArea(1,10);
        foodAmount.setText("Enter the amount here.");
        JTextArea foodUnit = new JTextArea(1,10);
        foodUnit.setText("Enter the unit here.");

        add(foodName);
        add(foodAmount);
        add(foodUnit);

        JButton option = new JButton("Add");
        add(option);
        option.addActionListener(new ActionListener() {
            //Creates new FoodItem with the three fields and calls mutator method shopPanel.addFood in the
            //ShoppingCheckListPanel classadd
            @Override
            public void actionPerformed(ActionEvent e) {
                food = new FoodItem(foodName.getText(), Integer.parseInt(foodAmount.getText()), foodUnit.getText());
                shopPanel.addFood(food);
                JOptionPane.showMessageDialog(null, "Added!");
            }
        });
    }
}
