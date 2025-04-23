package com.example.chatviewer;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.TextFlow;

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
        fileLabel.setText("Open File Logic.");
    }

    public void handleOpenFile() {
        fileLabel.setText("Open File Logic."); // TODO: Still gotta figure this out
    }
}