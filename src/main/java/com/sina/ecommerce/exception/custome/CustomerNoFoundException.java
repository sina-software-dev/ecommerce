package com.sina.ecommerce.exception.custome;

/**
 * @author Sina Ramezani, 7/29/2025
 */
public class CustomerNoFoundException extends RuntimeException {

    public CustomerNoFoundException() {
        super();
    }

    public CustomerNoFoundException(String message) {
        super(message);
    }

    public CustomerNoFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerNoFoundException(Throwable cause) {
        super(cause);
    }

    protected CustomerNoFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
