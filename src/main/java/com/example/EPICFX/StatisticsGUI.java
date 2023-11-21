package com.example.EPICFX;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatisticsGUI {
    private static BufferedImage backgroundImage;

    static User player = new User(); // Create an instance of the User class

    // Main method for testing
    public static void main(String[] args) {
        ShowStatistics(player.getScore(), player.getTime(), 10, 10, 10);
    }

    // Method to display statistics in a GUI window
    public static void ShowStatistics(int CurrentScore, long CurrentTime, int MeanScore, int SDScores, int MeanTime) {
        JFrame frame = new JFrame("Show Statistics");
        frame.setSize(500, 500);
        frame.setLocation(250, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            backgroundImage = ImageIO.read(new File("C:/Users/dylan/MyRepos/EpicEndevour2/src/main/resources/com/example/EPICFX/your-scores.png"
            ));
        } catch (IOException var14) {
            var14.printStackTrace();
        }

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (StatisticsGUI.backgroundImage != null) {
                    g.drawImage(StatisticsGUI.backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
                }
            }
        };
        panel.setOpaque(false);
        frame.setContentPane(panel);
        panel.setLayout((LayoutManager)null);
        Font customFont = new Font("Arial", 0, 25);

        // Create labels for displaying statistics
        JLabel currentScoreLabel = createLabel("" + CurrentScore, 325, 138, 380, 20, customFont);
        JLabel currentTimeLabel = createLabel("" + CurrentTime + " seconds", 325, 190, 380, 20, customFont);
        JLabel meanScoreLabel = createLabel("" + MeanScore, 325, 293, 380, 20, customFont);
        JLabel sdScoresLabel = createLabel("" + SDScores, 325, 340, 380, 20, customFont);
        JLabel meanTimeLabel = createLabel("" + MeanTime + " seconds", 325, 390, 380, 20, customFont);

        // Add labels to the panel
        panel.add(currentScoreLabel);
        panel.add(currentTimeLabel);
        panel.add(meanScoreLabel);
        panel.add(sdScoresLabel);
        panel.add(meanTimeLabel);

        frame.setVisible(true);
    }

    // Method to create a JLabel with specified properties
    private static JLabel createLabel(String text, int x, int y, int width, int height, Font font) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(font);
        return label;
    }
}
