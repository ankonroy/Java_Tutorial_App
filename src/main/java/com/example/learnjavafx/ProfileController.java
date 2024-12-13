package com.example.learnjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileController {

    @FXML
    private Button backButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private TextField oldPasswordField;

    @FXML
    private TextField newPasswordField;

    @FXML
    private Button changePasswordButton;

    private String loggedInUsername; // Set this from the login session
    private Connection connection;

    public ProfileController() {
        // Initialize database connection
        connection = DatabaseConnection.getConnection(); // Ensure this method exists
    }

    @FXML
    private void initialize() {
        System.out.println("Initialize called");
        loadUserData();
    }

    private void loadUserData() {
//        System.out.println("nameLabel is null: " + (nameLabel == null));
        try {
            String query = "SELECT full_name, email, username FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, CurrentUser.getCurrUsername());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                nameLabel.setText("Name: " + resultSet.getString("full_name"));
                emailLabel.setText("Email: " + resultSet.getString("email"));
                usernameLabel.setText("Username: " + resultSet.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onBackButtonClick(ActionEvent event) {
        try {
            // Navigate back to the previous page (e.g., My Courses)
            Stage stage = (Stage) backButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/learnjavafx/mycourses-view.fxml"));
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("My Courses");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onChangePasswordClick(ActionEvent event) {
        String oldPassword = oldPasswordField.getText().trim();
        String newPassword = newPasswordField.getText().trim();

        if (oldPassword.isEmpty() || newPassword.isEmpty()) {
            System.out.println("Both fields must be filled.");
            return;
        }

        try {
            // Validate old password
            String validationQuery = "SELECT password FROM users WHERE username = ?";
            PreparedStatement validateStatement = connection.prepareStatement(validationQuery);
            validateStatement.setString(1, CurrentUser.getCurrUsername());
            ResultSet resultSet = validateStatement.executeQuery();

            if (resultSet.next()) {
                String currentPassword = resultSet.getString("password");

                if (!currentPassword.equals(oldPassword)) {
                    System.out.println("Old password is incorrect.");
                    return;
                }

                // Update to new password
                String updateQuery = "UPDATE users SET password = ? WHERE username = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setString(1, newPassword);
                updateStatement.setString(2, CurrentUser.getCurrUsername());
                int rowsUpdated = updateStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Password updated successfully.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteAccount(ActionEvent event) {
        // Get the current user (modify based on your application logic)
        String currentUser = CurrentUser.getCurrUsername();

        // Confirmation dialog
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Delete Account");
        confirmation.setHeaderText("Are you sure you want to delete your account?");
        confirmation.setContentText("This action is irreversible!");

        if (confirmation.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            boolean success = UserService.deleteAccount(currentUser);

            if (success) {
                // Inform the user
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Account Deleted");
                alert.setHeaderText(null);
                alert.setContentText("Your account has been successfully deleted.");
                alert.showAndWait();

                // Navigate to the login screen
//                navigateToLogin(event);
                try {
                    // Load the login page
                    Parent root = FXMLLoader.load(getClass().getResource("/com/example/learnjavafx/login-view.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root, 800, 600);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // Error message
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("An error occurred while deleting your account. Please try again.");
                alert.showAndWait();
            }
        }
    }
}
