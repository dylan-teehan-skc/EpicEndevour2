package com.example.EPICFX;

public class Question {
    // Fields to store question details
    String questionText;
    String[] answers;
    int correctAnswerIndex;

    // Constructor to initialize the fields
    public Question(String questionText, String[] answers, int correctAnswerIndex) {
        this.questionText = questionText;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
    }
    // Getter methods for accessing the fields
    public String getQuestionText() {
        return questionText;
    }
    public String[] getAnswers() {
        return answers;
    }
    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }
}
