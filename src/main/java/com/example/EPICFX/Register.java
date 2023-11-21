package com.example.EPICFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Main class for handling user registration
public class Register extends Application {

    // Method to initialize and show the registration scene
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Register.class.getResource("RegisterScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 650, 450);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        stage.setY(175);
        stage.setX(465);
    }

    // Main method to launch the JavaFX application for registration
    public static void main(String[] args) {
        launch(args);
    }
}
