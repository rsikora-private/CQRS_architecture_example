package com.cqrs.command.engine.exception;

/**
 * Created by rsikora on 3/6/2017.
 */

public class CQRSAsyncCommandException extends CQRSException {

    public CQRSAsyncCommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
