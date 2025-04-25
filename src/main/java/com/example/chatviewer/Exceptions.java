package com.example.chatviewer;

/**
 * Contains the customs exceptions for the Chat Viewer application.
 *All the exceptions are static classes that are all placed here for optimal OOP programming style.
 */
public class Exceptions {

    /**
     * Thrown when the format of the chat in the .msg file is invalid.
     * Includes the ending of a file unexpectedly.
     */
    public static class InvalidMessageFormatException extends Exception {
        public InvalidMessageFormatException(String errorMessage) {
            super(errorMessage);
        }
    }
}
 // TODO:
    // Add other exceptions.
    // Empty file.
    // Incorrect Header?
    // Basically more specific and less ambiguous.