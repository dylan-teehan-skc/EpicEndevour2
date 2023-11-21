package com.example.EPICFX;

public class User {
    private String username;
    private long time;
    private int score;
    private int averageScore;
    private int averageTime;
    private int sdScore;

    // Constructor to set user details
    public void userDetails(String username, long time, int score, int averageScore, int averageTime, int sdScore) {
        this.username = username;
        this.time = time;
        this.score = score;
        this.averageScore = averageScore;
        this.averageTime = averageTime;
        this.sdScore = sdScore;

    }

    // Getters and setters for user attributes
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTime() {
        return time;
    }

    public void setTime (long time){
        this.time = time;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(int averageScore) {
        this.averageScore = averageScore;
    }

    public int getAverageTime() {
        return averageTime;
    }

    public void setAverageTime(int averageTime) {
        this.averageTime = averageTime;
    }

    public int getSdScore() {
        return sdScore;
    }

    public void setSdScore(int sdScore) {
        this.sdScore = sdScore;
    }
}

