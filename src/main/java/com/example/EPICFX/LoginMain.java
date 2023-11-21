package com.example.EPICFX;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Main class for starting the login application
public class LoginMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginMain.class.getResource("LoginScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 770, 530);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
        stage.setY(120);
        stage.setX(400);
    }

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch();
    }
}
