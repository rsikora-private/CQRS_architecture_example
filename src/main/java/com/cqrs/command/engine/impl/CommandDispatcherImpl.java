package com.cqrs.command.engine.impl;

import com.cqrs.command.CQRSCommand;
import com.cqrs.command.engine.CommandDispatcher;
import com.cqrs.command.engine.CommandRunner;
import com.cqrs.command.engine.exception.CQRSAsyncCommandException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by robertsikora on 25.02.2017.
 */

@Component
public class CommandDispatcherImpl<C, R> implements CommandDispatcher<C, R> {

    private static final Logger LOG = LoggerFactory.getLogger(CommandDispatcherImpl.class);

    private final CommandRunner<C, R> commandRunner;
    private final AsyncDecorator<C, R> asyncDecorator;

    @Autowired
    public CommandDispatcherImpl(CommandRunner<C, R> commandRunner, AsyncDecorator<C, R> asyncDecorator) {
        this.commandRunner = commandRunner;
        this.asyncDecorator = asyncDecorator;
    }

    @Override
    public R dispatch(C command) {
        LOG.debug("dispatch(), command {}", command);
        Assert.isTrue(isCommand(command), "Passed object there in not a CQRS command !");
        final boolean isAsync = isAsyncCommand(command);
        if (isAsync) {
            try {
                return asyncDecorator.runAsync(command).get();
            } catch (Exception ex) {
                throw new CQRSAsyncCommandException("Exception occurred in Async task", ex);
            }
        }
        return commandRunner.run(command);
    }

    private boolean isCommand(final C command) {
        return command.getClass().isAnnotationPresent(CQRSCommand.class);
    }

    private boolean isAsyncCommand(final C command) {
        return command.getClass().getAnnotation(CQRSCommand.class).async();
    }
}
