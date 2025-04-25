package com.example.chatviewer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;


public class ChatParserTest {

    @Test
    void testParseValidFile() throws Exceptions.InvalidMessageFormatException {
        List<String> lines = List.of(
                "Time:12:34:56",
                "Name:Adam",
                "Message:Hello Bob :) :)",
                "",
                "Time:12:34:59",
                "Name:Bob",
                "Message:Hi Adam :(",
                "",
                "Time:12:35:01",
                "Name:Bob",
                "Message::( I have to work on my assignment :( on PIJ"
        );

        // Parser method called with the log lines above
        List<ChatMessage> messages = ChatParser.parse(lines);

        // Check that the three (3) messages are successfully parsed.
        assertEquals(3, messages.size());

        // Verify the fields of the first message
        assertEquals("12:34:56", messages.get(0).getTimestamp()); // Check timestamp
        assertEquals("Adam", messages.get(0).getNickname()); // Check the sender's name
        assertEquals("Hello Bob", messages.get(0).getContent()); // Check the message content

        // Verify the fields of the second message
        assertEquals("12:34:59", messages.get(1).getTimestamp());
        assertEquals("Bob", messages.get(1).getNickname());
        assertEquals("Hi Adam", messages.get(1).getContent());

        // Verify the fields of the third message
        assertEquals("12:35:01", messages.get(2).getTimestamp());
        assertEquals("Bob", messages.get(2).getNickname());
        assertEquals(":( I have to work on my assignment :( on PIJ", messages.get(2).getContent());
    }
}
