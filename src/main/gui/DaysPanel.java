package gui;

import model.Week;
import model.foodrelated.Recipe;

import javax.swing.*;
import java.awt.*;

/**
 * This class shows the 7 days of the week and their respective fields as JTextAreas
 * -Date
 * -Theme
 * -Recipe
 *
 */

public class DaysPanel extends JPanel {
    private Week week;
    private JTextArea[] dates;
    private JTextArea[] themes;
    private JTextArea[] recipes;

    public DaysPanel() {
        setLayout(new FlowLayout());
        dates = new JTextArea[7];
        themes = new JTextArea[7];
        recipes = new JTextArea[7];

        for (int i = 0; i < 7; i++) {
            JTextArea a = new JTextArea(2,12);
            a.setText("Empty Day");
            dates[i] = a;
            add(dates[i]);
        }

        for (int i = 0; i < 7; i++) {
            JTextArea a = new JTextArea(10,12);
            a.setText("Empty Theme");
            themes[i] = a;
            add(themes[i]);
        }

        for (int i = 0; i < 7; i++) {
            JTextArea a = new JTextArea(10,12);
            a.setText("Empty Recipe");
            recipes[i] = a;
            add(recipes[i]);
        }
    }

    //EFFECTS: loads data from week and updates the textAreas to display data from week
    public void loadWeek(Week week) {
        this.week = week;
        for (int i = 0; i < 7; i++) {
            dates[i].setText(week.getDayAtIndex(i).getDate());
        }

        for (int i = 0; i < 7; i++) {
            themes[i].setText(week.getDayAtIndex(i).getTheme());
        }

        for (int i = 0; i < 7; i++) {
            recipes[i].setText(week.getDayAtIndex(i).getRecipeName());
        }
    }

    //MODIFIES: week
    //EFFECTS: updates the data for the week by iteratively going through them
    public Week updateWeek(Week w) {
        for (int i = 0; i < 7; i++) {
            Recipe r = new Recipe(recipes[i].getText(), themes[i].getText());
            w.changeDayRecipe(i,r);
            w.changeDayTheme(i,themes[i].getText());
        }
        return w;
    }
}
