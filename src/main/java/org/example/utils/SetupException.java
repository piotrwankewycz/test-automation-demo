package org.example.utils;

/**
 * Unchecked custom exception for setup failure
 */
public class SetupException extends RuntimeException{

    public SetupException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }

    public SetupException(String errorMessage) {
        super(errorMessage);
    }
}
