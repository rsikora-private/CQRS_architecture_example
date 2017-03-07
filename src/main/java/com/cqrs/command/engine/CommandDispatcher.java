package com.cqrs.command.engine;

/**
 * Created by robertsikora on 25.02.2017.
 */

public interface CommandDispatcher<C, R> {

     R dispatch(C command);
}
