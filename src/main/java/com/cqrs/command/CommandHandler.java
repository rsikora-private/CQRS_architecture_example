package com.cqrs.command;

/**
 * Created by robertsikora on 25.02.2017.
 */
public interface CommandHandler<C, R> {

    R handle(C command);
}
