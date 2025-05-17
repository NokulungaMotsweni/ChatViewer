package com.example.chatviewer;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.util.List;

/**
 * Controller class for the Chat Viewer Graphical User Interface (GUI)
 * Handles button clicks, updates labels and text displays.
 */
public class ChatViewerController {

    // Displays the file path and name
    @FXML
    private Label fileLabel;

    // Displays the conversation
    @FXML
    private TextFlow chatTextFlow;

    // Opens dialogue and allows the user to choose and open a file with the conversation
    @FXML
    private Button openMsgFileButton;

    /**
     * Method runs when the file open button is clicked.
     * Sets
     */
    @FXML
    protected void onOpenChatButtonClick() {
        System.out.println("Button clicked!");

        try {
            FileHandler fileHandler = new FileHandler(openMsgFileButton.getScene().getWindow());
            List<ChatMessage> messages = fileHandler.openAndParseFile();

            // Show success feedback
            fileLabel.setText("Chat loaded successfully.");

            // Display messages in the chat window
            ChatRenderer.render(chatTextFlow, messages);

        } catch (IOException e) {
            showErrorDialog("File Error", "Could not read the file.");
        } catch (Exceptions.InvalidMessageFormatException e) {
            showErrorDialog("Format Error", e.getMessage());
        }
    }

    public void handleOpenFile() {
        fileLabel.setText("Open File Logic."); // TODO: Still gotta figure this out
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null); // optional: you could set a header
        alert.setContentText(message);
        alert.showAndWait();
    }
}