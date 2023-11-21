package com.example.EPICFX;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

// Class representing the graphical user interface for the leaderboard
public class LeaderboardGUI {
    private static JFrame frame;
    private static JPanel panel;
    private static BufferedImage backgroundImage;

    // Method to create and display the leaderboard window
    public static void createLeaderboardWindow() {
        frame = new JFrame("Leaderboard");
        frame.setSize(500, 500);
        frame.setLocation(750, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            // Load the background image from a file.
            backgroundImage = ImageIO.read(new File("C:/Users/dylan/MyRepos/EpicFXWorking/src/main/resources/com/example/EPICFX/ldrboard.png"));

            panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (backgroundImage != null) {
                        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                    }
                }
            };

            panel.setLayout(null); // Set the layout to null for absolute positioning

            Font customFont1 = new Font("Arial", Font.PLAIN, 10);
            Font customFont2 = new Font("Arial", Font.PLAIN, 15);

            LeaderboardEntry[] topPlayers = getTopPlayers(5);

            int y = 145; // Initial vertical position for the labels
            for (LeaderboardEntry entry : topPlayers) {
                JLabel playerLabel = createLabel(entry.getUser(), customFont1);
                playerLabel.setBounds(185, y, 300, 30);

                JLabel scoreLabel = createLabel("" + entry.getMeanScore(), customFont2);
                scoreLabel.setBounds(320, y, 150, 30);

                JLabel timeLabel = createLabel("" + entry.getMeanTime() + "", customFont2);
                timeLabel.setBounds(390, y, 250, 30);

                panel.add(playerLabel);
                panel.add(scoreLabel);
                panel.add(timeLabel);

                y += 68; // Increase vertical position for the next set of labels
            }

        } catch (IOException e) {
            e.printStackTrace();
            panel = new JPanel(); // Fallback if the image loading fails
        }

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    // Method to create a JLabel with specified text and font
    private static JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    // Method to get the top players from the leaderboard data
    private static LeaderboardEntry[] getTopPlayers(int count) {
        LeaderboardEntry[] allEntries = readLeaderboardData("Leaderboard.csv");
        if (allEntries.length == 0) {
            return new LeaderboardEntry[0];
        }

        java.util.Arrays.sort(allEntries, new LeaderboardComparator());

        int numPlayers = Math.min(count, allEntries.length);
        LeaderboardEntry[] topPlayers = new LeaderboardEntry[numPlayers];
        System.arraycopy(allEntries, 0, topPlayers, 0, numPlayers);

        return topPlayers;
    }

    // Method to read leaderboard data from a CSV file
    private static LeaderboardEntry[] readLeaderboardData(String filename) {
        List<LeaderboardEntry> entries = new ArrayList<>();
        try {
            File csvFile = new File(filename);
            if (csvFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(csvFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        String user = parts[0];
                        int meanScore = Integer.parseInt(parts[1]);
                        int meanTime = Integer.parseInt(parts[2]);
                        entries.add(new LeaderboardEntry(user, meanScore, meanTime));
                    }
                }
                reader.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return entries.toArray(new LeaderboardEntry[0]);
    }

    // Main method to invoke the GUI creation
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createLeaderboardWindow());

        LeaderboardGUI gui = new LeaderboardGUI();
        LeaderboardGUI.createLeaderboardWindow();
    }
}
