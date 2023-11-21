package com.example.EPICFX;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static com.example.EPICFX.StatisticsGUI.player;

// Class to write player data to a CSV file
public class PlayerDataWriter {
    private File csvFile;

    // Constructor to create or access the CSV file based on the player's username
    public PlayerDataWriter(String username) {
        this.csvFile = new File(player.getUsername() + ".csv");
        if (!this.csvFile.exists()) {
            try {
                this.csvFile.createNewFile();
                System.out.println("New CSV file created: " + this.csvFile.getName());
            } catch (IOException var3) {
                var3.printStackTrace();
                System.exit(1);
            }
        }
    }

    // Method to write score and time data to the CSV file
    public void writeScore(String username, int score, long seconds) {
        try {
            FileWriter writer = new FileWriter(username + ".csv", true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter out = new PrintWriter(bw);
            out.println("" + score + "," + seconds);
            out.close();
            bw.close();
            writer.close();
        } catch (IOException var8) {
            var8.printStackTrace();
        }
    }

    // Method to get the CSV file
    public File getCSVFile() {
        return this.csvFile;
    }
}
