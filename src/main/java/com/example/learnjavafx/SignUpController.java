package com.example.learnjavafx;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class SignUpController {

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button signUpButton;

    @FXML
    private Hyperlink backToLoginLink;

    // Handle Sign-Up button click
    @FXML
    private void onSignUpButtonClick() {
        String fullName = fullNameField.getText().trim();
        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        // Validate inputs
        if (fullName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "All fields are required.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match.");
            return;
        }

        // Hash password before storing
//        String hashedPassword = PasswordUtils.hashPassword(password);

        // Save to database
//        boolean isSignUpSuccessful = UserService.signUpUser(username, email, hashedPassword);
        boolean isSignUpSuccessful = UserService.signUpUser(fullName, username, email, password);

        if (isSignUpSuccessful) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "Account created successfully!");
            clearFields();
            onBackToLoginLinkClick();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Sign up failed. Try a different username or email.");
        }
    }

    // Handle "Back to Login" link click
    @FXML
    private void onBackToLoginLinkClick() {
        try {
            // Load the login FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/learnjavafx/login-view.fxml")); // Adjust the path if needed
            Parent root = loader.load();

            // Set the login scene
            Scene loginScene = new Scene(root, 800, 600);

            // Get the current stage and set the new scene
            Stage currentStage = (Stage) backToLoginLink.getScene().getWindow();
            currentStage.setScene(loginScene);
            currentStage.setTitle("Login"); // Optional: Set the window title
        } catch (IOException e) {
            e.printStackTrace(); // Handle potential errors
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        fullNameField.clear();
        usernameField.clear();
        emailField.clear();
        passwordField.clear();
        confirmPasswordField.clear();
    }
}
