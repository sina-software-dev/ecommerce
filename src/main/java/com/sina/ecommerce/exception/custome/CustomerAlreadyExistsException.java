package com.sina.ecommerce.exception.custome;

/**
 * @author Sina Ramezani, 7/29/2025
 */
public class CustomerAlreadyExistsException extends RuntimeException {

    public CustomerAlreadyExistsException() {
        super();
    }

    public CustomerAlreadyExistsException(String message) {
        super(message);
    }

    public CustomerAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    protected CustomerAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
