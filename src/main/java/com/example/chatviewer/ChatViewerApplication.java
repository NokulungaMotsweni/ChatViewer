package com.example.chatviewer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Entry point for the JavaFX Chat Viewer application.
 *
 * The ChatViewerApplication class loads the JavaFX runtime and loads the UI. This is defined in the FXML file: {@code ChatViewerView.fxml}
 */
public class ChatViewerApplication extends Application {

    /**
     * Automatically called by the JavaFX runtime when the application is launched.
     * The FXML file is then loaded and the scene is set.
     * @param stage, provided by JavaFX
     * @throws IOException, if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Load FXML layout for main window
        FXMLLoader fxmlLoader = new FXMLLoader(ChatViewerApplication.class.getResource("ChatViewerView.fxml"));

        // Scene with the default dimensions is created with the loaded layout
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        // Window Title
        stage.setTitle("Hello!");

        // Set scene and show stage
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}