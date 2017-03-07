package com.cqrs.command.engine.exception;

/**
 * Created by rsikora on 3/6/2017.
 */

public class CQRSBootstrapException extends CQRSException {

    public CQRSBootstrapException(String message, Throwable cause) {
        super(message, cause);
    }

    public CQRSBootstrapException(String message) {
        super(message);
    }
}
