package com.example.chatviewer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.text.TextFlow;

public class ChatViewerController {
    @FXML
    private Label fileLabel;

    @FXML
    private TextFlow chatTextFlow;

    @FXML
    private Button openMsgFileButton;


    @FXML
    protected void onHelloButtonClick() {
        fileLabel.setText("Open File Logic.");
    }

    public void handleOpenFile() {
        fileLabel.setText("Open File Logic.");
    }
}