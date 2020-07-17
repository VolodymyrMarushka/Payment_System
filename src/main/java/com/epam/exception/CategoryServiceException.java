package com.epam.exception;

public class CategoryServiceException extends RuntimeException {


    public CategoryServiceException() {
        super();
    }

    public CategoryServiceException(String message) {
        super(message);
    }

    public CategoryServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public CategoryServiceException(Throwable cause) {
        super(cause);
    }
}
