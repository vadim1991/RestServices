package com.epam.exception;

/**
 * Created by Vadym_Vlasenko on 9/8/2015.
 */
public class SendingException extends RuntimeException {

    public SendingException() {
    }

    public SendingException(String message) {
        super(message);
    }

    public SendingException(String message, Throwable cause) {
        super(message, cause);
    }

    public SendingException(Throwable cause) {
        super(cause);
    }

    public SendingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
