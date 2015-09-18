package com.epam.exception;

/**
 * Created by Vadym_Vlasenko on 9/8/2015.
 */
public class CustomAuthenticationException extends RuntimeException {

    public CustomAuthenticationException() {
    }

    public CustomAuthenticationException(String message) {
        super(message);
    }

    public CustomAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomAuthenticationException(Throwable cause) {
        super(cause);
    }

    public CustomAuthenticationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
