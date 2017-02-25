package com.cqrs.command.engine.impl;

import com.cqrs.command.engine.CommandRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Created by robertsikora on 25.02.2017.
 */

@Component
public class AsyncDecorator<C, R> {

    @Autowired
    private CommandRunner<C, R> commandRunner;

    public CompletableFuture<R> run(final C command) {
        return CompletableFuture.supplyAsync(()-> commandRunner.run(command));
    }

}
