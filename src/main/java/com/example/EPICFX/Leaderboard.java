package com.example.EPICFX;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.example.EPICFX.StatisticsGUI.player;

// Class representing the leaderboard functionality
public class Leaderboard {
    private File csvFile;

    // Method to write player data to the leaderboard
    public static void WriteToLeaderboard(String user, int MeanScore, int MeanTime) {
        File csvFile = new File("Leaderboard.csv");

        try {
            // Read existing data from the CSV file.
            List<LeaderboardEntry> entries = readLeaderboardData(csvFile);

            // Check if the user already exists in the leaderboard.
            LeaderboardEntry existingEntry = findEntryByUsername(entries, user);

            if (existingEntry != null) {
                // If the user exists, update the entry with the highest score and time.
                if (MeanScore > existingEntry.getMeanScore() || (MeanScore == existingEntry.getMeanScore() && MeanTime < existingEntry.getMeanTime())) {
                    player.setAverageScore(MeanScore);
                    player.setAverageTime(MeanTime);
                }
            } else {
                // If the user doesn't exist, add a new entry.
                entries.add(new LeaderboardEntry(user, MeanScore, MeanTime));
            }

            // Sort the list based on your criteria (highest score and lowest time).
            Collections.sort(entries, new LeaderboardComparator());

            // Write the sorted data back to the CSV file.
            writeLeaderboardData(csvFile, entries);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Method to find a leaderboard entry by username
    private static LeaderboardEntry findEntryByUsername(List<LeaderboardEntry> entries, String username) {
        for (LeaderboardEntry entry : entries) {
            if (entry.getUser().equals(username)) {
                return entry;
            }
        }
        return null;
    }

    // Method to read leaderboard data from a CSV file
    private static List<LeaderboardEntry> readLeaderboardData(File csvFile) throws IOException {
        List<LeaderboardEntry> entries = new ArrayList<>();
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
        return entries;
    }

    // Method to write leaderboard data to a CSV file
    private static void writeLeaderboardData(File csvFile, List<LeaderboardEntry> entries) throws IOException {
        FileWriter writer = new FileWriter(csvFile, false);
        PrintWriter out = new PrintWriter(new BufferedWriter(writer));
        for (LeaderboardEntry entry : entries) {
            out.println(entry.getUser() + "," + entry.getMeanScore() + "," + entry.getMeanTime());
        }
        out.close();
    }

    // Getter method for the CSV file
    public File getCSVFile() {
        return csvFile;
    }

}

// Class representing a single entry in the leaderboard
class LeaderboardEntry {
    private String user;
    private int meanScore;
    private int meanTime;

    // Constructor for the LeaderboardEntry class
    public LeaderboardEntry(String user, int meanScore, int meanTime) {
        this.user = user;
        this.meanScore = meanScore;
        this.meanTime = meanTime;
    }

    // Getter method for the username
    public String getUser() {
        return user;
    }

    // Getter method for the mean score
    public int getMeanScore() {
        return meanScore;
    }

    // Getter method for the mean time
    public int getMeanTime() {
        return meanTime;
    }
}

// Comparator class for sorting leaderboard entries
class LeaderboardComparator implements Comparator<LeaderboardEntry> {
    @Override
    public int compare(LeaderboardEntry entry1, LeaderboardEntry entry2) {
        // Sort by highest score, and in case of a tie, by lowest time.
        if (entry1.getMeanScore() == entry2.getMeanScore()) {
            return entry1.getMeanTime() - entry2.getMeanTime();
        }
        return entry2.getMeanScore() - entry1.getMeanScore();
    }
}
