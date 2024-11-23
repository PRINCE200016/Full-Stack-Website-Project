package com.itrainu.Exception;

/**
 * ApplicationException is propagated from Model classes when a business logic
 * exception occurs.
 * This custom exception allows the application to provide detailed error messages 
 * or causes related to specific exceptions in the business logic layer.
 * 
 * @author Kirti Singh
 */
public class ApplicationException extends Exception {

    // Constructor to create an exception with a message
    public ApplicationException(String msg) {
        super(msg);
    }

    // Constructor to create an exception with a message and cause (another throwable)
    public ApplicationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    // Constructor to create an exception with a cause only
    public ApplicationException(Throwable cause) {
        super(cause);
    }
    
}
