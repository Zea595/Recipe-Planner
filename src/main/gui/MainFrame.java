package gui;

import model.Week;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    Week week;
    Container container;
    WeekPanel weekpanel;
    DaysPanel dayspanel;


    //Effects: displays all elements and JPanels except the ones made by the buttons in WeekPanel
    public MainFrame(String title) {
        super(title);
        week = new Week();
        container = getContentPane();
        weekpanel = new WeekPanel(week, this);
        // sets default days
        dayspanel = new DaysPanel();

        setLayout(new BorderLayout());
        container.add(weekpanel, BorderLayout.WEST);
        container.add(dayspanel, BorderLayout.CENTER);
    }

    //EFFECTS: loads in data and updates the fields for the textAreas of the 7 days of the week using data from
    //the json directory (I put this method here to pass this.week to the button method in weekPanel)
    public void updateDays(Week w) {
        dayspanel.loadWeek(w);
    }

    //EFFECTS: generates random week, used for button method in weekPanel
    public Week updateWeek(Week w) {
        return dayspanel.updateWeek(w);
    }


}
