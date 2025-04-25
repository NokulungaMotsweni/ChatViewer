package com.example.chatviewer;


/**
 * @param timestamp Timestamp of the message (as a raw string from the file)
 * @param nickname  Nickname of the user who sent the message
 * @param content   Actual context/text of the message
 */
public record ChatMessage(String timestamp, String nickname, String content) {

    public ChatMessage {
    }

    /**
     * Gets the message timestamp.
     * Returns: Timestamp of the message.
     */
    @Override
    public String timestamp() {
        return timestamp;
    }

    /**
     * Gets the sender's nickname.
     * Returns: Sender's nickname.
     */
    @Override
    public String nickname() {
        return nickname;
    }

    /**
     * Gets the content of the message.
     * Returns: Message content.
     */
    @Override
    public String content() {
        return content;
    }
}
