package com.example.EPICFX;

import javafx.fxml.FXML;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// Class for handling user registration
public class RegisterUser {
    @FXML
    private PasswordField newPassword;
    @FXML
    private Button signUpButton;
    @FXML
    private TextField newUsernameEmail;
    @FXML
    private PasswordField newConfirmPassword;
    @FXML
    private Text Success;

    // Method to hash the provided password using SHA-256 algorithm
    protected static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Handle the exception appropriately in your application
            return null;
        }
    }

    // Method to validate user input and initiate user registration
    public void ValidateUser() {
        // Retrieve the new username and validate it as an email address.
        String newUsername = newUsernameEmail.getText().trim();
        String newEmailRegex = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
        Pattern pattern = Pattern.compile(newEmailRegex);
        Matcher matcher = pattern.matcher(newUsername);

        if (!matcher.matches()) {
            // If the email is invalid, show an error message.
            System.out.println("Invalid Email Address, Please Retry.");
            Success.setText("Invalid Email Address, Please Retry.");
        } else if (newPassword != null && newPassword.getText().trim().equals(newConfirmPassword.getText().trim())) {
            // Retrieve and register the new username and password.
            char[] newPasswordChars = newPassword.getText().toCharArray();
            String newPassword = new String(newPasswordChars);
            registerUser(newUsername, newPassword);
        } else {
            System.out.println("Passwords do not match");
            Success.setText("Passwords do not match");
        }
    }

    // Method to write user data to a CSV file
    private void registerUser(String username, String password) {
        try (FileWriter writer = new FileWriter("user_data.csv", true);
             BufferedWriter bw = new BufferedWriter(writer);
             PrintWriter out = new PrintWriter(bw)) {
            // Append the new user's information to the CSV file.
            String hashedPassword = hashPassword(password);
            out.println(username + "," + hashedPassword);
            switchToLogin();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Method to switch to the login scene after successful registration
    public void switchToLogin() {
        try {
            showSuccessMessage("User Registered Successfully. Please Login.");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScene.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 770, 530));
            stage.show();
            stage.setTitle("Login");
            stage.setY(120);
            stage.setX(400);
            // Close the current window if needed
            Stage currentStage = (Stage) signUpButton.getScene().getWindow();
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to display a success message
    private void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
