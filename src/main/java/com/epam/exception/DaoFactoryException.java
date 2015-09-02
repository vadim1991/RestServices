package com.epam.exception;

/**
 * Created by Vadym_Vlasenko on 8/28/2015.
 */
public class DaoFactoryException extends RuntimeException {

    public DaoFactoryException() {
    }

    public DaoFactoryException(String message) {
        super(message);
    }

    public DaoFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoFactoryException(Throwable cause) {
        super(cause);
    }

    public DaoFactoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
