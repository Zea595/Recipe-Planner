package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Main {
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 800;
    public static BufferedImage image;

    public static void main(String[] args) throws IOException {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame frame = new MainFrame("Week Recipe Planner");

                JLabel background = new JLabel(new ImageIcon(
                        "C:\\Users\\Henry\\IdeaProjects\\project_b6k4h\\data\\Food.jpg"));
                background.setPreferredSize(new Dimension(WINDOW_WIDTH, 100));
                frame.add(background, BorderLayout.NORTH);

                frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });

    }
}
