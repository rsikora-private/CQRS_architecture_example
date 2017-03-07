package com.cqrs.command.engine.impl;

import com.cqrs.command.engine.CommandRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * Created by robertsikora on 25.02.2017.
 */

@Component
public class AsyncDecorator<C, R> {
    private static final Logger LOG = LoggerFactory.getLogger(AsyncDecorator.class);

    private final CommandRunner<C, R> commandRunner;

    @Autowired
    public AsyncDecorator(CommandRunner<C, R> commandRunner) {
        this.commandRunner = commandRunner;
    }

    public CompletableFuture<R> runAsync(final C command) {
        LOG.debug("running new task in parallel");
        return CompletableFuture.supplyAsync(() -> commandRunner.run(command));
    }

}
