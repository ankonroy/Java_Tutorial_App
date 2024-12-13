package com.example.learnjavafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class TopicController {

    @FXML
    private Label topicName;

//    @FXML
//    private Label topicName;

//    private DetailsController parentController;

    public void setTopicName(String name) {
        topicName.setText(name);
    }

//    public void setParentController(DetailsController parentController) {
//        this.parentController = parentController;
//    }

    @FXML
    private void onTopicNameClick() {
        String content = Utill.giveTopicDetails(CurrentCourse.getCurrCourse(), topicName.getText());
        CurrentTopic.setCurrTopic(topicName.getText());
//        DetailsController.updateArticle(content, topicName.getText());
//        parentController.updateArticle(art);
    }

}
