package com.cqrs.command.engine;

/**
 * Created by robertsikora on 25.02.2017.
 */

public interface CommandRunner<C, R> {

    R run(C command);

}
