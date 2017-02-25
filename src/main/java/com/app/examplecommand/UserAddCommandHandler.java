package com.app.examplecommand;

import com.cqrs.command.Command;
import com.cqrs.command.CommandHandler;

/**
 * Created by robertsikora on 25.02.2017.
 */

@Command
public class UserAddCommandHandler implements CommandHandler<UserAddCommand, Void> {

    @Override
    public Void handle(UserAddCommand command) {
        return null;
    }
}
