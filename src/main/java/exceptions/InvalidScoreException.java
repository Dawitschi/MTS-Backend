package main.java.exceptions;

/**
 * Exception thrown if an invalid score is entered into the system.
 */
public class InvalidScoreException extends RuntimeException{

    public InvalidScoreException(String text) {
        super("Found an invalid score: " + text);
    }
}
