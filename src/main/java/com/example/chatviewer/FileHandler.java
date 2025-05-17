package com.example.chatviewer;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileHandler {

    private final Window parentWindow;

    public FileHandler(Window parentWindow) {
        this.parentWindow = parentWindow;
    }

    public List<ChatMessage> openAndParseFile() throws IOException, Exceptions.InvalidMessageFormatException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Chat File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Chat Files", "*.msg"));

        File selectedFile = fileChooser.showOpenDialog(parentWindow);
        if (selectedFile == null) {
            throw new IOException("No file selected.");
        }

        List<String> lines = Files.readAllLines(selectedFile.toPath());
        return ChatParser.parse(lines);
    }
}
