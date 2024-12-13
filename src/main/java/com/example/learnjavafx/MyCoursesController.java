package com.example.learnjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class MyCoursesController {

    @FXML
    private Button logOutButton;

    @FXML
    private Button profileButton;

    @FXML
    private VBox courseBox1; // Example for course 1 VBox

    @FXML
    private TilePane coursesContainer;

    @FXML
    private void initialize() {
        loadCourses();
    }

    private void loadCourses() {
        try {
            Set<String> names = Utill.giveNames();
            for (String name : names) { // Load course components
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/learnjavafx/course.fxml"));
                Pane coursePane = loader.load();

                // Optionally customize the course
                CourseController courseController = loader.getController();
                courseController.setCourseDetails(name, "Learn " + name + " in no time");

                // Add the coursePane to the TilePane
                coursesContainer.getChildren().add(coursePane);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onLogOutButtonClick(ActionEvent event) {
        try {
            // Navigate back to login page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/learnjavafx/login-view.fxml"));
            Stage stage = (Stage) logOutButton.getScene().getWindow();
            stage.setScene(new Scene(loader.load(), 800, 600));
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onProfileButtonClick(ActionEvent event) {
        try {
            // Navigate to profile page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/learnjavafx/profile.fxml"));
            Stage stage = (Stage) profileButton.getScene().getWindow();
            stage.setScene(new Scene(loader.load(), 800, 600));
            stage.setTitle("Profile");
//            ProfileController.initialize();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    private void onViewDetailsButtonClick(ActionEvent event) {
//        try {
//            // Load and navigate to the course details page
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/learnjavafx/details.fxml"));
//            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//            stage.setScene(new Scene(loader.load(), 800, 600));
//            stage.setTitle("Course Details");
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
