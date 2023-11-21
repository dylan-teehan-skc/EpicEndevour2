package com.example.EPICFX;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.EPICFX.PlayerDataStatistics.PlayerData;
import static com.example.EPICFX.StatisticsGUI.player;

// Main class for handling the quiz questions and answers
public class QuestionsMain extends Application {

    @FXML
    private Label QuestionText;
    @FXML
    private Button Answer1;
    @FXML
    private Button Answer2;
    @FXML
    private Button Answer3;
    @FXML
    private Button Answer4;
    @FXML
    private Label PageMarker;
    private int userIndex = -1;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;
    private long startTime;
    private Question[] questions;
    private int quizMode;
    private int score;

    // Action method for handling the first answer button
    @FXML
    private void handleAnswer1Action(ActionEvent event) {
        setUserIndex(0);
        checkAnswer();
    }

    // Action method for handling the second answer button
    @FXML
    private void handleAnswer2Action(ActionEvent event) {
        setUserIndex(1);
        checkAnswer();
    }

    // Action method for handling the third answer button
    @FXML
    private void handleAnswer3Action(ActionEvent event) {
        setUserIndex(2);
        checkAnswer();
    }

    // Action method for handling the fourth answer button
    @FXML
    private void handleAnswer4Action(ActionEvent event) {
        setUserIndex(3);
        checkAnswer();
    }

    // Method to set the user's answer choice
    private void setUserIndex(int index) {
        userIndex = index;
    }

    // Method to initialize the quiz scene
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuestionsMain.class.getResource("Questions.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 865, 633);
        stage.setTitle("Quiz Application");
        stage.setScene(scene);
        stage.show();
    }

    // Method to start playing the quiz
    @FXML
    protected void playGame() {
        // Load questions based on the selected quiz mode
        loadQuestions(quizMode);

        startTime = System.currentTimeMillis();
        // Start asking questions
        handleNextQuestion();
    }

    // Method to set the quiz mode
    public void setQuizMode(int quizMode) {
        this.quizMode = quizMode;
    }

    // Method to load questions based on the quiz mode
    private void loadQuestions(int quizMode) {
        QuestionBank questionBank = new QuestionBank();
        switch (quizMode) {
            case 1:
                questions = questionBank.getBeginnerQuestions();
                break;
            case 2:
                questions = questionBank.getIntermediateQuestions();
                break;
            case 3:
                questions = questionBank.getHardQuestions();
                break;
            default:
                System.out.println("Invalid choice. Exiting.");
                System.exit(1);
        }

        if (questions == null || questions.length == 0) {
            System.out.println("No questions available. Exiting.");
            System.exit(1);
        }
    }

    // Method to handle the next question in the quiz
    private void handleNextQuestion() {
        if (currentQuestionIndex < questions.length) {
            Question currentQuestion = questions[currentQuestionIndex];
            System.out.println(currentQuestion.getQuestionText());
            QuestionText.setText(currentQuestion.getQuestionText());
            Answer1.setText(String.valueOf(currentQuestion.getAnswers()[0]));
            Answer2.setText(String.valueOf(currentQuestion.getAnswers()[1]));
            Answer3.setText(String.valueOf(currentQuestion.getAnswers()[2]));
            Answer4.setText(String.valueOf(currentQuestion.getAnswers()[3]));
            PageMarker.setText("Question " + (currentQuestionIndex + 1));

            // Move to the next question after updating the UI
        } else {
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;
            long seconds = elapsedTime / 1000L;
            long minutes = seconds / 60L;
            seconds %= 60L;
            player.setTime(seconds);
            System.out.println("The quiz is over, you answered " + correctAnswers + " questions correctly out of 12");
            double percent = (double) correctAnswers / 12.0 * 100.0;
            int roundedPercent = (int) Math.round(percent);
            System.out.println("You got " + roundedPercent + "%");
            System.out.println("Quiz completed in " + minutes + " minutes and " + seconds + " seconds.");
            System.out.println("Your score was " + score);
            player.setScore(score);
            Stage currentStage = (Stage) Answer1.getScene().getWindow();
            currentStage.close();
            PlayerDataWriter playerDataWriter = new PlayerDataWriter(player.getUsername());
            playerDataWriter.writeScore(player.getUsername(), player.getScore(), player.getTime());
            PlayerData(player.getUsername(), player.getScore(), player.getTime());
        }
    }

    // Method to check the user's answer and update the quiz
    private void checkAnswer() {
        if (userIndex != -1) {
            // Update the user's choice immediately
            int userChoice = userIndex;

            if (userChoice == questions[currentQuestionIndex].getCorrectAnswerIndex()) {
                System.out.println("Correct!");
                correctAnswers++;
                switch (quizMode) {
                    case 1:
                        score += 1;
                        break;
                    case 2:
                        score += 2;
                        break;
                    case 3:
                        score += 3;
                        break;
                }

            } else {
                System.out.println("Incorrect. The correct answer was: " + questions[currentQuestionIndex].getAnswers()[questions[currentQuestionIndex].getCorrectAnswerIndex()]);
            }

            // Move to the next question after checking the answer
            currentQuestionIndex++;
            userIndex = -1; // Reset userIndex for the next question

            // Call handleNextQuestion to show the next question
            handleNextQuestion();
        }
    }

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}
