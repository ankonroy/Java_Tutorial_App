package com.example.learnjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Set;

public class DetailsController {

    private Stage stage;
    private Scene scene;

    @FXML
    private ListView topicsList;

    @FXML
    private TextArea article;

    @FXML
    private Label courseName;

    @FXML
    private Label topicName;


    @FXML
    private void initialize() {
        courseName.setText(CurrentCourse.getCurrCourse());
        loadTopics();
    }

    private void loadTopics() {
        try {
            String course = CurrentCourse.getCurrCourse();
            Set<String> names = Utill.giveTopicNames(course);
            System.out.println(names);
            for (String name : names) { // Example: Load 10 course components
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/learnjavafx/topic.fxml"));
                Pane coursePane = loader.load();

                // Optionally customize the course
                TopicController topicController = loader.getController();
                topicController.setTopicName(name);

                // Add the coursePane to the TilePane
                topicsList.getItems().add(coursePane);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onHomeButtonClick(ActionEvent event) {
        navigateToView(event, "mycourses-view.fxml");
    }

    @FXML
    private void onProfileButtonClick(ActionEvent event) {
        navigateToView(event, "profile.fxml");
    }

    private void navigateToView(ActionEvent event, String fxmlFile) {
        try {
            // Load the desired view
            Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @FXML
//    public void updateArticle(String content, String topic) {
//        article.setText(CurrentTopic.getCurrContent());
//        topicName.setText(CurrentTopic.getCurrTopic());
//    }
    @FXML
    public void updateArticle() {
        article.setText(CurrentTopic.getCurrContent());
        topicName.setText(CurrentTopic.getCurrTopic());
    }
}
