package com.example.EPICFX;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ChooseGamemode extends Application {

    @FXML
    private Button beginnerButton;
    @FXML
    private Button intermediateButton;
    @FXML
    private Button hardButton;

    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file for the ChooseGameMode scene
        FXMLLoader fxmlLoader = new FXMLLoader(ChooseGamemode.class.getResource("ChooseGameMode.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 408);

        // Set the scene and show the stage
        stage.setScene(scene);
        stage.show();

        // Set initial position of the stage
        stage.setY(190);
        stage.setX(470);
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch();
    }

    public void runBeginner() {
        // Open the question scene with beginner difficulty (1)
        openQuestionScene(1);

        // Close the current stage (ChooseGamemode)
        Stage currentStage = (Stage) beginnerButton.getScene().getWindow();
        currentStage.close();
    }

    public void runIntermediate() {
        // Open the question scene with intermediate difficulty (2)
        openQuestionScene(2);

        // Close the current stage (ChooseGamemode)
        Stage currentStage = (Stage) intermediateButton.getScene().getWindow();
        currentStage.close();
    }

    public void runHard() {
        // Open the question scene with hard difficulty (3)
        openQuestionScene(3);

        // Close the current stage (ChooseGamemode)
        Stage currentStage = (Stage) hardButton.getScene().getWindow();
        currentStage.close();
    }

    public void openQuestionScene(int quizMode) {
        try {
            // Load the FXML file for the Questions scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Questions.fxml"));
            Parent root = loader.load();

            // Access the controller to set the quiz mode
            QuestionsMain controller = loader.getController();
            controller.setQuizMode(quizMode);

            // Create a new stage for the Questions scene
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 865, 633));

            // Set the position of the new stage
            stage.setY(100);
            stage.setX(320);

            // Show the new stage
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
