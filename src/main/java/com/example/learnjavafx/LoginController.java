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

public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink signUpLink;

    // Handle login button click
    @FXML
    private void onLoginButtonClick() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        // Validate inputs
        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter both username and password.");
            System.out.println("Login Clicked");
            return;
        }

        // Authenticate user
        boolean isAuthenticated = UserService.authenticateUser(username, password);

        if (isAuthenticated) {
//            showAlert(Alert.AlertType.INFORMATION, "Success", "Login successful!");
            // Saving Username
            CurrentUser.setCurrUsername(username);
            // Navigate to the dashboard or home page
            navigateToHomePage();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Invalid username or password.");
        }
    }

    // Handle "Sign Up" link click
    @FXML
    private void onSignUpLinkClick() {
        try {
            // Load the Sign-Up FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/learnjavafx/signup-view.fxml"));
            Parent root = loader.load();

            // Create a new scene with the Sign-Up view
            Scene signUpScene = new Scene(root, 800, 600);

            // Get the current stage
            Stage currentStage = (Stage) signUpLink.getScene().getWindow();

            // Set the new scene on the current stage
            currentStage.setScene(signUpScene);
            currentStage.setTitle("Sign Up");

            // Optional: Adjust window size if necessary
            currentStage.setResizable(false);
            currentStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception if the Sign-Up FXML file cannot be loaded
            showErrorDialog("Error", "Unable to load the Sign-Up page.");
        }
    }

    // Helper method to show error dialogs
    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void navigateToHomePage() {
        try {
            // Load the HomePage.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/learnjavafx/mycourses-view.fxml"));
            Parent root = loader.load();

            // Get the current stage
            Stage stage = (Stage) loginButton.getScene().getWindow();

            // Set the new scene for the stage
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.setTitle("Home Page");
            stage.show();

            System.out.println("Navigated to the home page.");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading the home page.");
        }
    }

}
