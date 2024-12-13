package com.example.learnjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class CourseController {

    @FXML
    private Label courseNameLabel;

    @FXML
    private Label courseDescriptionLabel;

    public void setCourseDetails(String name, String description) {
        courseNameLabel.setText(name);
        courseDescriptionLabel.setText(description);
    }

//    @FXML
//    public void onViewDetailsButtonClick() {
//        System.out.println("View Details clicked for " + courseNameLabel.getText());
//
//        // Implement navigation or other functionality
//    }

    @FXML
    private void onViewDetailsButtonClick(ActionEvent event) {
        try {
            // Load and navigate to the course details page
            CurrentCourse.setCurrCourse(courseNameLabel.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/learnjavafx/details.fxml"));
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(loader.load(), 800, 600));
            stage.setTitle("Course Details");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
