package com.cqrs.command.engine.exception;

/**
 * Created by rsikora on 3/6/2017.
 */

class CQRSException extends RuntimeException {

    CQRSException(String message, Throwable cause) {
        super(message, cause);
    }

    CQRSException(String message) {
        super(message);
    }
}
