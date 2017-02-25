package com.cqrs.command.engine.impl;

import com.cqrs.command.Command;
import com.cqrs.command.engine.CommandDispatcher;
import com.cqrs.command.engine.CommandRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by robertsikora on 25.02.2017.
 */

@Component
public class CommandDispatcherImpl<C, R> implements CommandDispatcher<C, R> {

    @Autowired
    private CommandRunner<C, R> commandRunner;

    @Autowired
    private AsyncDecorator<C, R> asyncDecorator;

    @Override
    public R dispatch(C command) {
        Assert.isTrue(!isCommand(command), "There is not an instance of command object !");
        final boolean isAsync = isAsyncCommand(command);
        if(isAsync) {

        }
        return commandRunner.run(command);
    }

    private boolean isCommand(final C command) {
        return command.getClass().isAnnotationPresent(Command.class);
    }

    private boolean isAsyncCommand(final C command) {
        return command.getClass().getAnnotation(Command.class).async();
    }
}
