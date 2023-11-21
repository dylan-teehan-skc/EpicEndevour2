package com.example.EPICFX;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class QuestionBank {
    // Declare arrays to store questions of different difficulty levels
    private Question[] beginnerQuestions;
    private Question[] intermediateQuestions;
    private Question[] hardQuestions;



    public QuestionBank() {
        beginnerQuestions = readQuestionsFromFile("beginner_questions.txt");
        intermediateQuestions = readQuestionsFromFile("intermediate_questions.txt");
        hardQuestions = readQuestionsFromFile("hard_questions.txt");
    }

    // Read questions from a file and return an array of Question objects
    private Question[] readQuestionsFromFile(String fileName) {
        // Create an ArrayList to temporarily store the read questions
        ArrayList<Question> questionsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Read each line from the file
            while ((line = br.readLine()) != null) {
                // Split the line using the pipe character to get different parts
                String[] parts = line.split("\\|");

                // Check if the line has at least 6 parts (0 to 5)
                if (parts.length >= 6) {
                    // Extract question text, answers, and correct answer index from parts
                    String questionText = parts[0].trim();
                    String[] answers = new String[4];
                    for (int i = 0; i < 4; i++) {
                        answers[i] = parts[i + 1].trim();
                    }
                    int correctAnswerIndex = Integer.parseInt(parts[5].trim());

                    // Create a Question object and add it to the ArrayList
                    Question question = new Question(questionText, answers, correctAnswerIndex);
                    questionsList.add(question);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the ArrayList to an array and return
        return questionsList.toArray(new Question[0]);
    }

    // Getter methods for accessing questions
    public Question[] getBeginnerQuestions() {
        return beginnerQuestions;
    }
    public Question[] getIntermediateQuestions() {
        return intermediateQuestions;
    }
    public Question[] getHardQuestions() {
        return hardQuestions;
    }

    // Method to print questions
    public static void printQuestions(Question[] questions) {
        for (int i = 0; i < questions.length; i++) {
            // Print question text
            System.out.println("Question " + (i + 1) + ": " + questions[i].questionText);

            // Print answer choices
            for (int j = 0; j < questions[i].answers.length; j++) {
                System.out.println((j + 1) + ". " + questions[i].answers[j]);
            }

            // Print a new line for better readability
            System.out.println();
        }
    }

}
