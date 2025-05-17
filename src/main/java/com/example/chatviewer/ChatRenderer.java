package com.example.chatviewer;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;
import java.util.List;

import com.example.chatviewer.Exceptions.InvalidImageException;

public class ChatRenderer {
    private static final Image SMILEY_FACE;
    private static final Image SAD_FACE;

    static {
        Image smileyFace = null;
        Image sadFace = null;

        try {
            smileyFace = loadImage("/images/smile_happy.gif");
        } catch (InvalidImageException e) {
            System.err.println("Smiley face image not found: " + e.getMessage());
        }

        try {
            sadFace = loadImage("/images/smile_sad.gif");
        } catch (InvalidImageException e) {
            System.err.println("Sad face image not found: " + e.getMessage());
        }

        SMILEY_FACE = smileyFace;
        SAD_FACE = sadFace;
    }

    private static Image loadImage(String path) throws InvalidImageException {
        var url = ChatRenderer.class.getResource(path);
        if (url == null) {
            throw new InvalidImageException("Image not found at: " + path);
        }
        return new Image(url.toString());
    }

    public static void render(TextFlow chatTextFlow, List<ChatMessage> messages) {
        chatTextFlow.getChildren().clear();
        String lastNickname = null;

        for (ChatMessage msg : messages) {
            // [timestamp]
            Text timestamp = new Text("[" + msg.timestamp() + "] ");
            timestamp.setStyle("-fx-fill: gray;");

            // nickname:
            String nicknameStr = msg.nickname().equals(lastNickname) ? "..." : msg.nickname() + ": ";
            Text nickname = new Text(nicknameStr);
            nickname.setStyle("-fx-fill: blue;");

            // parsed content (with emoji)
            List<javafx.scene.Node> contentNodes = parseContent(msg.content());

            chatTextFlow.getChildren().addAll(timestamp, nickname);
            chatTextFlow.getChildren().addAll(contentNodes);
            chatTextFlow.getChildren().add(new Text(System.lineSeparator()));

            lastNickname = msg.nickname();
        }
    }

    private static List<Node> parseContent(String content) {
        List<Node> contentNodes = new ArrayList<>();
        int i = 0;

        while (i < content.length()) {
            if (content.startsWith(":)", i) && SMILEY_FACE != null) {
                ImageView image = new ImageView(SMILEY_FACE);
                image.setFitWidth(16);
                image.setFitHeight(16);
                contentNodes.add(image);
                i += 2;
            } else if (content.startsWith(":(", i) && SAD_FACE != null) {
                ImageView image = new ImageView(SAD_FACE);
                image.setFitWidth(16);
                image.setFitHeight(16);
                contentNodes.add(image);
                i += 2;
            } else {
                int next = nextEmojiIndex(content, i);
                if (next > i) {
                    Text text = new Text(content.substring(i, next));
                    text.setStyle("-fx-font-weight: bold; -fx-fill: black;");
                    contentNodes.add(text);
                }
                i = next;
            }
        }

        return contentNodes;
    }

    private static int nextEmojiIndex(String content, int from) {
        int happy = content.indexOf(":)", from);
        int sad = content.indexOf(":(", from);

        if (happy == -1 && sad == -1) return content.length();
        if (happy == -1) return sad;
        if (sad == -1) return happy;
        return Math.min(happy, sad);
    }

}
