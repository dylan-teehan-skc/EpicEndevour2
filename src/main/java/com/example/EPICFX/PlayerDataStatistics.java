package com.example.EPICFX;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.example.EPICFX.Leaderboard.WriteToLeaderboard;
import static com.example.EPICFX.StatisticsGUI.player;

// Class to handle player data statistics
public class PlayerDataStatistics {
    public PlayerDataStatistics() {
    }

    // Main method to demonstrate and handle player data statistics
    public static void main(String[] args) {
        PlayerData(player.getUsername(), player.getScore(), player.getTime());
    }

    // Method to process player data and calculate statistics
    public static void PlayerData(String user, int score, long second) {
        PlayerDataWriter userDataWriter = new PlayerDataWriter(user);
        File csvFile = userDataWriter.getCSVFile();
        int[] statistics = calculateStatistics(csvFile);
        System.out.println("Mean Score: " + statistics[0]);
        player.setAverageScore(statistics[0]);
        System.out.println("Standard Deviation of Score: " + statistics[1]);
        player.setSdScore(statistics[1]);
        System.out.println("Mean Time in Seconds: " + statistics[2]);
        player.setAverageTime(statistics[2]);
        StatisticsGUI.ShowStatistics(score, second, statistics[0], statistics[1], statistics[2]);
        WriteToLeaderboard(player.getUsername(), statistics[0], statistics[2]);
        new LeaderboardGUI();
        LeaderboardGUI.createLeaderboardWindow();
    }

    // Method to calculate statistics from player data
    private static int[] calculateStatistics(File csvFile) {
        List<Integer> scores = new ArrayList<>();
        List<Integer> seconds = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));

            String line;
            try {
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        int score = Integer.parseInt(parts[0]);
                        int second = Integer.parseInt(parts[1]);
                        scores.add(score);
                        seconds.add(second);
                    }
                }
            } catch (Throwable var9) {
                try {
                    br.close();
                } catch (Throwable var8) {
                    var9.addSuppressed(var8);
                }

                throw var9;
            }

            br.close();
        } catch (NumberFormatException | IOException var10) {
            var10.printStackTrace();
            System.exit(1);
        }

        if (scores.isEmpty()) {
            System.out.println("No scores available to calculate statistics.");
            return new int[]{0, 0, 0};
        } else {
            int meanScore = calculateMean(scores);
            int standardDeviationScore = calculateStandardDeviation(scores, meanScore);
            int meanSeconds = calculateMean(seconds);
            return new int[]{meanScore, standardDeviationScore, meanSeconds};
        }
    }

    // Method to calculate the mean of a list of integers
    private static int calculateMean(List<Integer> data) {
        int sum = 0;

        for (int value : data) {
            sum += value;
        }

        return sum / data.size();
    }

    // Method to calculate the standard deviation of a list of integers
    private static int calculateStandardDeviation(List<Integer> data, int mean) {
        double squaredDifferencesSum = 0.0;

        for (int value : data) {
            squaredDifferencesSum += Math.pow((double) (value - mean), 2.0);
        }

        double variance = squaredDifferencesSum / (double) data.size();
        return (int) Math.sqrt(variance);
    }
}
