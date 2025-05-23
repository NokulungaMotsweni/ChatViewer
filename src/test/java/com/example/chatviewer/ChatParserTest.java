package com.example.chatviewer;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Class contains the unit tests for ChatParser class from a list of strings to ChatMessage objects.
 */
public class ChatParserTest {

    /**
     * Tests the "happy path" where a valid file with the correct format is parsed into a list of ChatMessage
     * objects with accurate data extraction.
     * @throws Exceptions.InvalidMessageFormatException
     */
    @Test
    void testParseValidFile() throws Exceptions.InvalidMessageFormatException {
//          Valid list of chat log lines (part of - Conversation-large.msg) stiulated
//          @author: Viktor Cerny

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
        assertEquals("12:34:56", messages.getFirst().timestamp()); // Check timestamp
        assertEquals("Adam", messages.get(0).nickname()); // Check the sender's name
        assertEquals("Hello Bob", messages.get(0).content()); // Check the message content

        // Verify the fields of the second message
        assertEquals("12:34:59", messages.get(1).timestamp());
        assertEquals("Bob", messages.get(1).nickname());
        assertEquals("Hi Adam", messages.get(1).content());

        // Verify the fields of the third message
        assertEquals("12:35:01", messages.get(2).timestamp());
        assertEquals("Bob", messages.get(2).nickname());
        assertEquals(":( I have to work on my assignment :( on PIJ", messages.get(2).content());
    }

    /**
     * Behaviour of parser when given empty list of lines tested.
     */
    @Test
    void testParseInvalidFile() {

        List<String> lines = List.of(); // Empty input list

        // Exception thrown due to empty input
        Exception exception = assertThrows(
                Exceptions.InvalidMessageFormatException.class,
                () -> ChatParser.parse(lines) // Try to parse the empty file
        );

        // Asset that the exception message indicates that the file is empty
        assertTrue(exception.getMessage().contains("Empty File."));
    }

    /**
     * Tests that a message block with a missing field results in an InvalidMessageFormatException
     * due to an incomplete message structure.
     */

    @Test
    void testParseMalformedFile_missingField() {
        List<String> lines = List.of(
                "Time:12:34:56",
                "Message:Hello Bob :)" // Missing "Name:" line
        );

        // Expecting an exception due to a missing field in the message block
        Exception exception = assertThrows(
                Exceptions.InvalidMessageFormatException.class,
                () -> ChatParser.parse(lines));

        // Asset that the exception message indicates that there is an unexpected end of file
        assertTrue(exception.getMessage().contains("Unexpected end of file"));
    }

    /**
     * Incorrect header label ("Timestamp:" instead of "time")
     */
    @Test
    void testParseMalformedFile_incorrectHeading() {
        List<String> lines = List.of(
                "Timestamp:12:34:56",        // Incorrect header, should be "Time:"
                "Name:Adam",                 // Correct name
                "Message:Hello Bob :)"       // Correct message
        );

        // Expect exception due to the incorrect header format
        Exception exception = assertThrows(
                Exceptions.InvalidMessageFormatException.class,
                () -> ChatParser.parse(lines)
        );

        // Verify that exception message does indicate incorrect header
        assertTrue(exception.getMessage().contains("Incorrect heading format"));
    }
// TODO:
    // Go through ICA-Appendix and create test cases after adding exceptions from those cases
    // Fix the missing header test cases after adding new exceptions
    // Fix the incorrect/invalid/unrecognised header test cases
}
