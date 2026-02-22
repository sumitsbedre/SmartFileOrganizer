package com.smartorganizer.exception;

/*
 * Custom exception for file processing errors.
 * 
 * This helps us wrap low-level exceptions (like IOException)
 * into a cleaner application-level exception.
 */
public class FileProcessingException extends Exception {

    // Constructor with message
    public FileProcessingException(String message) {
        super(message);
    }

    // Constructor with message and root cause
    public FileProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}