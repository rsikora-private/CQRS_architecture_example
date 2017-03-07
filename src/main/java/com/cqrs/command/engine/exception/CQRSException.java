package com.cqrs.command.engine.exception;

/**
 * Created by rsikora on 3/6/2017.
 */

public class CQRSException extends RuntimeException {

    public CQRSException(String message, Throwable cause) {
        super(message, cause);
    }

    public CQRSException(String message) {
        super(message);
    }
}
