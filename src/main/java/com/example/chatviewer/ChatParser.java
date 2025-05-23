package com.example.chatviewer;

import java.util.*;

/**
 * Turns raw files into ChatMessage objects.
 * Reads lines from the .msg file and uses regex to slice them into little message pieces.
 * (time, name, message)
 */
public class ChatParser {

    /**
     * Parses a list of lines into a list of ChatMessage Objects.
     * Structure: Each message is made up of three (3) lines: Time, Name and Message.
     *
     * @param lines: List of strings from .msg file.
     * @return: A List of ChatMessage Objects
     * @throws: InvalidMessageFormat should the file not match the expected format or if the file ends unexpectedly.
     *
     */
    public static List<ChatMessage> parse(List<String> lines) throws Exceptions.InvalidMessageFormatException {
        List<ChatMessage> messages = new ArrayList<>();

        for(int i = 0; i < lines.size(); i+= 4) {
            if (i + 2 >= lines.size()) {
                throw new Exceptions.InvalidMessageFormatException(
                        String.format(
                                "Unexpected end of file: Expected three (3) lines for a message at line %d", i
                        )
                );
            }

            String timestampLine = lines.get(i).trim();
            String nicknameLine = lines.get(i + 1).trim();
            String contentLine = lines.get(i + 2).trim();

            // Validate the headings
            if (!timestampLine.trim().startsWith("Time:") ||
                    !nicknameLine.trim().startsWith("Name:") ||
                    !contentLine.trim().startsWith("Message:"))  {
                throw new Exceptions.InvalidMessageFormatException(
                        "Message is malformed near line " + (i + 1)
                                + ". Expected format:\nTime:\nName:\nMessage:\n(empty line)");
            }

            // Extract the values
            String timestamp = timestampLine.replaceFirst("^Time:", "").trim();
            String nickname = nicknameLine.replaceFirst("^Name:", "").trim();
            String content = contentLine.replaceFirst("^Message:", "").trim();

            if (timestamp.isEmpty() || nickname.isEmpty()) {
                throw new Exceptions.InvalidMessageFormatException("Message is malformed near line " + (i + 1));
            }

            messages.add(new ChatMessage(timestamp, nickname, content));

            // Cech if there is an empty line after the message, unless the message is the last message.
            if (i + 3 < lines.size()) {
                String emptyLine = lines.get(i + 3).trim();
                if (!emptyLine.isEmpty()) {
                    throw new Exceptions.InvalidMessageFormatException("Empty line expected after message at line number " + (i + 3));
                }
            }
        }
        return messages;
    }
}
