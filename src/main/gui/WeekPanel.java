package gui;

import model.Week;
import model.persistance.JsonReader;
import model.persistance.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This is the class that controls the buttons on the side
 * Each button function is split into their own respective methods
 *
 * Buttons that create new windows create the JPanels in their run() mouseEvent methods
 * BEWARE that almost every button does the windows "beep" sound, sorry if that is very annoying
 */

public class WeekPanel extends JPanel {
    private static final int BUTTON_WIDTH = 5;
    private ShoppingCheckListPanel shopPanel;
    private Week week;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_PATH = "./data/week.json";
    private MainFrame frame;
    private GridBagConstraints constraint;

    public WeekPanel(Week week, MainFrame frame) {
        this.week = week;
        this.frame = frame;
        this.shopPanel = new ShoppingCheckListPanel();
        Dimension size = getPreferredSize();
        size.width = 150;
        setPreferredSize(size);
        jsonWriter = new JsonWriter(JSON_PATH);
        jsonReader = new JsonReader(JSON_PATH);

        //SET LAYOUT
        setLayout(new GridBagLayout());
        constraint = new GridBagConstraints();
        constraint.gridwidth = BUTTON_WIDTH;
        constraint.insets = new Insets(5,0,5,0);
        constraint.fill = GridBagConstraints.HORIZONTAL;

        //GENERATE BUTTONS
        generateWeek();
        showShoppingList();
        addFoodItem();
        save();
        load();
        exit();
    }

    //MODIFIES: week
    //EFFECTS: generates a week with randomized themes and their associated recipes
    public void generateWeek() {
        JButton option1 = new JButton("Generate Week");
        constraint.gridx = 0;
        constraint.gridy = 0;
        add(option1, constraint);

        option1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit.getDefaultToolkit().beep();
                week.generateRandomWeekSchedule();
                frame.setVisible(false);
                frame.updateDays(week);
                frame.setVisible(true);
            }
        });
    }

    //EFFECTS: beeps and shows ShoppingCheckListPanel that has a list of checkBoxes in
    // a new window
    public void showShoppingList() {
        JButton option2 = new JButton("Show Shopping List");
        constraint.gridx = 0;
        constraint.gridy = 1;
        add(option2, constraint);

        option2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Toolkit.getDefaultToolkit().beep();
                        JFrame frame = new JFrame("Ingredients Checklist");
                        frame.setSize(600, 600);
                        shopPanel.generateShoppingList();
                        frame.add(shopPanel);
                        frame.setVisible(true);
                    }
                });
            }
        });
    }

    //MODIFIES: ShopPanel
    //EFFECTS: Creates a new AddFoodItemPanel that has fields that updates ShoppingList
    public void addFoodItem() {
        JButton option = new JButton("Add FoodItem");
        constraint.gridx = 0;
        constraint.gridy = 2;
        add(option, constraint);

        option.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Toolkit.getDefaultToolkit().beep();
                JFrame frame = new JFrame("Add Ingredients");
                frame.setSize(600, 600);
                frame.add(new AddFoodItemPanel(shopPanel));
                frame.setVisible(true);
            }
        });
    }

    //EFFECTS: beeps and saves THEME and RECIPE name that is currently displayed on the calendar to the directory
    public void save() {
        JButton option3 = new JButton("Save");
        constraint.gridx = 0;
        constraint.gridy = 3;
        add(option3, constraint);

        option3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Toolkit.getDefaultToolkit().beep();
                    week = frame.updateWeek(week);
                    jsonWriter.open();
                    jsonWriter.write(week);
                    jsonWriter.close();
                    JOptionPane.showMessageDialog(null,"Saved to: " + JSON_PATH);
                } catch (FileNotFoundException r) {
                    JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_PATH);
                }
            }
        });
    }

    // EFFECTS: beeps system and loads the saved week from the directory and refreshes the jframe
    public void load() {

        JButton option4 = new JButton("Load");
        constraint.gridx = 0;
        constraint.gridy = 4;
        add(option4, constraint);

        option4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    week = jsonReader.read();
                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null,"Loaded from: " + JSON_PATH);
                } catch (IOException r) {
                    JOptionPane.showMessageDialog(null,"Unable to read from file: " + JSON_PATH);
                }
                frame.setVisible(false);
                frame.updateDays(week);
                frame.setVisible(true);
            }
        });

    }

    //EFFECTS: exits the program safely
    public void exit() {
        JButton option5 = new JButton("Exit");
        constraint.gridx = 0;
        constraint.gridy = 5;
        constraint.weighty = 5;
        add(option5, constraint);

        option5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }


}
