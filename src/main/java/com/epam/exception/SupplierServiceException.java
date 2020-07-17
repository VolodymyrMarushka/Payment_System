package com.epam.exception;

public class SupplierServiceException extends RuntimeException {

    public SupplierServiceException() {
        super();
    }

    public SupplierServiceException(String message) {
        super(message);
    }

    public SupplierServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public SupplierServiceException(Throwable cause) {
        super(cause);
    }
}
