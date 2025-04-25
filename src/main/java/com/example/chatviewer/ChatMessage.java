package com.example.chatviewer;

public class ChatMessage {
    // Timestamp of the message (as a raw string from the file)
    private final String timestamp;

    // Nickname of the user who sent the message
    private final String nickname;

    // Actual context/text of the message
    private final String content;


    /**
     * Constructs new ChatMessage object via the parsed message data.
     *
     * @param timestamp: Time when message was sent.
     * @param nickname: Name of sender.
     * @param content: Actual text of the message.
     */
    public ChatMessage(String timestamp, String nickname, String content) {
        this.timestamp = timestamp;
        this.nickname = nickname;
        this.content = content;
    }

    /**
     * Gets the message timestamp.
     * Returns: Timestamp of the message.
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Gets the sender's nickname.
     * Returns: Sender's nickname.
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Gets the content of the message.
     * Returns: Message content.
     */
    public String getContent() {
        return content;
    }
}
