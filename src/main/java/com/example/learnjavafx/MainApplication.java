package com.example.learnjavafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class MainApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the Login page (Initial screen)
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/learnjavafx/login-view.fxml"));
            Parent root = loader.load();  // Load FXML for login screen

            // Set the scene and stage
            Scene scene = new Scene(root, 800, 600);  // Define the size of the window
            primaryStage.setScene(scene);
            primaryStage.setTitle("Tutorial App");  // Set window title
            primaryStage.show();

            Utill.fetchJson();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        System.out.println("Hello World");
        launch(args);  // Start the application
    }
}
